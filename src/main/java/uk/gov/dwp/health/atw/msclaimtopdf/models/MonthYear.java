package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class MonthYear {

  @JsonProperty(value = "mm")
  @NonNull
  String mm;

  @JsonProperty(value = "yyyy")
  @NonNull
  String yyyy;
}
