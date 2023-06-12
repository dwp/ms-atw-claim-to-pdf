package uk.gov.dwp.health.atw.msclaimtopdf.exceptions;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

public class CryptoConfigException extends PdfGenerationException {

  public CryptoConfigException(@NonNull String msg) {
    super(HttpStatus.NOT_FOUND, msg);
  }
}
