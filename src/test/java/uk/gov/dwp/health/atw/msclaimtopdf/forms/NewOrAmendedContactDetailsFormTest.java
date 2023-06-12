package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import static org.junit.jupiter.api.Assertions.*;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.RESPONSE_HTML_FILES_LOCATION;

import j2html.tags.specialized.HtmlTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.dwp.health.atw.msclaimtopdf.testData.ContactInformationTestData;
import uk.gov.dwp.health.atw.msclaimtopdf.utils.TestUtils;

@SpringBootTest(classes = NewOrAmendedContactDetailsForm.class)
class NewOrAmendedContactDetailsFormTest {

  @Test
  @DisplayName("generate html for new address details form successful")
  void generateSuccessfulForm() {
    HtmlTag htmlTag = NewOrAmendedContactDetailsForm.
        generateForm(ContactInformationTestData.submittedContactInformationRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/NewAddressDetails.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate html for form with optional fields added in the new contact information but were not in the old contact information successful")
  void generateFormWithOptionalFields() {
    HtmlTag htmlTag = NewOrAmendedContactDetailsForm.
        generateForm(ContactInformationTestData.submittedContactInformationWithOptionalFieldsRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/NewContactDetailsWithOptionalFields.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate html for form with optional fields for new and old contact information successful")
  void generateFormWithOptionalFieldsForBothNewAndOldContactInformation() {
    HtmlTag htmlTag = NewOrAmendedContactDetailsForm.
        generateForm(ContactInformationTestData.submittedContactInformationWithOptionalFieldsForOldAndNewRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/NewContactDetailsWithOptionalFieldsForNewAndOld.html"),
        htmlTag.render());
  }
}