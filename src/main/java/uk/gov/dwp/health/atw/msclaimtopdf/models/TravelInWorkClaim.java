package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class TravelInWorkClaim {

  @JsonProperty(value = "dayOfTravel")
  @NonNull
  int dayOfTravel;

  @JsonProperty(value = "startPostcode")
  @NonNull
  String startPostcode;

  @JsonProperty(value = "endPostcode")
  @NonNull
  String endPostcode;

  @JsonProperty(value = "costOfTravel")
  @NonNull
  double costOfTravel;
}
