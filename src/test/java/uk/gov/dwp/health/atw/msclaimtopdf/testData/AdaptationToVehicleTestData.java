package uk.gov.dwp.health.atw.msclaimtopdf.testData;

import java.util.Map;
import uk.gov.dwp.health.atw.msclaimtopdf.models.enums.ClaimType;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.AdaptationToVehicleClaimRequest;

import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.ACCESS_TO_WORK_NUMBER;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.COST;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.DECLARATION_VERSION;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.adaptationToVehicles;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.claimant;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.claimantWithNoEmailAddress;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.evidences;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.payee;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.payeeWithMissingOptionalFields;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.validClaimNumber;

public class AdaptationToVehicleTestData {

  public static AdaptationToVehicleClaimRequest validAdaptationToVehicleSubmitRequest =
      AdaptationToVehicleClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.ADAPTATION_TO_VEHICLE)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claim(Map.of("0", adaptationToVehicles))
          .claimant(claimant)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .declarationVersion(DECLARATION_VERSION)
          .build();

  public static AdaptationToVehicleClaimRequest validAdaptationToVehicleWithNoClaimantEmailAddressSubmitRequest =
      AdaptationToVehicleClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.ADAPTATION_TO_VEHICLE)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claim(Map.of("0", adaptationToVehicles))
          .claimant(claimantWithNoEmailAddress)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .declarationVersion(DECLARATION_VERSION)
          .build();

  public static AdaptationToVehicleClaimRequest adaptationToVehicleOptionalFieldSkipped =
      AdaptationToVehicleClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.ADAPTATION_TO_VEHICLE)
          .cost(COST)
          .evidence(evidences)
          .payee(payeeWithMissingOptionalFields)
          .claim(Map.of("0", adaptationToVehicles))
          .declarationVersion(DECLARATION_VERSION)
          .claimant(claimant)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .build();
}
