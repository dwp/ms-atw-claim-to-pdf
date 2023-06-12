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
import java.util.List;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Claimant;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Date;
import uk.gov.dwp.health.atw.msclaimtopdf.models.EquipmentOrAdaptation;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ClaimRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.EquipmentOrAdaptationClaimRequest;

public class EquipmentOrAdaptationForm {

  public static HtmlTag generateForm(ClaimRequest claimRequest) {
    Config.closeEmptyTags = true;

    return html(
        getHeadTag("specialist equipment"),
        generateFormBody(claimRequest)
    );
  }

  private static BodyTag generateFormBody(ClaimRequest claimRequest) {
    EquipmentOrAdaptationClaimRequest equipmentOrAdaptationClaimRequest =
        (EquipmentOrAdaptationClaimRequest) claimRequest;

    return body(
        getFormHeader("Claim for specialist equipment costs"),
        h2("Claimant’s details").withClass("govuk-heading-m"),
        getClaimantDetails(equipmentOrAdaptationClaimRequest),
        h2("Claim information").withClass("govuk-heading-m"),
        getClaimInformation(equipmentOrAdaptationClaimRequest),
        h2("Payee details").withClass("govuk-heading-m"),
        getPayeeDetails(equipmentOrAdaptationClaimRequest.getPayee()),
        h2("Claim declaration").withClass("govuk-heading-m"),
        getClaimDeclaration(equipmentOrAdaptationClaimRequest.getDeclarationVersion(),
            null,
            equipmentOrAdaptationClaimRequest.getCreatedDate(),
            null,
            false,
            true)
    );
  }

  private static DlTag getClaimantDetails(
      EquipmentOrAdaptationClaimRequest equipmentOrAdaptationClaimRequest) {

    Claimant claimantDetails = equipmentOrAdaptationClaimRequest.getClaimant();

    DlTag dlTag = new DlTag();
    dlTag.with(
        divTagWithDtAndDdTags("Full name", claimantDetails.getForename()
                + " " + claimantDetails.getSurname()),
        divTagWithDtAndDdTags("Access to Work number",
            equipmentOrAdaptationClaimRequest.getAtwNumber()),
        iff(claimantDetails.getEmailAddress() != null,
            divTagWithDtAndDdTags("Email address", claimantDetails.getEmailAddress())),
        divTagWithDtAndDdTags("Claim type", equipmentOrAdaptationClaimRequest.getClaimType()
            .getLabel()),
        divTagWithDtAndDdTags("Company or organisation name", claimantDetails.getCompany())
    );

    return dlTag.withClass("govuk-summary-list");
  }

  private static DlTag getClaimInformation(
      EquipmentOrAdaptationClaimRequest equipOrAdaptClaimRequest) {
    List<EquipmentOrAdaptation> equipmentOrAdaptations = equipOrAdaptClaimRequest.getClaim();
    DlTag dlTag = new DlTag();

    dlTag.with(getEquipmentOrAdaptationsReceived(equipmentOrAdaptations),
        getTotalCost(equipOrAdaptClaimRequest.getCost(),
            "Total cost of specialist equipment"),
        getReceiptAttached(equipOrAdaptClaimRequest.getEvidence()));

    return dlTag.withClass("govuk-summary-list");
  }

  private static DivTag getEquipmentOrAdaptationsReceived(
      List<EquipmentOrAdaptation> equipmentOrAdaptations) {
    DdTag ddTag = new DdTag();
    for (EquipmentOrAdaptation equipmentOrAdaptation : equipmentOrAdaptations) {

      Date dateOfPurchase = equipmentOrAdaptation.getDateOfPurchase();
      int month = Integer.parseInt(dateOfPurchase.getMm());
      String dayMonthYear = dateOfPurchase.getDd() + " "
          + new DateFormatSymbols().getMonths()[month - 1] + " "
          + dateOfPurchase.getYyyy();

      ddTag.with(
          p(
              join(dayMonthYear),
              br(),
              join(escapeXml11(equipmentOrAdaptation.getDescription()))
          )
      );
    }

    return divTagWithDtAndDdTags("Specialist equipment description", ddTag);
  }
}
