package uk.gov.dwp.health.atw.msclaimtopdf.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.dwp.health.atw.msclaimtopdf.exceptions.FileUploadException;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ClaimRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ContactInformationRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.NewPayeeDetailsRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.responses.GeneratedResponse;
import uk.gov.dwp.health.atw.msclaimtopdf.services.GenerateService;

@RestController
@Validated
public class GenerateController {

  final GenerateService generateService;

  public GenerateController(
      GenerateService generateService) {
    this.generateService = generateService;
  }

  @PostMapping(value = "/generate/claim-form")
  public ResponseEntity<?> generateFormAndUploadPdf(@RequestBody ClaimRequest body)
      throws FileUploadException {

    String fileId = generateService.generateFormAndUploadPdf(body);
    return ResponseEntity.ok(new GeneratedResponse(fileId));
  }

  @PostMapping(value = "/generate/update-contact-details")
  public ResponseEntity<?> updateContactDetails(@RequestBody ContactInformationRequest body)
      throws FileUploadException {

    String fileId = generateService.updateContactDetails(body);
    return ResponseEntity.ok(new GeneratedResponse(fileId));
  }

  @PostMapping(value = "/generate/create-payee")
  public ResponseEntity<?> updatePayeeDetails(@RequestBody NewPayeeDetailsRequest body)
      throws FileUploadException {

    String fileId = generateService.updatePayeeDetails(body);
    return ResponseEntity.ok(new GeneratedResponse(fileId));
  }
}
