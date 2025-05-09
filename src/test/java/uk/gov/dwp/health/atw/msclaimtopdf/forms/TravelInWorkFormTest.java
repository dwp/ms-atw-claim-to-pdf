package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.RESPONSE_HTML_FILES_LOCATION;

import j2html.tags.specialized.HtmlTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.dwp.health.atw.msclaimtopdf.testData.TravelInWorkTestData;
import uk.gov.dwp.health.atw.msclaimtopdf.utils.TestUtils;

@SpringBootTest(classes = TravelInWorkForm.class)
class TravelInWorkFormTest {

  TravelInWorkTestData travelInWorkTestData;

  @BeforeEach
  void setup() {
    travelInWorkTestData = new TravelInWorkTestData();
  }

  @Test
  @DisplayName("generate form for client who has employed employment status")
  void generateFormWithEmployedEmploymentStatus() {
    HtmlTag htmlTag = TravelInWorkForm.generateForm(
        travelInWorkTestData.tiwClaimWithEmployedEmploymentStatusRequest);

    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelInWork.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has employed employment status for multiple months")
  void generateFormWithEmployedEmploymentStatusForMultipleMonths() {
    HtmlTag htmlTag = TravelInWorkForm.generateForm(
        travelInWorkTestData.tiwClaimWithEmployedEmploymentStatusRequestForMultipleMonths);

    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelInWorkForEmployedMultipleMonths.html"),
        htmlTag.render());
  }


  @Test
  @DisplayName("generate form for client who has employed employment status with no email address for the claimant")
  void generateFormWithTaxiTravelTypeAndEmployedEmploymentStatusWithNoEmailAddressForClaimant() {
    HtmlTag htmlTag = TravelInWorkForm.generateForm(
        travelInWorkTestData.tiwWhoIsEmployedAndNoClaimantEmailAddressClaimRequest);
    assertEquals(TestUtils.getFileAsString(
            RESPONSE_HTML_FILES_LOCATION + "/TravelInWorkWithNoClaimantEmailAddress.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has employed employment status with optional fields missing")
  void generateFormWithSkippedOptionalField() {
    HtmlTag htmlTag = TravelInWorkForm.generateForm(
        travelInWorkTestData.taxiTravelInWorkOptionalFieldSkipped);
    assertEquals(TestUtils.getFileAsString(
            RESPONSE_HTML_FILES_LOCATION + "/TravelInWorkOptionalFieldSkipped.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has self employed employment status")
  void generateFormTiwAndSelfEmployedEmploymentStatus() {
    HtmlTag htmlTag = TravelInWorkForm.generateForm(
        travelInWorkTestData.tiwWhoIsSelfEmployedClaimRequest);
    assertEquals(TestUtils.getFileAsString(
            RESPONSE_HTML_FILES_LOCATION + "/TravelInWorkWithTaxiWhoIsSelfEmployed.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has self employed employment status with no email address for the claimant")
  void generateFormTiwAndSelfEmployedEmploymentStatusWithNoEmailAddressForClaimant() {
    HtmlTag htmlTag = TravelInWorkForm.generateForm(
        travelInWorkTestData.tiwWhoIsSelfEmployedAndNoClaimantEmailAddressClaimRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelInWorkWhoIsSelfEmployedWithNoClaimantEmailAddress.html"),
        htmlTag.render());
  }

  @Test
  void generateSuccessfulFormWithSupportWorkerWithExistingPayee() {
    HtmlTag htmlTag = TravelInWorkForm.
        generateForm(travelInWorkTestData.tiwWhoIsEmployedAndHasExistingPayeeClaimRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelInWorkWithExistingPayee.html"),
        htmlTag.render());
  }

  @Test
  void generateSuccessfulFormWithSupportWorkerWithExistingPayeeOldDataModel() {
    HtmlTag htmlTag = TravelInWorkForm.
        generateForm(travelInWorkTestData.tiwWhoIsEmployedAndHasExistingPayeeClaimRequestOldDataModel);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelInWorkWithExistingPayeeOldDataModel.html"),
        htmlTag.render());
  }
}