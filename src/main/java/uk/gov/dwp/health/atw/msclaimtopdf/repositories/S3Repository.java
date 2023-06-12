package uk.gov.dwp.health.atw.msclaimtopdf.repositories;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.gov.dwp.health.atw.msclaimtopdf.config.S3Properties;
import uk.gov.dwp.health.atw.msclaimtopdf.exceptions.FileUploadException;
import uk.gov.dwp.health.atw.msclaimtopdf.services.impl.KmsServiceImpl;
import uk.gov.dwp.health.crypto.exception.CryptoException;

@Service
public class S3Repository {

  final Logger logger = LoggerFactory.getLogger(S3Repository.class);

  final S3Properties s3Properties;
  final AmazonS3 client;
  final KmsServiceImpl kmsService;
  final ObjectMapper objectMapper;

  public S3Repository(S3Properties s3Properties, AmazonS3 client, KmsServiceImpl kmsService,
                      ObjectMapper objectMapper) {
    this.s3Properties = s3Properties;
    this.client = client;
    this.kmsService = kmsService;
    this.objectMapper = objectMapper;
  }

  public String createFile(byte[] decodeBase64, String key) throws FileUploadException {
    try {
      ByteArrayInputStream fis = new ByteArrayInputStream(s3Properties.isEncryptionEnabled()
          ? objectMapper.writeValueAsBytes(kmsService.encrypt(decodeBase64))
          : decodeBase64);

      ObjectMetadata metadata = new ObjectMetadata();
      byte[] bytes = IOUtils.toByteArray(fis);
      metadata.setContentLength(bytes.length);

      ByteArrayInputStream content = new ByteArrayInputStream(bytes);
      if (!s3Properties.isEncryptionEnabled()) {
        metadata.setContentType("application/pdf");
        logger.info("File will be uploaded to S3 without encryption");
      } else {
        logger.info("File encrypted and will now upload to S3");
      }

      client.putObject(s3Properties.getBucketName(), key, content, metadata);
      logger.info("File uploaded successfully to S3");
      return key;

    } catch (CryptoException | IOException ex) {
      logger.error(String.format("Fail to upload file to S3 bucket - %s", ex.getMessage()));
      throw new FileUploadException("Fail to upload file to S3 bucket - %s" + ex.getMessage());
    }
  }
}
