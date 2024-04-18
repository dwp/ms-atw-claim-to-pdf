package uk.gov.dwp.health.atw.msclaimtopdf.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Evidence;
import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelInWork;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Jacksonized
@SuperBuilder
public class TravelInWorkClaimRequest extends ClaimRequestWithWorkplaceContact {

  @JsonProperty(value = "evidence")
  private List<Evidence> evidence;

  @JsonProperty(value = "totalMileage")
  private Integer totalMileage;

  @JsonProperty(value = "claim")
  @NotNull
  @NonNull
  private Map<String, TravelInWork> claim;
}
