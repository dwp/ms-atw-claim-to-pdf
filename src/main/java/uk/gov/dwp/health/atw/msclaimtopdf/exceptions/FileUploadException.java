package uk.gov.dwp.health.atw.msclaimtopdf.exceptions;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

public class FileUploadException extends PdfGenerationException {

  public FileUploadException(@NonNull String message) {
    super(HttpStatus.PRECONDITION_FAILED, message);
  }
}
