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
import uk.gov.dwp.health.atw.msclaimtopdf.models.AdaptationToVehicle;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Evidence;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Jacksonized
@SuperBuilder
public class AdaptationToVehicleClaimRequest extends ClaimRequest {

  @JsonProperty(value = "evidence")
  @NotNull
  @NonNull
  private List<Evidence> evidence;

  @JsonProperty(value = "claim")
  @NotNull
  @NonNull
  private Map<String, AdaptationToVehicle> claim;
}
