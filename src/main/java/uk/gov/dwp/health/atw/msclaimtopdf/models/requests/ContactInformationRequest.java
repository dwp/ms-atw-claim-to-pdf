package uk.gov.dwp.health.atw.msclaimtopdf.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import uk.gov.dwp.health.atw.msclaimtopdf.models.ContactInformation;

@Data
@NoArgsConstructor
@Jacksonized
@SuperBuilder
public class ContactInformationRequest extends PersonalDetailsRequest {

  @JsonProperty(value = "newContactInformation")
  @NonNull
  private ContactInformation newContactInformation;
}
