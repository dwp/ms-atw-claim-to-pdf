package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class AdaptationToVehicleClaim {

  @JsonProperty(value = "description")
  @NonNull
  String description;

  @JsonProperty(value = "dateOfInvoice")
  @NonNull
  Date dateOfInvoice;
}
