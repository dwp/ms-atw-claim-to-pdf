package uk.gov.dwp.health.atw.msclaimtopdf.testData;

import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.ACCESS_TO_WORK_NUMBER;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.DECLARATION_VERSION;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.address;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.localDate;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.newAddress;

import java.time.LocalDate;
import uk.gov.dwp.health.atw.msclaimtopdf.models.ContactInformation;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ContactInformationRequest;

public class ContactInformationTestData {

  public static final String FORENAME = "Martha";
  public static final String SURNAME = "Ledgrave";
  public static final LocalDate DOB = LocalDate.of(1930, 11, 12);
  public static final String HOME_NUMBER = "01233665599";
  public static final String MOBILE_NUMBER = "07627847834";
  public static final String EMAIL_ADDRESS = "marthaLedgrave@company.com";

  public static ContactInformation oldContactInformationAllFields =
      ContactInformation.builder()
          .forename(FORENAME)
          .surname(SURNAME)
          .dateOfBirth(DOB)
          .emailAddress(EMAIL_ADDRESS)
          .homeNumber(HOME_NUMBER)
          .mobileNumber(MOBILE_NUMBER)
          .address(address)
          .build();

  public static ContactInformation oldContactWithOptionalFieldsInformation =
      ContactInformation.builder()
          .forename(FORENAME)
          .surname(SURNAME)
          .dateOfBirth(DOB)
          .address(address)
          .build();

  public static ContactInformation newContactInformationAllFields =
      ContactInformation.builder()
          .forename(FORENAME)
          .surname(SURNAME)
          .dateOfBirth(DOB)
          .emailAddress(EMAIL_ADDRESS)
          .homeNumber(HOME_NUMBER)
          .mobileNumber(MOBILE_NUMBER)
          .address(newAddress)
          .build();

  public static ContactInformation newContactInformationWithOptionalFields =
      ContactInformation.builder()
          .forename(FORENAME)
          .surname(SURNAME)
          .dateOfBirth(DOB)
          .address(newAddress)
          .build();

  public static ContactInformationRequest submittedContactInformationRequest =
      ContactInformationRequest.builder()
          .accessToWorkNumber(ACCESS_TO_WORK_NUMBER)
          .currentContactInformation(oldContactInformationAllFields)
          .newContactInformation(newContactInformationAllFields)
          .declarationVersion(DECLARATION_VERSION)
          .createdDate(localDate)
          .build();

  public static ContactInformationRequest submittedContactInformationWithOptionalFieldsRequest =
      ContactInformationRequest.builder()
          .accessToWorkNumber(ACCESS_TO_WORK_NUMBER)
          .currentContactInformation(oldContactWithOptionalFieldsInformation)
          .newContactInformation(newContactInformationAllFields)
          .declarationVersion(DECLARATION_VERSION)
          .createdDate(localDate)
          .build();

  public static ContactInformationRequest submittedContactInformationWithOptionalFieldsForOldAndNewRequest =
      ContactInformationRequest.builder()
          .accessToWorkNumber(ACCESS_TO_WORK_NUMBER)
          .currentContactInformation(oldContactWithOptionalFieldsInformation)
          .newContactInformation(newContactInformationWithOptionalFields)
          .declarationVersion(DECLARATION_VERSION)
          .createdDate(localDate)
          .build();
}
