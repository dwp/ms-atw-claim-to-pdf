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
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.existingPayeeOldDataModel;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.monthYearOfSupport;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.payee;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.validClaimNumber;

import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelDetails;
import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelToWork;
import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelToWorkClaim;
import uk.gov.dwp.health.atw.msclaimtopdf.models.WorkplaceContact;
import uk.gov.dwp.health.atw.msclaimtopdf.models.enums.ClaimStatus;
import uk.gov.dwp.health.atw.msclaimtopdf.models.enums.ClaimType;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.TravelToWorkClaimRequest;

public class TravelToWorkTestData {

  public TravelToWorkClaim travelToWorkClaim = TravelToWorkClaim.builder()
      .dayOfTravel("12")
      .totalTravel("13")
      .build();

  public TravelToWorkClaim travelToWorkClaimOneJourney = TravelToWorkClaim.builder()
      .dayOfTravel("12")
      .totalTravel("1")
      .build();

  public TravelToWork travelToWorkClaimForOneMonth = TravelToWork.builder()
      .monthYear(monthYearOfSupport)
      .claim(asList(travelToWorkClaim, travelToWorkClaim))
      .build();

  public TravelToWork travelToWorkClaimForOneJourney = TravelToWork.builder()
      .monthYear(monthYearOfSupport)
      .claim(asList(travelToWorkClaimOneJourney))
      .build();

  public TravelDetails travelDetailsForLiftWithMiles = TravelDetails.builder()
      .howDidYouTravelForWork("lift")
      .journeysOrMileage("mileage")
      .build();

  public TravelDetails travelDetailsForLiftWithJourneys = TravelDetails.builder()
      .howDidYouTravelForWork("lift")
      .journeysOrMileage("journeys")
      .build();

  public TravelDetails travelDetailsForTaxi = TravelDetails.builder()
      .howDidYouTravelForWork("taxi")
      .build();


  public WorkplaceContact selfEmployedWorkplaceContact = WorkplaceContact.builder()
      .employmentStatus("Selfemployed")
      .build();

  public TravelToWorkClaimRequest taxiTravelTypeWhoIsEmployedTWClaimRequest =
      TravelToWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_TO_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .travelDetails(travelDetailsForTaxi)
          .claim(singletonMap("0", travelToWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public TravelToWorkClaimRequest
      taxiTravelTypeWhoIsEmployedTWAndNoClaimantEmailAddressClaimRequest =
      TravelToWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_TO_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .travelDetails(travelDetailsForTaxi)
          .claim(singletonMap("0", travelToWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimantWithNoEmailAddress)
          .build();

  public TravelToWorkClaimRequest taxiTravelTypeWhoIsSelfEmployedTWClaimRequest =
      TravelToWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_TO_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .travelDetails(travelDetailsForTaxi)
          .claim(singletonMap("0", travelToWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(selfEmployedWorkplaceContact)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public TravelToWorkClaimRequest taxiTravelTypeWhoIsSelfEmployedWithOneJourneyTWClaimRequest
      = TravelToWorkClaimRequest.builder()
      .id(validClaimNumber)
      .claimType(ClaimType.TRAVEL_TO_WORK)
      .cost(COST)
      .evidence(evidences)
      .payee(payee)
      .claimStatus(ClaimStatus.UPLOADED_TO_DOCUMENT_BATCH)
      .travelDetails(travelDetailsForTaxi)
      .claim(singletonMap("0", travelToWorkClaimForOneJourney))
      .declarationVersion(DECLARATION_VERSION)
      .workplaceContact(selfEmployedWorkplaceContact)
      .createdDate(TestData.localDate)
      .hasContributions(true)
      .atwNumber(ACCESS_TO_WORK_NUMBER)
      .claimant(claimant)
      .build();

  public TravelToWorkClaimRequest
      taxiTravelTypeWhoIsSelfEmployedTWAndNoClaimantEmailAddressClaimRequest =
      TravelToWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_TO_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .travelDetails(travelDetailsForTaxi)
          .claim(singletonMap("0", travelToWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(selfEmployedWorkplaceContact)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimantWithNoEmailAddress)
          .build();

  public TravelToWorkClaimRequest taxiTravelToWorkOptionalFieldSkipped =
      TravelToWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_TO_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .travelDetails(travelDetailsForTaxi)
          .claim(singletonMap("0", travelToWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactWithMissingOptionalFields)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public TravelToWorkClaimRequest liftWithMileageTravelTypeWhoIsEmployedTWClaimRequest =
      TravelToWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_TO_WORK)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .travelDetails(travelDetailsForLiftWithMiles)
          .claim(singletonMap("0", travelToWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public TravelToWorkClaimRequest liftWithJourneysTravelTypeWhoIsEmployedTWClaimRequest =
      TravelToWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_TO_WORK)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .travelDetails(travelDetailsForLiftWithJourneys)
          .claim(singletonMap("0", travelToWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public TravelToWorkClaimRequest liftWithMileageTravelTypeWhoIsSelfEmployedTWClaimRequest =
      TravelToWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_TO_WORK)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .travelDetails(travelDetailsForLiftWithMiles)
          .claim(singletonMap("0", travelToWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(selfEmployedWorkplaceContact)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public TravelToWorkClaimRequest liftWithJourneysTravelTypeWhoIsSelfEmployedTWClaimRequest =
      TravelToWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_TO_WORK)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .travelDetails(travelDetailsForLiftWithJourneys)
          .claim(singletonMap("0", travelToWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(selfEmployedWorkplaceContact)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public TravelToWorkClaimRequest taxiTravelTypeWhoIsEmployedTWWithExistingPayeeClaimRequest =
      TravelToWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_TO_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(existingPayee)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .travelDetails(travelDetailsForTaxi)
          .claim(singletonMap("0", travelToWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public TravelToWorkClaimRequest taxiTravelTypeWhoIsEmployedTWWithExistingPayeeClaimRequestOldDataModel =
      TravelToWorkClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.TRAVEL_TO_WORK)
          .cost(COST)
          .evidence(evidences)
          .payee(existingPayeeOldDataModel)
          .claimStatus(ClaimStatus.AWAITING_COUNTER_SIGN)
          .travelDetails(travelDetailsForTaxi)
          .claim(singletonMap("0", travelToWorkClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();
}
