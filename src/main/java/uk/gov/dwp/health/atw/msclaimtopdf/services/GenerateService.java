package uk.gov.dwp.health.atw.msclaimtopdf.services;

import j2html.tags.specialized.HtmlTag;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;
import org.springframework.stereotype.Service;
import uk.gov.dwp.health.atw.msclaimtopdf.connector.MsHtmlToPdfaConnector;
import uk.gov.dwp.health.atw.msclaimtopdf.exceptions.FileUploadException;
import uk.gov.dwp.health.atw.msclaimtopdf.forms.EquipmentOrAdaptationForm;
import uk.gov.dwp.health.atw.msclaimtopdf.forms.NewOrAmendedContactDetailsForm;
import uk.gov.dwp.health.atw.msclaimtopdf.forms.NewPayeeDetailsForm;
import uk.gov.dwp.health.atw.msclaimtopdf.forms.SupportWorkerForm;
import uk.gov.dwp.health.atw.msclaimtopdf.forms.TravelToWorkForm;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ClaimRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ContactInformationRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.NewPayeeDetailsRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.repositories.S3Repository;

@Service
public class GenerateService {

  private final MsHtmlToPdfaConnector msHtmlToPdfaConnector;
  private final S3Repository s3Repository;

  public GenerateService(MsHtmlToPdfaConnector msHtmlToPdfaConnector,
                         S3Repository s3Repository) {
    this.msHtmlToPdfaConnector = msHtmlToPdfaConnector;
    this.s3Repository = s3Repository;
  }

  public String generateFormAndUploadPdf(ClaimRequest claimRequest) throws FileUploadException {

    String generatedHtml = generateHtml(claimRequest).render();

    String s3BucketKey = "claim-forms/"
        + claimRequest.getClaimType()
        + "/" + claimRequest.getId()
        + "/" + UUID.randomUUID();

    return postHtmlToPdfAndUploadToS3Bucket(generatedHtml, s3BucketKey);
  }

  private HtmlTag generateHtml(ClaimRequest claimRequest) {

    switch (claimRequest.getClaimType()) {
      case TRAVEL_TO_WORK:
        return TravelToWorkForm.generateForm(claimRequest);
      case SUPPORT_WORKER:
        return SupportWorkerForm.generateForm(claimRequest);
      case EQUIPMENT_OR_ADAPTATION:
        return EquipmentOrAdaptationForm.generateForm(claimRequest);
      default:
        throw new IllegalArgumentException(
            "Claim type not supported " + claimRequest.getClaimType());
    }
  }

  public String updateContactDetails(ContactInformationRequest contactInformationRequest)
      throws FileUploadException {

    String generatedHtml =
        NewOrAmendedContactDetailsForm.generateForm(contactInformationRequest).render();

    String s3BucketKey = "claim-forms/contact-information"
        + "/" + contactInformationRequest.getAccessToWorkNumber()
        + "/" + UUID.randomUUID();

    return postHtmlToPdfAndUploadToS3Bucket(generatedHtml,  s3BucketKey);
  }

  public String updatePayeeDetails(NewPayeeDetailsRequest newPayeeDetailsRequest)
      throws FileUploadException {

    String generatedHtml =
        NewPayeeDetailsForm.generateForm(newPayeeDetailsRequest).render();

    String s3BucketKey = "claim-forms/payee-information"
        + "/" + newPayeeDetailsRequest.getAccessToWorkNumber()
        + "/" + UUID.randomUUID();

    return postHtmlToPdfAndUploadToS3Bucket(generatedHtml, s3BucketKey);
  }

  private String postHtmlToPdfAndUploadToS3Bucket(String generatedHtml, String s3BucketKey)
      throws FileUploadException {

    String base64 = Base64.getEncoder().encodeToString(
        generatedHtml.getBytes(StandardCharsets.UTF_8));
    byte[] decodedHtmlPdf = msHtmlToPdfaConnector.post(base64);

    return s3Repository.createFile(decodedHtmlPdf, s3BucketKey);
  }
}
