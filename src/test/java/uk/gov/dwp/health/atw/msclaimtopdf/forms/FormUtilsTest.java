package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import j2html.tags.specialized.DdTag;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.DlTag;
import j2html.tags.specialized.HeadTag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.enums.ClaimType;
import uk.gov.dwp.health.atw.msclaimtopdf.testData.FormUtilsTestData;
import j2html.Config;
import uk.gov.dwp.health.atw.msclaimtopdf.testData.TestData;
import java.time.LocalDateTime;

@SpringBootTest(classes = FormUtils.class)
class FormUtilsTest {

    @BeforeEach
    void setUp(){
        Config.closeEmptyTags = true;
    }

    @Test
    void getHeadTagSuccessful() {
        HeadTag testHead = FormUtils.getHeadTag("Test Head Tag");
        assertEquals(FormUtilsTestData.successfulHeadData("Test Head Tag"), testHead.render());
    }

    @Test
    void getFormHeaderSuccessful() {
        DivTag formHeader = FormUtils.getFormHeader(ClaimType.EQUIPMENT_OR_ADAPTATION.getLabel());
        assertEquals(FormUtilsTestData.successfulHeader(ClaimType.EQUIPMENT_OR_ADAPTATION.getLabel()), formHeader.render());
    }

    @Test
    void getAddress() {
        DdTag address = FormUtils.getAddress(TestData.address);
        assertEquals(FormUtilsTestData.validAddress(), address.render());
    }

    @Test
    void getAddressWithEscapeCharacters() {
        DdTag address = FormUtils.getAddress(TestData.addressWithEscapeCharacter);
        assertEquals(FormUtilsTestData.validAddressWithEscapeCharacters(), address.render());
    }

    @Test
    void divTagWithDtAndDdTagsWithStrings() {
        DivTag divTag = FormUtils.divTagWithDtAndDdTags("dtTagValue", "ddTagValue");
        assertEquals(FormUtilsTestData.validDivTagWithStrings("dtTagValue", "ddTagValue"), divTag.render());
    }

    @Test
    void divTagWithDtAndDdTagsWithStringAndDdTag() {
        DivTag divTag = FormUtils.divTagWithDtAndDdTags("dtTagValue", FormUtilsTestData.getDdTag());
        assertEquals(FormUtilsTestData.validDivTagWithStringAndDdTag("dtTagValue", FormUtilsTestData.getDdTag()), divTag.render());
    }

    @Test
    @DisplayName("get total cost successfully")
    void getTotalCost() {
        DivTag divTag = FormUtils.getTotalCost(200.00, "Total Cost");
        assertEquals(FormUtilsTestData.validTotalCost(200.00, "Total Cost"), divTag.render());
    }

    @Test
    @DisplayName("get total cost with null value returns empty string")
    void getTotalCostWithNullValue() {
        DivTag divTag = FormUtils.getTotalCost(null, "Total Cost");
        assertEquals(FormUtilsTestData.totalCostWithNullValue("Total Cost"), divTag.render());
    }

    @Test
    void getReceiptAttached() {
        DivTag divTag = FormUtils.getReceiptAttached(TestData.evidences);
        String receiptAttached = TestData.evidences.isEmpty() ? "No" : "Yes";
        assertEquals(FormUtilsTestData.validReciptAttached(receiptAttached), divTag.render());
    }

    @Test
    void getPayeeDetails() {
        DlTag dlTag = FormUtils.getPayeeDetailsNameAndEmail(TestData.payee);
        assertEquals(FormUtilsTestData.validPayeeDetails(TestData.payee), dlTag.render());
    }

    @Test
    void getPayeeDetailsForExistingPayee() {
        DlTag dlTag = FormUtils.getPayeeDetailsNameAndEmail(TestData.existingPayee);
        assertEquals(FormUtilsTestData.validPayeeDetails(TestData.existingPayee), dlTag.render());
    }

    @Test
    void getBasicPayeeDetails() {
        DlTag dlTag = FormUtils.getPayeeDetails(TestData.payee);
        assertEquals(FormUtilsTestData.validPayee(TestData.payee), dlTag.render());
    }

    @Test
    void getWorkplaceContactDetails() {
        DlTag dlTag = FormUtils.getWorkplaceContactDetails(TestData.employedWorkplaceContactResponse, false, true);
        assertEquals(FormUtilsTestData.validWorkplaceContactDetails(TestData.employedWorkplaceContactResponse), dlTag.render());
    }

    @Test
    void getWorkplaceContactDetailsForSelfEmployed() {
        DlTag dlTag = FormUtils.getWorkplaceContactDetails(TestData.selfEmployedWorkplaceContactResponse, false, true);
        assertEquals(FormUtilsTestData.validWorkplaceContactDetails(TestData.selfEmployedWorkplaceContactResponse), dlTag.render());
    }

    @Test
    void getWorkplaceContactDetailsForNoEmploymentStatus() {
        DlTag dlTag = FormUtils.getWorkplaceContactDetails(TestData.noEmploymentStatusWorkplaceContactResponse, false, true);
        assertEquals(FormUtilsTestData.validWorkplaceContactDetails(TestData.noEmploymentStatusWorkplaceContactResponse), dlTag.render());
    }

    @Test
    void getClaimDeclaration() {
        DlTag dlTag = FormUtils.getClaimDeclaration(FormUtilsTestData.DECLARATION_VERSION,
                                TestData.employedWorkplaceContactResponse.getDeclarationVersion(),
                                TestData.localDate,
                                TestData.employedWorkplaceContactResponse.getUpdatedOn(),
                                true,
                                false);

        assertEquals(FormUtilsTestData.validClaimDeclaration(), dlTag.render());
    }

    @Test
    void getNullFormattedDate() {
        assertEquals("", FormUtils.getFormattedDate(null));
    }

    @Test
    void getValidFormattedDate() {
        Assertions.assertNotNull(FormUtils.getFormattedDate(LocalDateTime.now()));
        Assertions.assertNotEquals("",FormUtils.getFormattedDate(LocalDateTime.now()));

    }
}
