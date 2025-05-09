package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.RESPONSE_HTML_FILES_LOCATION;

import j2html.tags.specialized.HtmlTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.dwp.health.atw.msclaimtopdf.testData.TravelToWorkTestData;
import uk.gov.dwp.health.atw.msclaimtopdf.utils.TestUtils;

@SpringBootTest(classes = TravelToWorkForm.class)
class TravelToWorkFormTest {

  TravelToWorkTestData travelToWorkTestData;

  @BeforeEach
  void setup() {
    travelToWorkTestData = new TravelToWorkTestData();
  }

  @Test
  @DisplayName("generate form for client who has employed employment status using taxis")
  void generateFormWithTaxiTravelTypeAndEmployedEmploymentStatus() {
    HtmlTag htmlTag = TravelToWorkForm.generateForm(
        travelToWorkTestData.taxiTravelTypeWhoIsEmployedTWClaimRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelToWork.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has employed employment status using taxis with no email address for the claimant")
  void generateFormWithTaxiTravelTypeAndEmployedEmploymentStatusWithNoEmailAddressForClaimant() {
    HtmlTag htmlTag = TravelToWorkForm.generateForm(
        travelToWorkTestData.taxiTravelTypeWhoIsEmployedTWAndNoClaimantEmailAddressClaimRequest);
    assertEquals(TestUtils.getFileAsString(
        RESPONSE_HTML_FILES_LOCATION + "/TravelToWorkWithNoClaimantEmailAddress.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has employed employment status using taxis with optional fields missing")
  void generateFormWithSkippedOptionalField() {
    HtmlTag htmlTag = TravelToWorkForm.generateForm(
        travelToWorkTestData.taxiTravelToWorkOptionalFieldSkipped);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelToWorkOptionalFieldSkipped.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has employed employment status using lifts in miles")
  void generateFormWithLiftTravelTypeInMilesAndEmployedEmploymentStatus() {
    HtmlTag htmlTag = TravelToWorkForm.generateForm(
        travelToWorkTestData.liftWithMileageTravelTypeWhoIsEmployedTWClaimRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelToWorkWithLiftUsingMilesForEmployed.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has employed employment status using lifts with journeys")
  void generateFormWithLiftTravelTypeWithJourneyAndEmployedEmploymentStatus() {
    HtmlTag htmlTag = TravelToWorkForm.generateForm(
        travelToWorkTestData.liftWithJourneysTravelTypeWhoIsEmployedTWClaimRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelToWorkWithLiftUsingJourneysForEmployed.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has self employed employment status using taxis")
  void generateFormWithTaxiTravelTypeAndSelfEmployedEmploymentStatus() {
    HtmlTag htmlTag = TravelToWorkForm.generateForm(
        travelToWorkTestData.taxiTravelTypeWhoIsSelfEmployedTWClaimRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelToWorkWithTaxiWhoIsSelfEmployed.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has self employed employment status using taxis with no email address for the claimant")
  void generateFormWithTaxiTravelTypeAndSelfEmployedEmploymentStatusWithNoEmailAddressForClaimant() {
    HtmlTag htmlTag = TravelToWorkForm.generateForm(
        travelToWorkTestData.taxiTravelTypeWhoIsSelfEmployedTWAndNoClaimantEmailAddressClaimRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelToWorkWithTaxiWhoIsSelfEmployedWithNoClaimantEmailAddress.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has self employed employment status using lifts with miles")
  void generateFormWithLiftTravelTypeInMilesAndSelfEmployedEmploymentStatus() {
    HtmlTag htmlTag = TravelToWorkForm.generateForm(
        travelToWorkTestData.liftWithMileageTravelTypeWhoIsSelfEmployedTWClaimRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelToWorkWithLiftUsingMilesWhoIsSelfEmployed.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has self employed employment status using lifts with journeys")
  void generateFormWithLiftTravelTypeInJourneysAndSelfEmployedEmploymentStatus() {
    HtmlTag htmlTag = TravelToWorkForm.generateForm(
        travelToWorkTestData.liftWithJourneysTravelTypeWhoIsSelfEmployedTWClaimRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelToWorkWithLiftUsingJourneysWhoIsSelfEmployed.html"),
        htmlTag.render());
  }

  @Test
  @DisplayName("generate form for client who has self employed employment status using taxis and " +
      "with one journey")
  void generateFormWithTaxiTravelTypeWithOneJourneyAndSelfEmployedEmploymentStatus() {
    HtmlTag htmlTag = TravelToWorkForm.generateForm(
        travelToWorkTestData.taxiTravelTypeWhoIsSelfEmployedWithOneJourneyTWClaimRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelToWorkWithTaxiWhoIsSelfEmployedOneJourney.html"),
        htmlTag.render());
  }

  @Test
  void generateSuccessfulFormWithTravelToWorkWithExistingPayee() {
    HtmlTag htmlTag = TravelToWorkForm.
        generateForm(travelToWorkTestData.taxiTravelTypeWhoIsEmployedTWWithExistingPayeeClaimRequest);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelToWorkWithExistingPayee.html"),
        htmlTag.render());
  }

  @Test
  void generateSuccessfulFormWithTravelToWorkWithExistingPayeeOldDataModel() {
    HtmlTag htmlTag = TravelToWorkForm.
        generateForm(travelToWorkTestData.taxiTravelTypeWhoIsEmployedTWWithExistingPayeeClaimRequestOldDataModel);
    assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/TravelToWorkWithExistingPayeeOldDataModel.html"),
        htmlTag.render());
  }
}