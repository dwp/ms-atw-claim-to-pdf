package uk.gov.dwp.health.atw.msclaimtopdf.testData;

import static j2html.TagCreator.dd;
import j2html.tags.specialized.DdTag;
import java.text.DecimalFormat;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Payee;
import uk.gov.dwp.health.atw.msclaimtopdf.models.PayeeDetails;
import uk.gov.dwp.health.atw.msclaimtopdf.models.WorkplaceContact;
import uk.gov.dwp.health.atw.msclaimtopdf.utils.TestUtils;

public class FormUtilsTestData {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public static final Double DECLARATION_VERSION = 2.3;

    public static DdTag getDdTag() {
        return  dd("TBC").withClass("govuk-summary-list__value");
    }

    public static String successfulHeadData(String header){
        return "<head><title>Access to Work - " +header+ "</title>" +
                "<meta charset=\"utf-8\"/>" +
                "<style>" + TestUtils.getFileAsString("styles/css/govUk.css") +"</style></head>";
    }

    public static String successfulHeader(String formTitle) {
        return "<div class=\"govuk-summary-list__row\"><img src=\"" +
            TestUtils.getFileAsString("styles/logo/imageBase64.txt") +
            "\" class=\"dwp_logo\"/>" +
            "<h1 class=\"govuk-heading-l\">" +
            "<p>Access to Work<br/>" + formTitle + "</p>" +
            "</h1>" +
            "</div>";
    }

    public static String validAddress() {
        return "<dd class=\"govuk-summary-list__value\">" +
                "<p>THE COTTAGE 1<br/>ST. MARYS ISLAND<br/>WHITLEY BAY<br/>WHITLEY BAY<br/>NE26 4RS</p>" +
                "</dd>";
    }

    public static String validAddressWithEscapeCharacters() {
        return "<dd class=\"govuk-summary-list__value\">" +
            "<p>THE &amp; CO£TT&apos;AGE 1<br/>ST. MAR&amp;YS£ ISLAND<br/>WHIT&amp;LEY B£AY<br/>WHI&amp;TLEY BA£Y<br/>NE26&amp; 4£RS</p>" +
            "</dd>";
    }

    public static String validDivTagWithStrings(String dtTagValue, String ddTagValue) {
        return "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">"+dtTagValue+"</dt>" +
                "<dd class=\"govuk-summary-list__value\">"+ddTagValue+"</dd>" +
                "</div>";
    }

    public static String validDivTagWithStringAndDdTag(String dtTagValue, DdTag ddTag) {
        return "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">"+dtTagValue+"</dt>" +
                ddTag +
                "</div>";
    }

    public static String validTotalCost(double cost, String costDescription) {
        return "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">"+costDescription+"</dt>" +
                "<dd class=\"govuk-summary-list__value\">£"+DECIMAL_FORMAT.format(cost)+"</dd>" +
                "</div>";
    }

    public static String totalCostWithNullValue(String costDescription) {
        return "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">"+costDescription+"</dt>" +
                "<dd class=\"govuk-summary-list__value\"></dd>" +
                "</div>";
    }

    public static String validReciptAttached(String receiptAttached) {
       return "<div class=\"govuk-summary-list__row\">" +
               "<dt class=\"govuk-summary-list__key\">Receipt attached</dt>" +
               "<dd class=\"govuk-summary-list__value\">"+receiptAttached+"</dd>" +
               "</div>";
    }

    public static String validPayeeDetails(PayeeDetails payeeDetails) {
        return "<dl class=\"govuk-summary-list\">" +
                "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">Full name</dt>" +
                "<dd class=\"govuk-summary-list__value\">"+payeeDetails.getFullName()+"</dd>" +
                "</div>" +
                "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">Email address</dt>" +
                "<dd class=\"govuk-summary-list__value\">"+payeeDetails.getEmailAddress()+"</dd>" +
                "</div>" +
                "</dl>";
    }

    public static String validPayee(Payee payee) {
        return "<dl class=\"govuk-summary-list\">" +
            "<div class=\"govuk-summary-list__row\">" +
            "<dt class=\"govuk-summary-list__key\">Full name</dt>" +
            "<dd class=\"govuk-summary-list__value\">"+payee.getDetails().getFullName()+"</dd>" +
            "</div>" +
            "<div class=\"govuk-summary-list__row\">" +
            "<dt class=\"govuk-summary-list__key\">Email address</dt>" +
            "<dd class=\"govuk-summary-list__value\">"+payee.getDetails().getEmailAddress()+"</dd>" +
            "</div>" +
            "<div class=\"govuk-summary-list__row\">" +
            "<dt class=\"govuk-summary-list__key\">Address</dt>" + validAddress() +
            "</div>" +
            "<div class=\"govuk-summary-list__row\">" +
            "<dt class=\"govuk-summary-list__key\">Name on bank account</dt>" +
            "<dd class=\"govuk-summary-list__value\">"+payee.getBankDetails().getAccountHolderName()+"</dd>" +
            "</div>" +
            "<div class=\"govuk-summary-list__row\">" +
            "<dt class=\"govuk-summary-list__key\">Sort code</dt>" +
            "<dd class=\"govuk-summary-list__value\">"+payee.getBankDetails().getSortCode()+"</dd>" +
            "</div>" +
            "<div class=\"govuk-summary-list__row\">" +
            "<dt class=\"govuk-summary-list__key\">Account number</dt>" +
            "<dd class=\"govuk-summary-list__value\">"+payee.getBankDetails().getAccountNumber()+"</dd>" +
            "</div>" +
            "</dl>";
    }

    public static String validWorkplaceContactDetails(WorkplaceContact workplaceContact) {
        return "<dl class=\"govuk-summary-list\">" +
                "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">Name</dt>" +
                "<dd class=\"govuk-summary-list__value\">"+ workplaceContact.getFullName()+"</dd>" +
                "</div>" +
                "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">Email</dt>" +
                "<dd class=\"govuk-summary-list__value\">"
                + workplaceContact.getEmailAddress()+"</dd>" +
                "</div>" +
                "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">Name of company or organisation they " +
                "work for</dt>" +
                "<dd class=\"govuk-summary-list__value\">"
                + workplaceContact.getOrganisation()+"</dd>" +
                "</div>" +
                "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">Organisation address</dt>" +
                "<dd class=\"govuk-summary-list__value\">" +
                "<p>THE &amp; CO£TT&apos;AGE 1<br/>ST. MAR&amp;YS£ ISLAND<br/>WHIT&amp;LEY B£AY<br/>WHI&amp;TLEY BA£Y<br/>NE26&amp; 4£RS</p>" +
                "</dd>" +
                "</div>" +
                "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">Workplace contact Job title</dt>" +
                "<dd class=\"govuk-summary-list__value\">"+ workplaceContact.getJobTitle()+"</dd>" +
                "</div>" +
                "</dl>";
    }

    public static String validClaimDeclaration() {
        return "<dl class=\"govuk-summary-list\">" +
                "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">Claimant declaration</dt>" +
                "<dd class=\"govuk-summary-list__value\">Yes</dd>" +
                "</div>" +
                "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">Claim sent to workplace contact</dt>" +
                "<dd class=\"govuk-summary-list__value\">" + TestData.localDateFormatted + "</dd>" +
                "</div>" +
                "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">Workplace contact declaration</dt>" +
                "<dd class=\"govuk-summary-list__value\">Yes</dd>" +
                "</div>" +
                "<div class=\"govuk-summary-list__row\">" +
                "<dt class=\"govuk-summary-list__key\">Claim submission date</dt>" +
                "<dd class=\"govuk-summary-list__value\">" + TestData.workplaceContactLocalDateFormatted + "</dd>" +
                "</div>" +
                "</dl>";
    }
}
