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
import uk.gov.dwp.health.atw.msclaimtopdf.models.SupportWorker;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Jacksonized
@SuperBuilder
public class SupportWorkerClaimRequest extends ClaimRequestWithWorkplaceContact {

  @JsonProperty(value = "claim")
  @NonNull
  private Map<String, SupportWorker> claim;

  @JsonProperty(value = "evidence")
  @NonNull
  private List<Evidence> evidence;
}
