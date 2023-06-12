package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class PayeeDetails {

  @JsonProperty(value = "fullName")
  @NonNull
  String fullName;

  @JsonProperty(value = "emailAddress")
  String emailAddress;
}
