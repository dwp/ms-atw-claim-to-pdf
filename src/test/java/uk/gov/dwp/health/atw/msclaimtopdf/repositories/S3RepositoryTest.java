package uk.gov.dwp.health.atw.msclaimtopdf.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.SupportWorkerTestData.supportWorkerWhoIsEmployedClaimRequest;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.dwp.health.atw.msclaimtopdf.config.S3Properties;
import uk.gov.dwp.health.atw.msclaimtopdf.exceptions.FileUploadException;
import uk.gov.dwp.health.atw.msclaimtopdf.services.impl.KmsServiceImpl;
import uk.gov.dwp.health.crypto.CryptoMessage;
import uk.gov.dwp.health.crypto.exception.CryptoException;

@SpringBootTest(classes = S3Repository.class)
class S3RepositoryTest {
  @Autowired
  S3Repository repository;

  @MockBean
  private AmazonS3 amazonS3;

  @MockBean
  private S3Properties s3Properties;

  @MockBean
  private KmsServiceImpl kmsService;

  @MockBean
  private ObjectMapper objectMapper;

  String encodedBody = "encodedResponseBody";
  byte[] decoded = Base64.getDecoder().decode(encodedBody);

  @Test
  @DisplayName("upload to S3 bucket")
  void uploadToS3() throws FileUploadException {
    String s3Key = "claim-forms/SUPPORT_WORKER/" + supportWorkerWhoIsEmployedClaimRequest.getId();

    when(amazonS3.putObject(any(String.class), any(String.class), any(InputStream.class),
        any(ObjectMetadata.class)))
        .thenReturn(new PutObjectResult());

    when(s3Properties.getBucketName()).thenReturn("bucket_name");

    assertTrue(repository.createFile(decoded, s3Key)
        .startsWith("claim-forms/" + supportWorkerWhoIsEmployedClaimRequest.getClaimType()));

    verify(amazonS3, times(1)).putObject(eq("bucket_name"), anyString(),
        any(ByteArrayInputStream.class), any(ObjectMetadata.class));
  }

  @Test
  @DisplayName("Kms Encryption succeeds")
  void testSuccessfulEncryption() throws CryptoException, IOException, FileUploadException {
    String s3Key = "claim-forms/SUPPORT_WORKER/" + supportWorkerWhoIsEmployedClaimRequest.getId();

    CryptoMessage cryptoMessage = mock(CryptoMessage.class);

    when(s3Properties.isEncryptionEnabled()).thenReturn(true);

    when(kmsService.encrypt(any(byte[].class))).thenReturn(cryptoMessage);

    when(objectMapper.writeValueAsBytes(cryptoMessage)).thenReturn("SGVsbG8gd29ybGQ=".getBytes());

    when(amazonS3.putObject(any(String.class), any(String.class), any(InputStream.class),
            any(ObjectMetadata.class))).thenReturn(
            new PutObjectResult());

    when(s3Properties.getBucketName()).thenReturn("bucket_name");

    repository.createFile(decoded, s3Key);

    InOrder inOrder = inOrder(kmsService, objectMapper, amazonS3);
    inOrder.verify(kmsService, times(1)).encrypt(any(byte[].class));
    inOrder.verify(objectMapper, times(1)).
            writeValueAsBytes(any(CryptoMessage.class));
    inOrder.verify(amazonS3, times(1))
            .putObject(any(String.class), any(String.class), any(InputStream.class),
                    any(ObjectMetadata.class));
  }

  @Test
  @DisplayName("Encryption throws CryptoException")
  void testEncryptionThrowsCryptoException()
          throws CryptoException, JsonProcessingException {
    String s3Key = "claim-forms/SUPPORT_WORKER/" + supportWorkerWhoIsEmployedClaimRequest.getId();

    when(s3Properties.isEncryptionEnabled()).thenReturn(true);

    when(kmsService.encrypt(any(byte[].class))).thenThrow(CryptoException.class);

    assertThrows(FileUploadException.class,
            () -> repository.createFile(decoded, s3Key));

    InOrder inOrder = inOrder(kmsService, objectMapper, amazonS3);
    inOrder.verify(kmsService, times(1)).encrypt(any(byte[].class));
    inOrder.verify(objectMapper, never()).writeValueAsBytes(any(CryptoMessage.class));
    inOrder.verify(amazonS3, never())
            .putObject(any(String.class), any(String.class), any(InputStream.class),
                    any(ObjectMetadata.class));
  }

  @Test
  @DisplayName("Encryption throws IOException")
  void testEncryptionThrowsIOException() throws CryptoException, JsonProcessingException {
    String s3Key = "claim-forms/SUPPORT_WORKER/" + supportWorkerWhoIsEmployedClaimRequest.getId();

    CryptoMessage cryptoMessage = mock(CryptoMessage.class);

    when(s3Properties.isEncryptionEnabled()).thenReturn(true);

    when(kmsService.encrypt(any(byte[].class))).thenReturn(cryptoMessage);

    when(objectMapper.writeValueAsBytes(cryptoMessage)).thenThrow(JsonProcessingException.class);

    assertThrows(FileUploadException.class,
            () -> repository
            .createFile(decoded, s3Key));

    InOrder inOrder = inOrder(kmsService, objectMapper, amazonS3);
    inOrder.verify(kmsService, times(1)).encrypt(any(byte[].class));
    inOrder.verify(objectMapper, times(1)).writeValueAsBytes(any(CryptoMessage.class));
    inOrder.verify(amazonS3, never())
            .putObject(any(String.class), any(String.class), any(InputStream.class),
                    any(ObjectMetadata.class));
  }
}