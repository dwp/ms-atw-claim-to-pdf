package uk.gov.dwp.health.atw.msclaimtopdf.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Payee;

@Data
@NoArgsConstructor
@Jacksonized
@SuperBuilder
public class NewPayeeDetailsRequest extends PersonalDetailsRequest {

  @JsonProperty(value = "payee")
  @NonNull
  private Payee payee;
}
