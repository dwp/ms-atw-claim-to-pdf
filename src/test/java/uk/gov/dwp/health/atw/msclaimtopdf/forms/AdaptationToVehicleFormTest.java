package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import j2html.tags.specialized.HtmlTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.health.atw.msclaimtopdf.testData.AdaptationToVehicleTestData;
import uk.gov.dwp.health.atw.msclaimtopdf.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.RESPONSE_HTML_FILES_LOCATION;

public class AdaptationToVehicleFormTest {

  @Test
  @DisplayName("generate html for new adaptation to vehicle form")
  void generateSuccessfulForm() {
    HtmlTag htmlTag = AdaptationToVehicleForm.
        generateForm(AdaptationToVehicleTestData.validAdaptationToVehicleSubmitRequest);
    assertEquals(TestUtils.getFileAsString(
            RESPONSE_HTML_FILES_LOCATION + "/AdaptationToVehicle.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate html for new adaptation to vehicle form with no claimant email address")
  void generateSuccessfulFormWithNoEmailAddress() {
    HtmlTag htmlTag = AdaptationToVehicleForm.
        generateForm(AdaptationToVehicleTestData.validAdaptationToVehicleWithNoClaimantEmailAddressSubmitRequest);
    assertEquals(TestUtils.getFileAsString(
            RESPONSE_HTML_FILES_LOCATION + "/AdaptationToVehicleWithNoClaimantEmailAddress.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate html for new adaptation to vehicle form with optional fields skipped")
  void generateSuccessfulFormWithSkippingOptionalField() {
    HtmlTag htmlTag = AdaptationToVehicleForm
        .generateForm(
            AdaptationToVehicleTestData.adaptationToVehicleOptionalFieldSkipped);
    assertEquals(TestUtils.getFileAsString(
            RESPONSE_HTML_FILES_LOCATION + "/AdaptationToVehicleOptionalFieldSkipped.html"),
        htmlTag.render());
  }
}
