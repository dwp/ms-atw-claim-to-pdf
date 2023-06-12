package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import j2html.tags.specialized.HtmlTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.dwp.health.atw.msclaimtopdf.testData.NewPayeeDetailsTestData;
import uk.gov.dwp.health.atw.msclaimtopdf.utils.TestUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData.RESPONSE_HTML_FILES_LOCATION;

@SpringBootTest(classes = NewPayeeDetailsForm.class)
public class NewPayeeDetailsFormTest {

    @Test
    @DisplayName("generate html for new payee details form successful")
    void generateSuccessfulForm() {
        HtmlTag htmlTag = NewPayeeDetailsForm.
                generateForm(NewPayeeDetailsTestData.newPayeeDetailsRequest);
        assertEquals(TestUtils.getFileAsString(RESPONSE_HTML_FILES_LOCATION + "/NewPayeeDetails.html"),
                htmlTag.render());
    }

    @Test
    @DisplayName("generate html for new payee details form with optional field successful")
    void generateSuccessfulFormWithSkippingOptionalField() {
        HtmlTag htmlTag = NewPayeeDetailsForm
                .generateForm(NewPayeeDetailsTestData.newPayeeDetailsRequestWithOptionalFields);
        assertEquals(TestUtils.getFileAsString(
                        RESPONSE_HTML_FILES_LOCATION + "/NewPayeeDetailsWithOptionalFieldsSkipped.html"),
                htmlTag.render());
    }
}
