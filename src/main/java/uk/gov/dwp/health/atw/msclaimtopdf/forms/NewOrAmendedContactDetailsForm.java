package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import static j2html.TagCreator.body;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.html;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getClaimDeclaration;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getClaimantDetails;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getFormHeader;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getHeadTag;

import j2html.Config;
import j2html.tags.specialized.BodyTag;
import j2html.tags.specialized.HtmlTag;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ContactInformationRequest;

public class NewOrAmendedContactDetailsForm {

  public static HtmlTag generateForm(ContactInformationRequest claimRequest) {
    Config.closeEmptyTags = true;

    return html(
        getHeadTag("New Address Details"),
        generateFormBody(claimRequest)
    );
  }

  private static BodyTag generateFormBody(ContactInformationRequest contactInformationRequest) {

    return body(
        getFormHeader("Change in customer personal details"),
        h2("Claimant’s details currently held").withClass("govuk-heading-m"),
        getClaimantDetails(contactInformationRequest.getCurrentContactInformation(),
            contactInformationRequest.getAccessToWorkNumber()),
        h2("Claimant’s new details").withClass("govuk-heading-m"),
        getClaimantDetails(contactInformationRequest.getNewContactInformation(),
            contactInformationRequest.getAccessToWorkNumber()),
        h2("Claim declaration").withClass("govuk-heading-m"),
        getClaimDeclaration(contactInformationRequest.getDeclarationVersion(),
            null,
            contactInformationRequest.getCreatedDate(),
            null,
            false,
            true)
    );
  }
}
