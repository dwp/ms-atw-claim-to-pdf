package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class BankDetails {

  @JsonProperty(value = "accountHolderName")
  @NonNull
  String accountHolderName;

  @JsonProperty(value = "sortCode")
  @NonNull
  String sortCode;

  @JsonProperty(value = "accountNumber")
  @NonNull
  String accountNumber;

  @JsonProperty(value = "rollNumber")
  String rollNumber;
}
