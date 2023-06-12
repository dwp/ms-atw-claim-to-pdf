package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class TimeOfSupport {

  @JsonProperty(value = "hoursOfSupport")
  @NonNull
  String hoursOfSupport;

  @JsonProperty(value = "minutesOfSupport")
  @NonNull
  String minutesOfSupport;

}
