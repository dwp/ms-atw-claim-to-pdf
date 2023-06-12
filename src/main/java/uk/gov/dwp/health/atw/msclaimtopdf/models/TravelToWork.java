package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class TravelToWork {

  @JsonProperty(value = "monthYear")
  @NonNull
  MonthYear monthYear;

  @JsonProperty(value = "claim")
  @NonNull
  List<TravelToWorkClaim> claim;
}
