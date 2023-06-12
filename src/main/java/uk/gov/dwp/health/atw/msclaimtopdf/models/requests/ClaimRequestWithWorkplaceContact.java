package uk.gov.dwp.health.atw.msclaimtopdf.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uk.gov.dwp.health.atw.msclaimtopdf.models.WorkplaceContact;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ClaimRequestWithWorkplaceContact extends ClaimRequest {
  @JsonProperty(value = "workplaceContact")
  @NonNull
  private WorkplaceContact workplaceContact;
}
