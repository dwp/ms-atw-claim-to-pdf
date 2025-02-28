package uk.gov.dwp.health.atw.msclaimtopdf.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.dwp.health.atw.msclaimtopdf.config.properties.CryptoConfigProperties;
import uk.gov.dwp.health.atw.msclaimtopdf.exceptions.CryptoConfigException;
import uk.gov.dwp.health.crypto.CryptoConfig;
import uk.gov.dwp.health.crypto.CryptoDataManager;
import uk.gov.dwp.health.crypto.exception.CryptoException;

@Slf4j
@Configuration
public class KmsConfig {

  final Logger logger = LoggerFactory.getLogger(KmsConfig.class);

  @SneakyThrows
  @Bean
  public CryptoConfig cryptoConfig(final CryptoConfigProperties properties) {
    logger.info("Getting KMS Data Key and KMS Override values");
    var config = new CryptoConfig(properties.getDataKey());
    if (properties.getKmsOverride() != null && !properties.getKmsOverride().isBlank()) {
      config.setKmsEndpointOverride(properties.getKmsOverride());
    }
    return config;
  }

  @SneakyThrows
  @Bean
  public CryptoDataManager cryptoDataManager(final CryptoConfig cryptoConfig) {
    try {
      return new CryptoDataManager(cryptoConfig);
    } catch (CryptoException ex) {
      final String msg = String.format("kms crypto config error %s", ex.getMessage());
      log.error(msg);
      throw new CryptoConfigException(msg);
    }
  }
}
