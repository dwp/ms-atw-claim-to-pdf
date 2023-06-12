package uk.gov.dwp.health.atw.msclaimtopdf.testData;

import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.ACCESS_TO_WORK_NUMBER;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.COST;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.DECLARATION_VERSION;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.claimant;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.claimantWithNoEmailAddress;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.equipmentOrAdaptations;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.evidences;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.payee;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.payeeWithMissingOptionalFields;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.validClaimNumber;

import uk.gov.dwp.health.atw.msclaimtopdf.models.enums.ClaimType;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.EquipmentOrAdaptationClaimRequest;

public class EquipmentOrAdaptationTestData {

  public static EquipmentOrAdaptationClaimRequest validEquipmentOrAdaptationSubmitRequest =
      EquipmentOrAdaptationClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.EQUIPMENT_OR_ADAPTATION)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claim(equipmentOrAdaptations)
          .claimant(claimant)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .declarationVersion(DECLARATION_VERSION)
          .build();

  public static EquipmentOrAdaptationClaimRequest validEquipmentOrAdaptationWithNoClaimantEmailAddressSubmitRequest =
      EquipmentOrAdaptationClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.EQUIPMENT_OR_ADAPTATION)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claim(equipmentOrAdaptations)
          .claimant(claimantWithNoEmailAddress)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .declarationVersion(DECLARATION_VERSION)
          .build();

  public static EquipmentOrAdaptationClaimRequest equipmentOrAdaptationOptionalFieldSkipped =
      EquipmentOrAdaptationClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.EQUIPMENT_OR_ADAPTATION)
          .cost(COST)
          .evidence(evidences)
          .payee(payeeWithMissingOptionalFields)
          .claim(equipmentOrAdaptations)
          .declarationVersion(DECLARATION_VERSION)
          .claimant(claimant)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .build();
}
