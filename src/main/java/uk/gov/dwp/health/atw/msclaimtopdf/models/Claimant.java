package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Claimant extends ContactInformation {

  @JsonProperty(value = "company")
  @NonNull
  private String company;
}
