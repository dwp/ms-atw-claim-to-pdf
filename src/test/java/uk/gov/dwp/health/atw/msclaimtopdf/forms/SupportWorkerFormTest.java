package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import j2html.tags.specialized.HtmlTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.dwp.health.atw.msclaimtopdf.testData.EquipmentOrAdaptationTestData;
import uk.gov.dwp.health.atw.msclaimtopdf.testData.SupportWorkerTestData;
import uk.gov.dwp.health.atw.msclaimtopdf.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.RESPONSE_HTML_FILES_LOCATION;

@SpringBootTest(classes = SupportWorkerForm.class)
class SupportWorkerFormTest {

    @Test
    void generateSuccessfulForm() {
        HtmlTag htmlTag = SupportWorkerForm.
                generateForm(SupportWorkerTestData.supportWorkerWhoIsEmployedClaimRequest);
        assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/SupportWorker.html"),
                htmlTag.render());
    }

    @Test
    void generateSuccessfulFormWithSupportHoursToTwoDecimal() {
        HtmlTag htmlTag = SupportWorkerForm.
                generateForm(SupportWorkerTestData.supportWorkerWhoIsEmployedWithSupportHoursToTwoDecimalPlacesClaimRequest);
        assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/SupportWorkerWithSupportHoursToTwoDecimalPlaces.html"),
                htmlTag.render());
    }

    @Test
    @DisplayName("generate html for new support worker form with no claimant email address")
    void generateSuccessfulFormWithNoClaimantEmailAddress() {
        HtmlTag htmlTag = SupportWorkerForm.
                generateForm(SupportWorkerTestData.supportWorkerWithNoClaimantEmailAddressClaimRequest);
        assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/SupportWorkerWithNoClaimantEmailAddress.html"),
                htmlTag.render());
    }

    @Test
    void generateSuccessfulFormWithSkippingOptionalField() {
        HtmlTag htmlTag = SupportWorkerForm
                .generateForm(
                        SupportWorkerTestData.supportWorkerClaimRequestWithOptionalFieldsMissing);
        assertEquals(TestUtils.getFileAsString(
                        RESPONSE_HTML_FILES_LOCATION + "/SupportWorkerOptionalFieldSkipped.html"),
                htmlTag.render());
    }

    @Test
    void generateSuccessfulFormWithHoursOfSupportClaims() {
        HtmlTag htmlTag = SupportWorkerForm
                .generateForm(
                        SupportWorkerTestData.supportWorkerClaimRequestWithHoursOfSupportClaims);
        assertEquals(TestUtils.getFileAsString(
                        RESPONSE_HTML_FILES_LOCATION + "/SupportWorkerWithHoursOfSupportClaims.html"),
                htmlTag.render());
    }
}