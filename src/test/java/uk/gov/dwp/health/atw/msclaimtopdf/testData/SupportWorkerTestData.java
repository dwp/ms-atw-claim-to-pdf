package uk.gov.dwp.health.atw.msclaimtopdf.testData;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.*;

import uk.gov.dwp.health.atw.msclaimtopdf.models.SupportWorker;
import uk.gov.dwp.health.atw.msclaimtopdf.models.SupportWorkerClaim;
import uk.gov.dwp.health.atw.msclaimtopdf.models.TimeOfSupport;
import uk.gov.dwp.health.atw.msclaimtopdf.models.enums.ClaimStatus;
import uk.gov.dwp.health.atw.msclaimtopdf.models.enums.ClaimType;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.SupportWorkerClaimRequest;

public class SupportWorkerTestData {

  private static TimeOfSupport timeOfSupport2Hours15Mins = TimeOfSupport.builder()
      .hoursOfSupport("2")
      .minutesOfSupport("15")
      .build();

  public static SupportWorkerClaim supportWorkerClaim = SupportWorkerClaim.builder()
      .dayOfSupport("1")
      .timeOfSupport(timeOfSupport2Hours15Mins)
      .build();

  private static TimeOfSupport timeOfSupport3Hours25Mins = TimeOfSupport.builder()
      .hoursOfSupport("3")
      .minutesOfSupport("25")
      .build();

  public static SupportWorkerClaim supportWorkerClaim2 = SupportWorkerClaim.builder()
      .dayOfSupport("2")
      .timeOfSupport(timeOfSupport3Hours25Mins)
      .nameOfSupport("Person 2")
      .build();

  private static TimeOfSupport timeOfSupport3Hours0Mins = TimeOfSupport.builder()
      .hoursOfSupport("3")
      .minutesOfSupport("0")
      .build();

  public static SupportWorkerClaim supportWorkerClaimWithHoursToTwoDecimalPlaces = SupportWorkerClaim.builder()
      .dayOfSupport("1")
      .timeOfSupport(timeOfSupport3Hours0Mins)
      .build();

  private static TimeOfSupport timeOfSupport4Hours0Mins = TimeOfSupport.builder()
      .hoursOfSupport("4")
      .minutesOfSupport("0")
      .build();

  public static SupportWorkerClaim supportWorkerClaimWithHoursToTwoDecimalPlaces2 = SupportWorkerClaim.builder()
      .dayOfSupport("2")
      .timeOfSupport(timeOfSupport4Hours0Mins)
      .nameOfSupport("Person 2")
      .build();

  public static SupportWorker supportWorkerClaimForOneMonth = SupportWorker.builder()
      .monthYear(monthYearOfSupport)
      .supportWorkerClaims(asList(supportWorkerClaim, supportWorkerClaim2))
      .build();

  public static SupportWorker supportWorkerClaimWithHoursToTwoDecimalPlacesForOneMonth = SupportWorker.builder()
      .monthYear(monthYearOfSupport)
      .supportWorkerClaims(asList(supportWorkerClaimWithHoursToTwoDecimalPlaces, supportWorkerClaimWithHoursToTwoDecimalPlaces2))
      .build();

  public static SupportWorkerClaim supportWorkerClaimWithHoursOfSupport = SupportWorkerClaim.builder()
      .dayOfSupport("1")
      .hoursOfSupport("2.00")
      .build();

  public static SupportWorkerClaim supportWorkerClaimWith3HoursOfSupport = SupportWorkerClaim.builder()
      .dayOfSupport("3")
      .hoursOfSupport("3.25")
      .nameOfSupport("Person 33")
      .build();

  public static SupportWorker supportWorkerClaimWithHoursToSupportForOneMonth = SupportWorker.builder()
      .monthYear(monthYearOfSupport)
      .supportWorkerClaims(asList(supportWorkerClaimWithHoursOfSupport, supportWorkerClaimWith3HoursOfSupport))
      .build();

  public static SupportWorkerClaimRequest supportWorkerClaimRequestWithHoursOfSupportClaims =
      SupportWorkerClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.SUPPORT_WORKER)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_DRS_UPLOAD)
          .claim(singletonMap("0", supportWorkerClaimWithHoursToSupportForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public static SupportWorkerClaimRequest supportWorkerWhoIsEmployedWithSupportHoursToTwoDecimalPlacesClaimRequest =
      SupportWorkerClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.SUPPORT_WORKER)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_DRS_UPLOAD)
          .claim(singletonMap("0", supportWorkerClaimWithHoursToTwoDecimalPlacesForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public static SupportWorkerClaimRequest supportWorkerWhoIsEmployedClaimRequest =
      SupportWorkerClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.SUPPORT_WORKER)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_DRS_UPLOAD)
          .claim(singletonMap("0", supportWorkerClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public static SupportWorkerClaimRequest supportWorkerWithNoClaimantEmailAddressClaimRequest =
      SupportWorkerClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.SUPPORT_WORKER)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_DRS_UPLOAD)
          .claim(singletonMap("0", supportWorkerClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimantWithNoEmailAddress)
          .build();

  public static SupportWorkerClaimRequest supportWorkerClaimRequestWithOptionalFieldsMissing =
      SupportWorkerClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.SUPPORT_WORKER)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_DRS_UPLOAD)
          .claim(singletonMap("0", supportWorkerClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactWithMissingOptionalFields)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();
}
