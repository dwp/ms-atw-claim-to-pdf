package uk.gov.dwp.health.atw.msclaimtopdf.forms;

import static j2html.TagCreator.body;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.html;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getClaimDeclaration;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getClaimantDetails;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getFormHeader;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getHeadTag;
import static uk.gov.dwp.health.atw.msclaimtopdf.forms.FormUtils.getPayeeDetails;

import j2html.Config;
import j2html.tags.specialized.BodyTag;
import j2html.tags.specialized.HtmlTag;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.NewPayeeDetailsRequest;


public class NewPayeeDetailsForm {

  public static HtmlTag generateForm(NewPayeeDetailsRequest claimRequest) {
    Config.closeEmptyTags = true;

    return html(
                getHeadTag("New Payee Details"),
                generateFormBody(claimRequest)
        );
  }

  private static BodyTag generateFormBody(NewPayeeDetailsRequest newPayeeDetailsRequest) {

    return body(
            getFormHeader("New or amended payee details"),
            h2("Claimant’s details currently held").withClass("govuk-heading-m"),
            getClaimantDetails(newPayeeDetailsRequest.getCurrentContactInformation(),
                        newPayeeDetailsRequest.getAccessToWorkNumber()),
            h2("Details of new person or company being paid").withClass("govuk-heading-m"),
            getPayeeDetails(newPayeeDetailsRequest.getPayee()),
            h2("Claim declaration").withClass("govuk-heading-m"),
            getClaimDeclaration(newPayeeDetailsRequest.getDeclarationVersion(),
                    null,
                    newPayeeDetailsRequest.getCreatedDate(),
                    null,
                    false,
                    true)
        );
  }
}
