package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import static j2html.TagCreator.body;
import static j2html.TagCreator.br;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.html;
import static j2html.TagCreator.iff;
import static j2html.TagCreator.join;
import static j2html.TagCreator.p;
import static org.apache.commons.text.StringEscapeUtils.escapeXml11;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.divTagWithDtAndDdTags;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getClaimDeclaration;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getFormHeader;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getHeadTag;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getPayeeDetails;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getReceiptAttached;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getTotalCost;

import j2html.Config;
import j2html.tags.specialized.BodyTag;
import j2html.tags.specialized.DdTag;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.DlTag;
import j2html.tags.specialized.HtmlTag;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import uk.gov.dwp.health.atw.msclaimtopdf.models.AdaptationToVehicle;
import uk.gov.dwp.health.atw.msclaimtopdf.models.AdaptationToVehicleClaim;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Claimant;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Date;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.AdaptationToVehicleClaimRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ClaimRequest;

public class AdaptationToVehicleForm {

  public static HtmlTag generateForm(ClaimRequest claimRequest) {
    Config.closeEmptyTags = true;

    return html(
        getHeadTag("adaptation to vehicle"),
        generateFormBody(claimRequest)
    );
  }

  private static BodyTag generateFormBody(ClaimRequest claimRequest) {
    AdaptationToVehicleClaimRequest adaptationToVehicleClaimRequest =
        (AdaptationToVehicleClaimRequest) claimRequest;

    return body(
        getFormHeader("Claim for adaptation to vehicle costs"),
        h2("Claimant’s details").withClass("govuk-heading-m"),
        getClaimantDetails(adaptationToVehicleClaimRequest),
        h2("Claim information").withClass("govuk-heading-m"),
        getClaimInformation(adaptationToVehicleClaimRequest),
        h2("Payee details").withClass("govuk-heading-m"),
        getPayeeDetails(adaptationToVehicleClaimRequest.getPayee()),
        h2("Claim declaration").withClass("govuk-heading-m"),
        getClaimDeclaration(adaptationToVehicleClaimRequest.getDeclarationVersion(),
            null,
            adaptationToVehicleClaimRequest.getCreatedDate(),
            null,
            false,
            true)
    );
  }

  private static DlTag getClaimantDetails(
      AdaptationToVehicleClaimRequest adaptationToVehicleClaimRequest) {

    Claimant claimantDetails = adaptationToVehicleClaimRequest.getClaimant();

    DlTag dlTag = new DlTag();
    dlTag.with(
        divTagWithDtAndDdTags("Full name", claimantDetails.getForename()
            + " " + claimantDetails.getSurname()),
        divTagWithDtAndDdTags("Access to Work number",
            adaptationToVehicleClaimRequest.getAtwNumber()),
        iff(claimantDetails.getEmailAddress() != null,
            divTagWithDtAndDdTags("Email address", claimantDetails.getEmailAddress())),
        divTagWithDtAndDdTags("Claim type", adaptationToVehicleClaimRequest.getClaimType()
            .getLabel()),
        divTagWithDtAndDdTags("Company or organisation name", claimantDetails.getCompany())
    );

    return dlTag.withClass("govuk-summary-list");
  }

  private static DlTag getClaimInformation(
      AdaptationToVehicleClaimRequest adaptationToVehicleClaimRequest) {

    Map<String, AdaptationToVehicle> adaptationToVehicleClaims =
        adaptationToVehicleClaimRequest.getClaim();
    DlTag dlTag = new DlTag();

    List<AdaptationToVehicleClaim> adaptationToVehicles = new ArrayList<>();
    for (AdaptationToVehicle adaptationToVehicle : adaptationToVehicleClaims.values()) {
      adaptationToVehicles.addAll(adaptationToVehicle.getClaimDescription());
    }

    dlTag.with(getAdaptationToVehiclesReceived(adaptationToVehicles),
        getTotalCost(adaptationToVehicleClaimRequest.getCost(),
            "Total cost of vehicle adaptations"),
        getReceiptAttached(adaptationToVehicleClaimRequest.getEvidence()));

    return dlTag.withClass("govuk-summary-list");
  }

  private static DivTag getAdaptationToVehiclesReceived(
      List<AdaptationToVehicleClaim> adaptationToVehicles) {
    DdTag ddTag = new DdTag();
    for (AdaptationToVehicleClaim adaptationToVehicle : adaptationToVehicles) {

      Date dateOfInvoice = adaptationToVehicle.getDateOfInvoice();
      int month = Integer.parseInt(dateOfInvoice.getMm());
      String dayMonthYear = dateOfInvoice.getDd() + " "
          + new DateFormatSymbols().getMonths()[month - 1] + " "
          + dateOfInvoice.getYyyy();

      ddTag.with(
          p(
              join(dayMonthYear),
              br(),
              join(escapeXml11(adaptationToVehicle.getDescription()))
          )
      );
    }

    return divTagWithDtAndDdTags("Vehicle adaptations description", ddTag);
  }
}
