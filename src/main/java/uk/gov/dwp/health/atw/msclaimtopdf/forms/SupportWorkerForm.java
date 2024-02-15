package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import static j2html.TagCreator.body;
import static j2html.TagCreator.br;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.html;
import static j2html.TagCreator.iff;
import static j2html.TagCreator.iffElse;
import static j2html.TagCreator.join;
import static j2html.TagCreator.p;
import static org.apache.commons.text.StringEscapeUtils.escapeXml11;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.divTagWithDtAndDdTags;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getClaimDeclaration;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getFormHeader;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getHeadTag;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getPayeeDetailsNameAndEmail;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getReceiptAttached;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getTotalCost;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getWorkplaceContactDetails;

import j2html.Config;
import j2html.tags.specialized.BodyTag;
import j2html.tags.specialized.DdTag;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.DlTag;
import j2html.tags.specialized.HtmlTag;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Claimant;
import uk.gov.dwp.health.atw.msclaimtopdf.models.SupportWorker;
import uk.gov.dwp.health.atw.msclaimtopdf.models.SupportWorkerClaim;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ClaimRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.SupportWorkerClaimRequest;

@Slf4j
public class SupportWorkerForm {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

  public static HtmlTag generateForm(ClaimRequest claimRequest) {
    Config.closeEmptyTags = true;

    return html(
        getHeadTag("Support Worker"),
        generateFormBody(claimRequest)
    );
  }

  private static BodyTag generateFormBody(ClaimRequest claimRequest) {
    SupportWorkerClaimRequest supportWorkerClaimRequest =
        (SupportWorkerClaimRequest) claimRequest;

    return body(
        getFormHeader("Claim for support worker costs"),
        h2("Claimant’s details").withClass("govuk-heading-m"),
        getClaimantDetails(supportWorkerClaimRequest),
        h2("Claim information").withClass("govuk-heading-m"),
        getClaimInformation(supportWorkerClaimRequest),
        h2("Payee details").withClass("govuk-heading-m"),
        getPayeeDetailsNameAndEmail(supportWorkerClaimRequest.getPayee().getDetails()),
        h2("Details of the person or company who can confirm the support costs")
            .withClass("govuk-heading-m"),
        getWorkplaceContactDetails(supportWorkerClaimRequest.getWorkplaceContact(), false, true),
        h2("Claim declaration").withClass("govuk-heading-m"),
        getClaimDeclaration(supportWorkerClaimRequest.getDeclarationVersion(),
            supportWorkerClaimRequest.getWorkplaceContact().getDeclarationVersion(),
            supportWorkerClaimRequest.getCreatedDate(),
            supportWorkerClaimRequest.getWorkplaceContact().getUpdatedOn(),
            true,
            false)
    );
  }

  private static DlTag getClaimantDetails(SupportWorkerClaimRequest supportWorkerClaimRequest) {

    Claimant claimantDetails = supportWorkerClaimRequest.getClaimant();

    DlTag dlTag = new DlTag();
    dlTag.with(
        divTagWithDtAndDdTags("Full name", claimantDetails.getForename()
            + " " + claimantDetails.getSurname()),
        divTagWithDtAndDdTags("Access to Work number", supportWorkerClaimRequest.getAtwNumber()),
        iff(claimantDetails.getEmailAddress() != null,
            divTagWithDtAndDdTags("Email address", claimantDetails.getEmailAddress())),
        divTagWithDtAndDdTags("Claim type", supportWorkerClaimRequest.getClaimType()
            .getLabel()),
        divTagWithDtAndDdTags("Company or organisation name", claimantDetails.getCompany())
    );

    return dlTag.withClass("govuk-summary-list");
  }

  private static DlTag getClaimInformation(SupportWorkerClaimRequest supportWorkerClaimRequest) {
    Map<String, SupportWorker> supportWorkerClaims = supportWorkerClaimRequest.getClaim();
    DlTag dlTag = new DlTag();

    String nameOfSupport = supportWorkerClaimRequest.getNameOfSupport();
    boolean timeOfSupportMode = true;
    for (SupportWorker supportWorker : supportWorkerClaims.values()) {

      timeOfSupportMode =
          supportWorker.getSupportWorkerClaims().stream()
              .anyMatch(e -> e.getTimeOfSupport() != null);

      int month = Integer.parseInt(supportWorker.getMonthYear().getMm());
      String year = supportWorker.getMonthYear().getYyyy();
      String monthWithYear = new DateFormatSymbols().getMonths()[month - 1] + " " + year;
      dlTag.with(
          divTagWithDtAndDdTags("Month support was received", monthWithYear),
          getDaysSupportWasReceived(supportWorker, month, monthWithYear, timeOfSupportMode,
              nameOfSupport));
    }

    dlTag.with(
        iffElse(timeOfSupportMode,
        getTotalHoursOfSupportReceived(supportWorkerClaims, timeOfSupportMode),
        getTotalHoursOfSupportReceivedForHoursOfSupport(supportWorkerClaims, timeOfSupportMode)),
        getTotalCost(supportWorkerClaimRequest.getCost(), "Total cost of support worker"),
        getReceiptAttached(supportWorkerClaimRequest.getEvidence()));

    return dlTag.withClass("govuk-summary-list");
  }

  private static DivTag getDaysSupportWasReceived(SupportWorker supportWorker, int month,
                                                  String monthWithYear, boolean timeOfSupportMode,
                                                  String nameOfSupport) {
    DdTag ddTag = new DdTag();
    for (SupportWorkerClaim supportWorkerClaim : supportWorker.getSupportWorkerClaims()) {

      ddTag.with(
          p(
              join(supportWorkerClaim.getDayOfSupport() + " " + monthWithYear),
              br(),
              join(
                  iffElse(timeOfSupportMode,
                      getTimeOfSupportForDaySupportReceived(supportWorkerClaim, timeOfSupportMode),
                      supportWorkerClaim.getHoursOfSupport() + " Hours")
              ),
              br(),
              join(escapeXml11(
                  nameOfSupport != null && !nameOfSupport.isBlank() ? nameOfSupport :
                      supportWorkerClaim.getNameOfSupport()
              ))
          )
      );
    }

    String dtValue =
        "Days support was received in " + new DateFormatSymbols().getMonths()[month - 1];

    return divTagWithDtAndDdTags(dtValue, ddTag);
  }

  private static String getTimeOfSupportForDaySupportReceived(
      SupportWorkerClaim supportWorkerClaim, boolean timeOfSupportMode) {

    if (!timeOfSupportMode) {
      log.info("getTimeOfSupportForDaySupportReceived:- timeOfSupport not found");
      return null;
    }

    String hours = supportWorkerClaim.getTimeOfSupport().getHoursOfSupport().equals("1")
        ? " hour" : " hours";
    String hoursAndMins = supportWorkerClaim.getTimeOfSupport().getHoursOfSupport() + hours;

    String minutes = supportWorkerClaim.getTimeOfSupport().getMinutesOfSupport().equals("1")
        ? " minute" : " minutes";
    hoursAndMins += " " + supportWorkerClaim.getTimeOfSupport().getMinutesOfSupport() + minutes;

    return hoursAndMins;
  }

  private static DivTag getTotalHoursOfSupportReceivedForHoursOfSupport(
      Map<String, SupportWorker> supportWorkerClaims, boolean timeOfSupportMode) {

    if (timeOfSupportMode) {
      log.info("getTotalHoursOfSupportReceivedForHoursOfSupport:-hoursOfSupport not found");
      return null;
    }

    double totalHours = 0.0;
    for (SupportWorker supportWorker : supportWorkerClaims.values()) {
      for (SupportWorkerClaim supportWorkerClaim : supportWorker.getSupportWorkerClaims()) {
        totalHours += Double.parseDouble(supportWorkerClaim.getHoursOfSupport());
      }
    }

    return divTagWithDtAndDdTags(
        "Total support received", DECIMAL_FORMAT.format(totalHours) + " hours");
  }

  private static DivTag getTotalHoursOfSupportReceived(
      Map<String, SupportWorker> supportWorkerClaims, boolean timeOfSupportMode) {

    if (!timeOfSupportMode) {
      log.info("getTotalHoursOfSupportReceived:- timeOfSupport not found");
      return null;
    }

    int totalMinutes = 0;
    for (SupportWorker supportWorker : supportWorkerClaims.values()) {
      for (SupportWorkerClaim supportWorkerClaim : supportWorker.getSupportWorkerClaims()) {
        totalMinutes +=
            Integer.parseInt(supportWorkerClaim.getTimeOfSupport().getHoursOfSupport()) * 60;
        totalMinutes +=
            Integer.parseInt(supportWorkerClaim.getTimeOfSupport().getMinutesOfSupport());
      }
    }

    return divTagWithDtAndDdTags(
        "Total support received", getTotalSupportReceived(totalMinutes));
  }

  private static String getTotalSupportReceived(int totalMinutes) {
    Duration dur = Duration.ofMinutes(totalMinutes);

    String hours = String.format("%d", dur.toHours());
    String minutes = String.format("%d", dur.toMinutesPart());

    return hours + (hours.equals("1") ? " hour " : " hours ")
        + minutes + (minutes.equals("1") ? " minute" : " minutes");
  }
}