package uk.gov.dwp.health.atw.msclaimtopdf.testData;

import static uk.gov.dwp.health.atw.msclaimtopdf.testData.ContactInformationTestData.oldContactInformationAllFields;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.ContactInformationTestData.oldContactWithOptionalFieldsInformation;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.ACCESS_TO_WORK_NUMBER;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.DECLARATION_VERSION;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.localDate;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.payee;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.payeeWithMissingOptionalFields;

import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.NewPayeeDetailsRequest;

public class NewPayeeDetailsTestData {
  public static NewPayeeDetailsRequest newPayeeDetailsRequest =
      NewPayeeDetailsRequest.builder()
          .accessToWorkNumber(ACCESS_TO_WORK_NUMBER)
          .currentContactInformation(oldContactInformationAllFields)
          .declarationVersion(DECLARATION_VERSION)
          .createdDate(localDate)
          .payee(payee)
          .build();

  public static NewPayeeDetailsRequest newPayeeDetailsRequestWithOptionalFields =
      NewPayeeDetailsRequest.builder()
          .accessToWorkNumber(ACCESS_TO_WORK_NUMBER)
          .currentContactInformation(oldContactWithOptionalFieldsInformation)
          .declarationVersion(DECLARATION_VERSION)
          .createdDate(localDate)
          .payee(payeeWithMissingOptionalFields)
          .build();
}
