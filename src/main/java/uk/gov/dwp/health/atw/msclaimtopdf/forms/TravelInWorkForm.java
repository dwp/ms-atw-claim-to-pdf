package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import static j2html.TagCreator.body;
import static j2html.TagCreator.br;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.html;
import static j2html.TagCreator.iff;
import static j2html.TagCreator.join;
import static j2html.TagCreator.p;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.divTagWithDtAndDdTags;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getAddress;
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
import java.util.Map;
import uk.gov.dwp.health.atw.msclaimtopdf.models.Claimant;
import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelInWork;
import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelInWorkClaim;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ClaimRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.TravelInWorkClaimRequest;

public class TravelInWorkForm {

  public static final String JOURNEYS = "journeys";
  public static final String JOURNEY = "journey";

  public static HtmlTag generateForm(ClaimRequest claimRequest) {
    Config.closeEmptyTags = true;

    return html(
        getHeadTag("Travel during work"),
        generateFormBody(claimRequest)
    );
  }

  private static BodyTag generateFormBody(ClaimRequest claimRequest) {
    TravelInWorkClaimRequest travelInWorkClaimRequest = (TravelInWorkClaimRequest) claimRequest;

    boolean selfEmployedEmploymentStatus =
        travelInWorkClaimRequest.getWorkplaceContact().getEmploymentStatus()
            .equalsIgnoreCase("Selfemployed");

    return body(
        getFormHeader("Claim for travel during work costs"),
        h2("Claimant’s details").withClass("govuk-heading-m"),
        getClaimantDetails(travelInWorkClaimRequest),
        h2("Claim information").withClass("govuk-heading-m"),
        getClaimInformation(travelInWorkClaimRequest),
        h2("Payee details").withClass("govuk-heading-m"),
        getPayeeDetailsNameAndEmail(travelInWorkClaimRequest.getPayee().getDetails()),
        h2("Employment details").withClass("govuk-heading-m"),
        getWorkplaceContactDetails(travelInWorkClaimRequest.getWorkplaceContact(),
            true,
            !selfEmployedEmploymentStatus),
        h2("Claim declaration").withClass("govuk-heading-m"),
        getClaimDeclaration(travelInWorkClaimRequest.getDeclarationVersion(),
            travelInWorkClaimRequest.getWorkplaceContact().getDeclarationVersion(),
            travelInWorkClaimRequest.getCreatedDate(),
            travelInWorkClaimRequest.getWorkplaceContact().getUpdatedOn(),
            !selfEmployedEmploymentStatus,
            selfEmployedEmploymentStatus)
    );
  }

  private static DlTag getClaimantDetails(TravelInWorkClaimRequest travelInWorkClaimRequest) {

    Claimant claimantDetails = travelInWorkClaimRequest.getClaimant();

    DlTag dlTag = new DlTag();
    dlTag.with(
        divTagWithDtAndDdTags("Full name", claimantDetails.getForename()
                + " " + claimantDetails.getSurname()),
        divTagWithDtAndDdTags("Access to Work number", travelInWorkClaimRequest.getAtwNumber()),
        iff(claimantDetails.getEmailAddress() != null,
            divTagWithDtAndDdTags("Email address", claimantDetails.getEmailAddress())),
        divTagWithDtAndDdTags("Home address", getAddress(claimantDetails.getAddress())),
        divTagWithDtAndDdTags("Claim type", travelInWorkClaimRequest.getClaimType()
            .getLabel()),
        divTagWithDtAndDdTags("Company or organisation name", claimantDetails.getCompany())
    );

    return dlTag.withClass("govuk-summary-list");
  }

  private static DlTag getClaimInformation(TravelInWorkClaimRequest travelInWorkClaimRequest) {
    Map<String, TravelInWork> travelInWorkClaims = travelInWorkClaimRequest.getClaim();
    DlTag dlTag = new DlTag();

    getMonthOfTravel(travelInWorkClaims, dlTag);

    dlTag.with(
        getTotalJourneysOfSupportReceived(travelInWorkClaims),
        iff(travelInWorkClaimRequest.getCost() != null,
            getTotalCost(travelInWorkClaimRequest.getCost(),
                "Total cost of " + JOURNEYS)),
        iff(travelInWorkClaimRequest.getTotalMileage() != null,
            getTotalMileage(travelInWorkClaimRequest.getTotalMileage(), "Total miles")),
        getReceiptAttached(travelInWorkClaimRequest.getEvidence()));

    return dlTag.withClass("govuk-summary-list");
  }

  private static void getMonthOfTravel(Map<String, TravelInWork> travelInWorkClaims, DlTag dlTag) {

    for (TravelInWork travelInWork : travelInWorkClaims.values()) {
      int month = Integer.parseInt(travelInWork.getMonthYear().getMm());
      String year = travelInWork.getMonthYear().getYyyy();
      String monthWithYear = new DateFormatSymbols().getMonths()[month - 1] + " " + year;

      dlTag.with(
          divTagWithDtAndDdTags("Month of travel", monthWithYear),
          getDaysSupportWasReceived(travelInWork, month, monthWithYear));
    }
  }

  private static DivTag getDaysSupportWasReceived(TravelInWork travelInWork, int month,
                                                  String monthWithYear) {
    DdTag ddTag = new DdTag();
    for (TravelInWorkClaim travelInWorkClaim : travelInWork.getClaim()) {
      ddTag.with(
          p(
              join(travelInWorkClaim.getDayOfTravel() + " " + monthWithYear),
              br(),
              join("From " + travelInWorkClaim.getStartPostcode()),
              br(),
              join("To " + travelInWorkClaim.getEndPostcode()),
              br(),
              join("£" + travelInWorkClaim.getCostOfTravel())
          )
      );
    }

    String dtValue = "Journeys made in " + new DateFormatSymbols().getMonths()[month - 1];

    return divTagWithDtAndDdTags(dtValue, ddTag);
  }

  private static DivTag getTotalJourneysOfSupportReceived(
      Map<String, TravelInWork> travelInWorkClaims) {

    int totalJourneys = 0;
    for (TravelInWork travelInWork : travelInWorkClaims.values()) {
      totalJourneys += travelInWork.getClaim().size();
    }

    String journey = totalJourneys > 1 ? JOURNEYS : JOURNEY;

    return divTagWithDtAndDdTags("Total number of " + journey,
        totalJourneys + " " + journey);
  }

  static DivTag getTotalMileage(Integer totalMileage, String dtValue) {
    String totalMileageValue = totalMileage != null ? String.valueOf(totalMileage) : "";
    return divTagWithDtAndDdTags(dtValue, totalMileageValue);
  }
}
