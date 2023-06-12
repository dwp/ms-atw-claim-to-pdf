package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.RESPONSE_HTML_FILES_LOCATION;

import j2html.tags.specialized.HtmlTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.dwp.health.atw.msclaimtopdf.testData.EquipmentOrAdaptationTestData;
import uk.gov.dwp.health.atw.msclaimtopdf.utils.TestUtils;

@SpringBootTest(classes = EquipmentOrAdaptationForm.class)
public class EquipmentOrAdaptationFormTest {

    @Test
    void generateSuccessfulForm() {
        HtmlTag htmlTag = EquipmentOrAdaptationForm.
                generateForm(EquipmentOrAdaptationTestData.validEquipmentOrAdaptationSubmitRequest);
        assertEquals(TestUtils.getFileAsString(
                RESPONSE_HTML_FILES_LOCATION + "/EquipmentOrAdaptation.html"),
                htmlTag.render());
    }

    @Test
    @DisplayName("generate html for new equipment or adaptation form with no claimant email address")
    void generateSuccessfulFormWithNoEmailAddress() {
        HtmlTag htmlTag = EquipmentOrAdaptationForm.
                generateForm(EquipmentOrAdaptationTestData.validEquipmentOrAdaptationWithNoClaimantEmailAddressSubmitRequest);
        assertEquals(TestUtils.getFileAsString(
                RESPONSE_HTML_FILES_LOCATION + "/EquipmentOrAdaptationWithNoClaimantEmailAddress.html"),
                htmlTag.render());
    }

    @Test
    void generateSuccessfulFormWithSkippingOptionalField() {
        HtmlTag htmlTag = EquipmentOrAdaptationForm
                .generateForm(
                        EquipmentOrAdaptationTestData.equipmentOrAdaptationOptionalFieldSkipped);
        assertEquals(TestUtils.getFileAsString(
                        RESPONSE_HTML_FILES_LOCATION + "/EquipmentOrAdaptationOptionalFieldSkipped.html"),
                htmlTag.render());
    }
}
