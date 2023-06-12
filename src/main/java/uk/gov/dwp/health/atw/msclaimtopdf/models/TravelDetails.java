package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class TravelDetails {

  @JsonProperty(value = "howDidYouTravelForWork")
  @NonNull
  String howDidYouTravelForWork;

  @JsonProperty(value = "journeysOrMileage")
  String journeysOrMileage;
}
