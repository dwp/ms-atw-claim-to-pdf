package uk.gov.dwp.health.atw.msclaimtopdf.testData;

import static java.util.Collections.singletonList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import uk.gov.dwp.health.atw.msclaimtopdf.models.*;

public class TestData {

  public static final String RESPONSE_HTML_FILES_LOCATION = "src/test/resources/responseHtmlTestData";
  public static final Double COST = 22.11;
  public static final Double DECLARATION_VERSION = 2.3;
  public static final String ACCESS_TO_WORK_NUMBER = "ATW12006521";

  public static long validClaimNumber = 1;
  public static final  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

  public static final String localDateFormatted
      = (LocalDate.of(2022, Month.JANUARY, 30)).format(formatter);

  public static final String workplaceContactLocalDateFormatted
      = (LocalDate.of(2022, Month.JANUARY, 31)).format(formatter);

  public static final LocalDateTime localDate
          = LocalDate.of(2022, Month.JANUARY, 30).atStartOfDay();

  public static Evidence evidence = Evidence.builder()
      .fileId("633ce73b-1414-433e-8a08-72449a0244fc/144b2aca-996d-4c27-bdf2-1e9b418874d3")
      .fileName("6b99f480c27e246fa5dd0453cd4fba29.pdf")
      .build();

  public static List<Evidence> evidences = singletonList(evidence);

  public static PayeeDetails payeeDetails = PayeeDetails.builder()
      .fullName("Citizen One")
      .emailAddress("citizen@test.com")
      .build();

  public static Address address = Address.builder()
      .address1("THE COTTAGE 1")
      .address2("ST. MARYS ISLAND")
      .address3("WHITLEY BAY")
      .address4("WHITLEY BAY")
      .postcode("NE26 4RS")
      .build();

  public static Address addressWithEscapeCharacter = Address.builder()
      .address1("THE & CO£TT'AGE 1")
      .address2("ST. MAR&YS£ ISLAND")
      .address3("WHIT&LEY B£AY")
      .address4("WHI&TLEY BA£Y")
      .postcode("NE26& 4£RS")
      .build();

  public static Address newAddress = Address.builder()
      .address1("15 Redburry Grove")
      .address2("Bramhope")
      .address3("Leeds")
      .address4("West Yorkshire")
      .postcode("NE26 4RS")
      .build();

  public static Address addressWithoutLineTwoAndFour = Address.builder()
          .address1("THE COTTAGE 1")
          .address3("WHITLEY BAY")
          .postcode("NE26 4RS")
          .build();

  public static BankDetails bankDetails = BankDetails.builder()
      .accountHolderName("Citizen One")
      .sortCode("000004")
      .accountNumber("12345677")
      .build();

  public static Payee payee = Payee.builder()
      .details(payeeDetails)
      .address(address)
      .bankDetails(bankDetails)
      .build();

  public static Payee payeeWithMissingOptionalFields = Payee.builder()
          .details(payeeDetails)
          .address(addressWithoutLineTwoAndFour)
          .bankDetails(bankDetails)
          .build();

  public static MonthYear monthYearOfSupport = MonthYear.builder()
      .mm("09")
      .yyyy("2021")
      .build();

  public static WorkplaceContact employedWorkplaceContactResponse = WorkplaceContact.builder()
      .employmentStatus("Employed")
      .fullName("counter Signer")
      .emailAddress("email@company.com")
      .organisation("Organisation")
      .jobTitle("boss2")
      .address(addressWithEscapeCharacter)
      .updatedOn(localDate.plusDays(1))
      .declarationVersion(DECLARATION_VERSION)
      .build();

  public static WorkplaceContact selfEmployedWorkplaceContactResponse = WorkplaceContact.builder()
      .employmentStatus("selfemployed")
      .fullName("counter Signer")
      .emailAddress("email@company.com")
      .organisation("Organisation")
      .jobTitle("boss2")
      .address(addressWithEscapeCharacter)
      .updatedOn(localDate.plusDays(1))
      .declarationVersion(DECLARATION_VERSION)
      .build();

  public static WorkplaceContact noEmploymentStatusWorkplaceContactResponse = WorkplaceContact.builder()
      .fullName("counter Signer")
      .emailAddress("email@company.com")
      .organisation("Organisation")
      .jobTitle("boss2")
      .address(addressWithEscapeCharacter)
      .updatedOn(localDate.plusDays(1))
      .declarationVersion(DECLARATION_VERSION)
      .build();

  public static WorkplaceContact employedWorkplaceContactWithMissingOptionalFields = WorkplaceContact.builder()
          .employmentStatus("Employed")
          .fullName("counter Signer")
          .emailAddress("email@company.com")
          .organisation("Organisation")
          .address(addressWithoutLineTwoAndFour)
          .updatedOn(localDate.plusDays(1))
          .declarationVersion(DECLARATION_VERSION)
          .build();

  public static Date dateOfPurchase = Date.builder()
          .dd("29")
          .mm("09")
          .yyyy("2021")
          .build();

  public static EquipmentOrAdaptation equipmentOrAdaptation = EquipmentOrAdaptation.builder()
          .description("Item 1")
          .dateOfPurchase(dateOfPurchase)
          .build();

  public static List<EquipmentOrAdaptation> equipmentOrAdaptations =
          singletonList(equipmentOrAdaptation);

  public static Date dateOfInvoice = Date.builder()
          .dd("12")
          .mm("04")
          .yyyy("2003")
          .build();

  public static AdaptationToVehicleClaim adaptationToVehicle = AdaptationToVehicleClaim.builder()
      .description("Item 1")
      .dateOfInvoice(dateOfInvoice)
      .build();

  public static AdaptationToVehicle adaptationToVehicles = AdaptationToVehicle.builder()
      .claimDescription(singletonList(adaptationToVehicle))
      .build();

  public static Claimant claimant = Claimant.builder()
          .company("Apple Inc")
          .emailAddress("test@appletest.com")
          .forename("Abel")
          .surname("Sample")
          .homeNumber("01233445566")
          .mobileNumber("0767677615")
          .dateOfBirth(LocalDate.of(1980, 10, 10))
          .address(newAddress)
          .build();

  public static Claimant claimantWithNoEmailAddress = Claimant.builder()
          .company("Apple Inc")
          .forename("Abel")
          .surname("Sample")
          .homeNumber("01233445566")
          .mobileNumber("0767677615")
          .dateOfBirth(LocalDate.of(1980, 10, 10))
          .address(newAddress)
          .build();
}
