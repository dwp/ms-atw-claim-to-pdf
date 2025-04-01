package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import static j2html.TagCreator.br;
import static j2html.TagCreator.dd;
import static j2html.TagCreator.div;
import static j2html.TagCreator.dt;
import static j2html.TagCreator.fileAsString;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.iff;
import static j2html.TagCreator.iffElse;
import static j2html.TagCreator.img;
import static j2html.TagCreator.join;
import static j2html.TagCreator.meta;
import static j2html.TagCreator.p;
import static j2html.TagCreator.style;
import static j2html.TagCreator.title;
import static org.apache.commons.text.StringEscapeUtils.escapeXml11;

import j2html.tags.specialized.DdTag;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.DlTag;
import j2html.tags.specialized.HeadTag;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.util.StringUtils;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Address;
import uk.gov.dwp.health.atw.msclaimtopdf.models.BankDetails;
import uk.gov.dwp.health.atw.msclaimtopdf.models.ContactInformation;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Evidence;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Payee;
import uk.gov.dwp.health.atw.msclaimtopdf.models.PayeeDetails;
import uk.gov.dwp.health.atw.msclaimtopdf.models.WorkplaceContact;

public class FormUtils {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
  private static final String DATE_FORMATTER = "dd MMMM yyyy";

  static HeadTag getHeadTag(String formName) {
    return head(
        title("Access to Work - " + formName),
        meta().withCharset("utf-8"),
        style(fileAsString("styles/css/govUk.css"))
    );
  }

  static DivTag getFormHeader(String formTitle) {
    return div(
        img().withSrc(fileAsString("styles/logo/imageBase64.txt").toString())
            .withClass("dwp_logo"),
        h1(p(
                join("Access to Work"),
                br(),
                join(formTitle)
            )
        ).withClass("govuk-heading-l")
    ).withClass("govuk-summary-list__row");
  }

  static DdTag getAddress(Address address) {
    DdTag ddTag = new DdTag();

    if (address != null) {
      ddTag.with(
          p(
              join((escapeXml11(address.getAddress1()))),
              br(),
              join(
                  iff(address.getAddress2() != null,
                      escapeXml11(address.getAddress2()) + "<br/>")
              ),
              join(escapeXml11(address.getAddress3())),
              br(),
              join(
                  iff(address.getAddress4() != null,
                      escapeXml11(address.getAddress4()) + "<br/>")
              ),
              join(escapeXml11(address.getPostcode()))
          )
      );
    }
    return ddTag.withClass("govuk-summary-list__value");
  }

  static String getFormattedDate(LocalDateTime dateTime) {
    if (dateTime != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
      return dateTime.format(formatter);
    }
    return "";
  }

  static DivTag divTagWithDtAndDdTags(String dtValue, String ddValue) {
    return div(
        dt(dtValue).withClass("govuk-summary-list__key"),
        dd(ddValue != null ? ddValue : "")
            .withClass("govuk-summary-list__value")
    ).withClass("govuk-summary-list__row");
  }

  static DivTag divTagWithDtAndDdTags(String dtValue, DdTag ddValue) {
    return div(
        dt(dtValue).withClass("govuk-summary-list__key"),
        ddValue.withClass("govuk-summary-list__value")
    ).withClass("govuk-summary-list__row");
  }

  static DivTag getTotalCost(Double cost, String dtValue) {
    String costValue = cost != null ? "£" + DECIMAL_FORMAT.format(cost) : "";
    return divTagWithDtAndDdTags(dtValue, costValue);
  }

  static DivTag getReceiptAttached(List<Evidence> evidence) {
    String receiptAttached = evidence == null || evidence.isEmpty() ? "No" : "Yes";
    return divTagWithDtAndDdTags("Receipt attached", receiptAttached);
  }

  static DlTag getPayeeDetailsNameAndEmail(Payee payee) {
    DlTag dlTag = new DlTag();
    BankDetails bankDetails = payee.getBankDetails();
    dlTag.with(
        divTagWithDtAndDdTags("Full name", payee.getDetails().getFullName()),
        divTagWithDtAndDdTags("Email address", payee.getDetails().getEmailAddress())
    );
    if (bankDetails != null) {
      dlTag.with(divTagWithDtAndDdTags("Account number", bankDetails.getAccountNumber()));
    }

    return dlTag.withClass("govuk-summary-list");
  }

  static DlTag getWorkplaceContactDetails(WorkplaceContact workplaceContact,
                                          boolean showEmploymentStatus,
                                          boolean showCompanyDetailsOfWhoCanConfirm) {

    if (workplaceContact.getEmploymentStatus() != null
        && workplaceContact.getEmploymentStatus().equalsIgnoreCase("selfemployed")) {
      workplaceContact.setEmploymentStatus("Self-employed");
    }

    DlTag dlTag = new DlTag();
    dlTag.with(
        iff(showEmploymentStatus,
            divTagWithDtAndDdTags("Employment status",
                workplaceContact.getEmploymentStatus() != null
                        ? StringUtils.capitalize(
                            workplaceContact.getEmploymentStatus().toLowerCase()) : "")
        ),
        iff(showCompanyDetailsOfWhoCanConfirm,
            divTagWithDtAndDdTags("Name", workplaceContact.getFullName())
        ),
        iff(showCompanyDetailsOfWhoCanConfirm,
            divTagWithDtAndDdTags("Email", workplaceContact.getEmailAddress())),
        iff(showCompanyDetailsOfWhoCanConfirm,
            divTagWithDtAndDdTags("Name of company or organisation they work for",
                workplaceContact.getOrganisation())),
        iff(showCompanyDetailsOfWhoCanConfirm,
            divTagWithDtAndDdTags("Organisation address",
                getAddress(workplaceContact.getAddress()))),
        iff(showCompanyDetailsOfWhoCanConfirm,
            divTagWithDtAndDdTags("Workplace contact Job title",
                    workplaceContact.getJobTitle()))
    );
    return dlTag.withClass("govuk-summary-list");
  }

  static DlTag getClaimDeclaration(Double declarationVersion,
                                   Double workplaceContactDeclarationVersion,
                                   LocalDateTime createdDate,
                                   LocalDateTime workplaceContactUpdatedOn,
                                   boolean workplaceContact,
                                   boolean selfEmployedEmploymentStatus) {
    String claimantDeclaration = declarationVersion == null ? "No" : "Yes";
    String workplaceContactDeclaration = workplaceContactDeclarationVersion == null ? "No" : "Yes";

    DlTag dlTag = new DlTag();
    dlTag.with(
        divTagWithDtAndDdTags("Claimant declaration", claimantDeclaration),
        iff(workplaceContact, divTagWithDtAndDdTags("Claim sent to workplace contact",
            getFormattedDate(createdDate))),
        iff(workplaceContact,
            divTagWithDtAndDdTags("Workplace contact declaration",
                workplaceContactDeclaration)
        ),
        divTagWithDtAndDdTags("Claim submission date",
            iffElse(selfEmployedEmploymentStatus, getFormattedDate(createdDate),
                getFormattedDate(workplaceContactUpdatedOn))));
    return dlTag.withClass("govuk-summary-list");
  }

  static DlTag getPayeeDetails(Payee payee) {

    PayeeDetails payeeDetails = payee.getDetails();
    BankDetails bankDetails = payee.getBankDetails();

    DlTag dlTag = new DlTag();
    dlTag.with(
            divTagWithDtAndDdTags("Full name", payeeDetails.getFullName()),
            divTagWithDtAndDdTags("Email address", payeeDetails.getEmailAddress()),
            divTagWithDtAndDdTags("Address", getAddress(payee.getAddress())),
            divTagWithDtAndDdTags("Name on bank account", bankDetails.getAccountHolderName()),
            divTagWithDtAndDdTags("Sort code", bankDetails.getSortCode()),
            divTagWithDtAndDdTags("Account number", bankDetails.getAccountNumber())
    );

    return dlTag.withClass("govuk-summary-list");
  }

  static DlTag getClaimantDetails(ContactInformation contactInformationRequest,
                                  String accessToWorkNumber) {

    String emailAddress = contactInformationRequest.getEmailAddress();
    String homeNumber = contactInformationRequest.getHomeNumber();
    String mobileNumber = contactInformationRequest.getMobileNumber();

    DlTag dlTag = new DlTag();
    dlTag.with(
        divTagWithDtAndDdTags("Full name", contactInformationRequest.getForename()
            + " " + contactInformationRequest.getSurname()),
        divTagWithDtAndDdTags("Access to Work number", accessToWorkNumber),
        iff(emailAddress != null, divTagWithDtAndDdTags("Email address",
            emailAddress)),
        iff(homeNumber != null, divTagWithDtAndDdTags("Home number", homeNumber)),
        iff(mobileNumber != null, divTagWithDtAndDdTags("Mobile number", mobileNumber)),
        divTagWithDtAndDdTags("Home address",
            getAddress(contactInformationRequest.getAddress()))
    );

    return dlTag.withClass("govuk-summary-list");
  }

}