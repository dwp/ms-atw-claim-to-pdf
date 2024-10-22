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
import uk.gov.dwp.health.atw.msclaimtopdf.models.EquipmentOrAdaptation;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Evidence;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Jacksonized
@SuperBuilder
public class EquipmentOrAdaptationClaimRequest extends ClaimRequest {

  @JsonProperty(value = "evidence")
  @NonNull
  private List<Evidence> evidence;

  @JsonProperty(value = "claim")
  @NonNull
  private Map<String, List<EquipmentOrAdaptation>> claim;
}
