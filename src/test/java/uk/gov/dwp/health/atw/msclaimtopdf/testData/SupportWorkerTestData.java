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

  private static TimeOfSupport timeOfSupport3Hours25Mins = TimeOfSupport.builder()
      .hoursOfSupport("3")
      .minutesOfSupport("25")
      .build();

  public static SupportWorkerClaim supportWorkerClaim2Hours15Mins = SupportWorkerClaim.builder()
      .dayOfSupport("1")
      .timeOfSupport(timeOfSupport2Hours15Mins)
      .build();

    public static SupportWorkerClaim supportWorkerClaim3Hours25Mins = SupportWorkerClaim.builder()
      .dayOfSupport("2")
      .timeOfSupport(timeOfSupport3Hours25Mins)
      .build();

  private static TimeOfSupport timeOfSupport3Hours0Mins = TimeOfSupport.builder()
      .hoursOfSupport("3")
      .minutesOfSupport("0")
      .build();

  public static SupportWorkerClaim supportWorkerClaimWith3Hours0Mins = SupportWorkerClaim.builder()
      .dayOfSupport("3")
      .timeOfSupport(timeOfSupport3Hours0Mins)
      .build();

  private static TimeOfSupport timeOfSupport4Hours0Mins = TimeOfSupport.builder()
      .hoursOfSupport("4")
      .minutesOfSupport("0")
      .build();

  public static SupportWorkerClaim supportWorkerClaimWith4Hours0Mins = SupportWorkerClaim.builder()
      .dayOfSupport("5")
      .timeOfSupport(timeOfSupport4Hours0Mins)
      .build();

  public static SupportWorker supportWorkerClaimForOneMonth = SupportWorker.builder()
      .monthYear(monthYearOfSupport)
      .supportWorkerClaims(asList(supportWorkerClaim2Hours15Mins, supportWorkerClaim3Hours25Mins))
      .build();

  public static SupportWorker supportWorkerClaimWithHoursOnlyForOneMonth = SupportWorker.builder()
      .monthYear(monthYearOfSupport)
      .supportWorkerClaims(asList(supportWorkerClaimWith3Hours0Mins, supportWorkerClaimWith4Hours0Mins))
      .build();

  public static SupportWorkerClaim supportWorkerClaimWithHoursOfSupport = SupportWorkerClaim.builder()
      .dayOfSupport("6")
      .hoursOfSupport("2.00")
      .build();

  public static SupportWorkerClaim supportWorkerClaimWith3HoursOfSupport = SupportWorkerClaim.builder()
      .dayOfSupport("7")
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

  public static SupportWorkerClaimRequest supportWorkerWhoIsEmployedWithSupportHoursInTimeOfSupportOnlyClaimRequest =
      SupportWorkerClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.SUPPORT_WORKER)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_DRS_UPLOAD)
          .nameOfSupport("Person 2")
          .claim(singletonMap("0", supportWorkerClaimWithHoursOnlyForOneMonth))
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
          .nameOfSupport("Person 2")
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
          .nameOfSupport("Person 2")
          .claim(singletonMap("0", supportWorkerClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimantWithNoEmailAddress)
          .build();

  public static SupportWorkerClaimRequest supportWorkerClaimRequestWithOptionalWorkplaceContactFieldsMissing =
      SupportWorkerClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.SUPPORT_WORKER)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_DRS_UPLOAD)
          .nameOfSupport("Person 2")
          .claim(singletonMap("0", supportWorkerClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactWithMissingOptionalFields)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();

  public static SupportWorkerClaimRequest supportWorkerWhoIsEmployedAndHasExistingPayeeClaimRequest =
      SupportWorkerClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.SUPPORT_WORKER)
          .cost(COST)
          .evidence(evidences)
          .payee(existingPayee)
          .claimStatus(ClaimStatus.AWAITING_DRS_UPLOAD)
          .nameOfSupport("Person 2")
          .claim(singletonMap("0", supportWorkerClaimForOneMonth))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();


  public static SupportWorkerClaim supportWorkerClaimWithNameOfSupport = SupportWorkerClaim.builder()
      .dayOfSupport("4")
      .timeOfSupport(timeOfSupport3Hours25Mins)
      .nameOfSupport("Person 3")
      .build();

  public static SupportWorker supportWorkerClaimWithNameOfSupportOnSupportWorkerClaim = SupportWorker.builder()
      .monthYear(monthYearOfSupport)
      .supportWorkerClaims(asList(supportWorkerClaim2Hours15Mins, supportWorkerClaimWithNameOfSupport))
      .build();

  public static SupportWorkerClaimRequest supportWorkerClaimRequestWithNameOfSupportOnSupportWorkerClaim =
      SupportWorkerClaimRequest.builder()
          .id(validClaimNumber)
          .claimType(ClaimType.SUPPORT_WORKER)
          .cost(COST)
          .evidence(evidences)
          .payee(payee)
          .claimStatus(ClaimStatus.AWAITING_DRS_UPLOAD)
          .claim(singletonMap("0", supportWorkerClaimWithNameOfSupportOnSupportWorkerClaim))
          .declarationVersion(DECLARATION_VERSION)
          .workplaceContact(employedWorkplaceContactResponse)
          .createdDate(TestData.localDate)
          .hasContributions(true)
          .atwNumber(ACCESS_TO_WORK_NUMBER)
          .claimant(claimant)
          .build();
}
