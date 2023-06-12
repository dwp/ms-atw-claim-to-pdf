package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class TravelToWorkClaim {

  @JsonProperty(value = "dayOfTravel")
  @NonNull
  String dayOfTravel;

  @JsonProperty(value = "totalTravel")
  @NonNull
  String totalTravel;
}
