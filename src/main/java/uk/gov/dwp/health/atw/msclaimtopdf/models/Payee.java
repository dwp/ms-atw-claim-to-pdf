package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class Payee {

  @JsonProperty(value = "details")
  @NonNull
  PayeeDetails details;

  @JsonProperty(value = "address")
  Address address;

  @JsonProperty(value = "bankDetails")
  BankDetails bankDetails;

  @JsonProperty(value = "newPayee")
  @NonNull
  boolean newPayee;
}
