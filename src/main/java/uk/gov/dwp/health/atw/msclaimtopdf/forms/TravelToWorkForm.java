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
import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelDetails;
import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelToWork;
import uk.gov.dwp.health.atw.msclaimtopdf.models.TravelToWorkClaim;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ClaimRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.TravelToWorkClaimRequest;

public class TravelToWorkForm {

  public static final String JOURNEYS = "journeys";
  public static final String JOURNEY = "journey";

  public static HtmlTag generateForm(ClaimRequest claimRequest) {
    Config.closeEmptyTags = true;

    return html(
        getHeadTag("Travel to or from work"),
        generateFormBody(claimRequest)
    );
  }

  private static BodyTag generateFormBody(ClaimRequest claimRequest) {
    TravelToWorkClaimRequest travelToWorkClaimRequest = (TravelToWorkClaimRequest) claimRequest;

    boolean selfEmployedEmploymentStatus =
        travelToWorkClaimRequest.getWorkplaceContact().getEmploymentStatus()
            .equalsIgnoreCase("Selfemployed");

    return body(
        getFormHeader("Claim for travel to or from work costs"),
        h2("Claimant’s details").withClass("govuk-heading-m"),
        getClaimantDetails(travelToWorkClaimRequest),
        h2("Claim information").withClass("govuk-heading-m"),
        getClaimInformation(travelToWorkClaimRequest),
        h2("Payee details").withClass("govuk-heading-m"),
        getPayeeDetailsNameAndEmail(travelToWorkClaimRequest.getPayee()),
        h2("Employment details").withClass("govuk-heading-m"),
        getWorkplaceContactDetails(travelToWorkClaimRequest.getWorkplaceContact(),
            true,
            !selfEmployedEmploymentStatus),
        h2("Claim declaration").withClass("govuk-heading-m"),
        getClaimDeclaration(travelToWorkClaimRequest.getDeclarationVersion(),
            travelToWorkClaimRequest.getWorkplaceContact().getDeclarationVersion(),
            travelToWorkClaimRequest.getCreatedDate(),
            travelToWorkClaimRequest.getWorkplaceContact().getUpdatedOn(),
            !selfEmployedEmploymentStatus,
            selfEmployedEmploymentStatus)
    );
  }

  private static DlTag getClaimantDetails(TravelToWorkClaimRequest travelToWorkClaimRequest) {

    Claimant claimantDetails = travelToWorkClaimRequest.getClaimant();

    DlTag dlTag = new DlTag();
    dlTag.with(
        divTagWithDtAndDdTags("Full name", claimantDetails.getForename()
                + " " + claimantDetails.getSurname()),
        divTagWithDtAndDdTags("Access to Work number", travelToWorkClaimRequest.getAtwNumber()),
        iff(claimantDetails.getEmailAddress() != null,
            divTagWithDtAndDdTags("Email address", claimantDetails.getEmailAddress())),
        divTagWithDtAndDdTags("Home address", getAddress(claimantDetails.getAddress())),
        divTagWithDtAndDdTags("Claim type", travelToWorkClaimRequest.getClaimType()
            .getLabel()),
        divTagWithDtAndDdTags("Company or organisation name", claimantDetails.getCompany())
    );

    return dlTag.withClass("govuk-summary-list");
  }

  private static DlTag getClaimInformation(TravelToWorkClaimRequest travelToWorkClaimRequest) {
    Map<String, TravelToWork> travelToWorkClaims = travelToWorkClaimRequest.getClaim();
    DlTag dlTag = new DlTag();

    TravelDetails travelDetails = travelToWorkClaimRequest.getTravelDetails();
    String travelType = travelDetails.getJourneysOrMileage();
    String journeysOrMiles =
        travelType != null && travelType.equalsIgnoreCase("mileage")
            ? "mile(s)" : JOURNEYS;

    dlTag.with(divTagWithDtAndDdTags("Type of travel",
        travelDetails.getHowDidYouTravelForWork()));

    getMonthOfTravel(travelToWorkClaims, dlTag, journeysOrMiles);

    dlTag.with(
        getTotalJourneysOfSupportReceived(travelToWorkClaims, journeysOrMiles),
        iff(travelToWorkClaimRequest.getCost() != null,
            getTotalCost(travelToWorkClaimRequest.getCost(),
                "Total cost of " + journeysOrMiles)),
        getReceiptAttached(travelToWorkClaimRequest.getEvidence()));

    return dlTag.withClass("govuk-summary-list");
  }

  private static void getMonthOfTravel(Map<String, TravelToWork> travelToWorkClaims, DlTag dlTag,
                                       String journeysOrMiles) {

    for (TravelToWork travelToWork : travelToWorkClaims.values()) {
      int month = Integer.parseInt(travelToWork.getMonthYear().getMm());
      String year = travelToWork.getMonthYear().getYyyy();
      String monthWithYear = new DateFormatSymbols().getMonths()[month - 1] + " " + year;

      dlTag.with(
          divTagWithDtAndDdTags("Month of travel", monthWithYear),
          getDaysSupportWasReceived(travelToWork, month, monthWithYear, journeysOrMiles));
    }
  }

  private static DivTag getDaysSupportWasReceived(TravelToWork travelToWork, int month,
                                                  String monthWithYear, String journeysOrMiles) {
    DdTag ddTag = new DdTag();
    for (TravelToWorkClaim travelToWorkClaim : travelToWork.getClaim()) {

      int travelTotal = (int) Double.parseDouble(travelToWorkClaim.getTotalTravel());
      String formattedTotal = (journeysOrMiles.startsWith(JOURNEY))
          ?  String.valueOf(Integer.valueOf(travelTotal))
          :  travelToWorkClaim.getTotalTravel();

      if (travelTotal == 1 && journeysOrMiles.equalsIgnoreCase(JOURNEYS)) {
        journeysOrMiles = JOURNEY;
      }

      ddTag.with(
          p(
              join(travelToWorkClaim.getDayOfTravel() + " " + monthWithYear),
              br(),
              join(formattedTotal + " " + journeysOrMiles)
          )
      );
      if (journeysOrMiles.equalsIgnoreCase(JOURNEY)) {
        journeysOrMiles = JOURNEYS;
      }
    }


    String dtValue = "Days " + journeysOrMiles + " were made in "
        + new DateFormatSymbols().getMonths()[month - 1];

    return divTagWithDtAndDdTags(dtValue, ddTag);
  }

  private static DivTag getTotalJourneysOfSupportReceived(
      Map<String, TravelToWork> travelToWorkClaims, String journeysOrMiles) {

    double totalJourneys = 0;
    for (TravelToWork travelToWork : travelToWorkClaims.values()) {
      for (TravelToWorkClaim travelToWorkClaim : travelToWork.getClaim()) {
        totalJourneys += Double.parseDouble(travelToWorkClaim.getTotalTravel());
      }
    }

    String szTotalJourneys = String.valueOf(totalJourneys);

    if (journeysOrMiles.startsWith(JOURNEY)) {
      int itotal = (int) totalJourneys;
      szTotalJourneys =  String.valueOf(itotal);
    }

    return divTagWithDtAndDdTags("Total number of " + journeysOrMiles,
        szTotalJourneys + " " + journeysOrMiles);
  }
}
