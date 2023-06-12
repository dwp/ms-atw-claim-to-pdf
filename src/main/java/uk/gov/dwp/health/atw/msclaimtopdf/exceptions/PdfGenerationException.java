package uk.gov.dwp.health.atw.msclaimtopdf.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@ToString
public abstract class PdfGenerationException extends Exception {
  private HttpStatus errorCode;
  private String errorMessage;
}
