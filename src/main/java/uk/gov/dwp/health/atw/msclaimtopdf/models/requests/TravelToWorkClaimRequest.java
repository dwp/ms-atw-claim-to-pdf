package uk.gov.dwp.health.atw.msclaimtopdf.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Evidence;
import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelDetails;
import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelToWork;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Jacksonized
@SuperBuilder
public class TravelToWorkClaimRequest extends ClaimRequestWithWorkplaceContact {

  @JsonProperty(value = "evidence")
  private List<Evidence> evidence;

  @JsonProperty(value = "travelDetails")
  @NonNull
  private TravelDetails travelDetails;

  @JsonProperty(value = "claim")
  @NonNull
  private Map<String, TravelToWork> claim;
}
