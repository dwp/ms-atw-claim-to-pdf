package uk.gov.dwp.health.atw.msclaimtopdf.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Claimant;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Payee;
import uk.gov.dwp.health.atw.msclaimtopdf.models.enums.ClaimStatus;
import uk.gov.dwp.health.atw.msclaimtopdf.models.enums.ClaimType;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "claimType", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = EquipmentOrAdaptationClaimRequest.class,
        name = "EQUIPMENT_OR_ADAPTATION"),
    @JsonSubTypes.Type(value = TravelToWorkClaimRequest.class, name = "TRAVEL_TO_WORK"),
    @JsonSubTypes.Type(value = SupportWorkerClaimRequest.class, name = "SUPPORT_WORKER")
})
public class ClaimRequest {

  private long id;

  @JsonProperty(value = "claimType")
  @NonNull
  private ClaimType claimType;

  @JsonProperty(value = "cost")
  private Double cost;

  @JsonProperty(value = "hasContributions")
  @NonNull
  private Boolean hasContributions;

  @JsonProperty(value = "payee")
  @NonNull
  private Payee payee;

  @JsonProperty(value = "declarationVersion")
  @NonNull
  private Double declarationVersion;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime createdDate;

  private ClaimStatus claimStatus;

  @JsonProperty(value = "atwNumber")
  @NonNull
  private String atwNumber;

  @JsonProperty(value = "claimant")
  @NonNull
  private Claimant claimant;


}
