package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class AdaptationToVehicle {

  @JsonProperty(value = "claimDescription")
  @NotNull
  @NonNull
  List<AdaptationToVehicleClaim> claimDescription;
}
