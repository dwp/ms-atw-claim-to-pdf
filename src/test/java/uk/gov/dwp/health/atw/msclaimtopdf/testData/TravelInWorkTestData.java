package uk.gov.dwp.health.atw.msclaimtopdf.testData;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.ACCESS_TO_WORK_NUMBER;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.COST;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.DECLARATION_VERSION;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.claimant;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.claimantWithNoEmailAddress;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.employedWorkplaceContactResponse;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.employedWorkplaceContactWithMissingOptionalFields;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.evidences;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.existingPayee;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.monthYearOfSupport;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.payee;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.validClaimNumber;

import java.util.Map;
import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelInWork;
import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelInWorkClaim;
import uk.gov.dwp.health.atw.msclaimtopdf.models.WorkplaceContact;
import uk.gov.dwp.health.atw.msclaimtopdf.models.enums.ClaimStatus;
import uk.gov.dwp.health.atw.msclaimtopdf.models.enums.ClaimType;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.TravelInWorkClaimRequest;

public class TravelInWorkTestData {

  public TravelInWorkClaim travelInWorkClaim = TravelInWorkClaim.builder()
      .dayOfTravel(12)
      .startPostcode("xx11 1xx")
      .endPostcode("xx11 1xx")
      .costOfTravel(11.05)
      .build();

  public TravelInWorkClaim travelInWorkClaim2 = TravelInWorkClaim.builder()
      .dayOfTravel(12)
      .startPostcode("bb11 1bb")
      .endPostcode("bb11 1bb")
      .costOfTravel(11.05)
      .build();

  public TravelInWork travelInWorkClaimForOneMonth = TravelInWork.builder()
      .monthYear(monthYearOfSupport)
      .claim(asList(travelInWorkClaim, travelInWorkClaim2))
      .build();

  public WorkplaceContact selfEmployedWorkplaceContact = WorkplaceContact.builder()
      .employmentStatus("Selfemployed")
      .build();

  public TravelInWorkClaimRequest tiwClaimWithEmployedEmploymentStatusRequest =
      TravelInWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_IN_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .totalMileage(10)
          .claim(singletonMap("0", travelInWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public TravelInWorkClaimRequest tiwClaimWithEmployedEmploymentStatusRequestForMultipleMonths =
      TravelInWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_IN_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .totalMileage(10)
          .claim(Map.of("0", travelInWorkClaimForOneMonth, "1", travelInWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public TravelInWorkClaimRequest
      tiwWhoIsEmployedAndNoClaimantEmailAddressClaimRequest =
      TravelInWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_IN_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .totalMileage(10)
          .claim(singletonMap("0", travelInWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimantWithNoEmailAddress)
          .build();

  public TravelInWorkClaimRequest taxiTravelInWorkOptionalFieldSkipped =
      TravelInWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_IN_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .totalMileage(10)
          .claim(singletonMap("0", travelInWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactWithMissingOptionalFields)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public TravelInWorkClaimRequest tiwWhoIsSelfEmployedClaimRequest =
      TravelInWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_IN_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .totalMileage(10)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .claim(singletonMap("0", travelInWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(selfEmployedWorkplaceContact)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public TravelInWorkClaimRequest
      tiwWhoIsSelfEmployedAndNoClaimantEmailAddressClaimRequest =
      TravelInWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_IN_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .totalMileage(10)
          .claim(singletonMap("0", travelInWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(selfEmployedWorkplaceContact)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimantWithNoEmailAddress)
          .build();

  public TravelInWorkClaimRequest tiwWhoIsEmployedAndHasExistingPayeeClaimRequest =
      TravelInWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_IN_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(existingPayee)
          .claimStatus(ClaimStatus.AWAITING_DRS_UPLOAD)
          .totalMileage(10)
          .claim(singletonMap("0", travelInWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();
}
