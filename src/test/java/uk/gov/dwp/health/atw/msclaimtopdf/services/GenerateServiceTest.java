package uk.gov.dwp.health.atw.msclaimtopdf.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.AdaptationToVehicleTestData.validAdaptationToVehicleSubmitRequest;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.ContactInformationTestData.submittedContactInformationRequest;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.EquipmentOrAdaptationTestData.validEquipmentOrAdaptationSubmitRequest;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.NewPayeeDetailsTestData.newPayeeDetailsRequest;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.SupportWorkerTestData.supportWorkerWhoIsEmployedClaimRequest;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.dwp.health.atw.msclaimtopdf.connector.MsHtmlToPdfaConnector;
import uk.gov.dwp.health.atw.msclaimtopdf.exceptions.FileUploadException;
import uk.gov.dwp.health.atw.msclaimtopdf.repositories.S3Repository;
import uk.gov.dwp.health.atw.msclaimtopdf.testData.TravelToWorkTestData;

@SpringBootTest(classes = GenerateService.class)
class GenerateServiceTest {

  @Autowired
  GenerateService generateService;

  @MockBean
  MsHtmlToPdfaConnector msHtmlToPdfaConnector;

  @MockBean
  S3Repository s3Repository;

  String uuid = UUID.randomUUID().toString();

  TravelToWorkTestData travelToWorkTestData;

  @BeforeEach
  void setup() {
    travelToWorkTestData  = new TravelToWorkTestData();
  }

  @Test
  @DisplayName("generate support worker html and upload pdf successful")
  void generateSupportWorkerFormAndUploadPdfSuccessful() throws FileUploadException {
    String responseFileKey = "claim-forms/" + supportWorkerWhoIsEmployedClaimRequest.getClaimType() + supportWorkerWhoIsEmployedClaimRequest.getId()+ "/" + uuid;

    when(msHtmlToPdfaConnector.post(anyString())).thenReturn(new byte[0]);
    when(s3Repository.createFile(any(byte[].class), anyString())).thenReturn(responseFileKey);

    assertEquals(responseFileKey, generateService.generateFormAndUploadPdf(supportWorkerWhoIsEmployedClaimRequest));

    verify(msHtmlToPdfaConnector, times(1)).post(anyString());
    verify(s3Repository, times(1)).createFile(any(byte[].class), anyString());
  }

  @Test
  @DisplayName("generate travel to work html and upload pdf successful")
  void generateTravelToWorkFormAndUploadPdfSuccessful() throws FileUploadException {
    String responseFileKey = "claim-forms/" + travelToWorkTestData.taxiTravelToWorkOptionalFieldSkipped.getClaimType() + travelToWorkTestData.taxiTravelToWorkOptionalFieldSkipped.getId()+ "/" + uuid;

    when(msHtmlToPdfaConnector.post(anyString())).thenReturn(new byte[0]);
    when(s3Repository.createFile(any(byte[].class), anyString())).thenReturn(responseFileKey);

    assertEquals(responseFileKey, generateService.generateFormAndUploadPdf(travelToWorkTestData.taxiTravelToWorkOptionalFieldSkipped));

    verify(msHtmlToPdfaConnector, times(1)).post(anyString());
    verify(s3Repository, times(1)).createFile(any(byte[].class), anyString());
  }

  @Test
  @DisplayName("generate equipment or adaptation html and upload pdf successful")
  void generateEquipmentOrAdaptationFormAndUploadPdfSuccessful() throws FileUploadException {
    String responseFileKey = "claim-forms/" + validEquipmentOrAdaptationSubmitRequest.getClaimType() + validEquipmentOrAdaptationSubmitRequest.getId()+ "/" + uuid;

    when(msHtmlToPdfaConnector.post(anyString())).thenReturn(new byte[0]);
    when(s3Repository.createFile(any(byte[].class), anyString())).thenReturn(responseFileKey);

    assertEquals(responseFileKey, generateService.generateFormAndUploadPdf(validEquipmentOrAdaptationSubmitRequest));

    verify(msHtmlToPdfaConnector, times(1)).post(anyString());
    verify(s3Repository, times(1)).createFile(any(byte[].class), anyString());
  }

  @Test
  @DisplayName("generate adaptation to vehicle html and upload pdf successful")
  void generateAdaptationToVehicleFormAndUploadPdfSuccessful() throws FileUploadException {
    String responseFileKey = "claim-forms/" + validAdaptationToVehicleSubmitRequest.getClaimType() + validAdaptationToVehicleSubmitRequest.getId()+ "/" + uuid;

    when(msHtmlToPdfaConnector.post(anyString())).thenReturn(new byte[0]);
    when(s3Repository.createFile(any(byte[].class), anyString())).thenReturn(responseFileKey);

    assertEquals(responseFileKey, generateService.generateFormAndUploadPdf(validAdaptationToVehicleSubmitRequest));

    verify(msHtmlToPdfaConnector, times(1)).post(anyString());
    verify(s3Repository, times(1)).createFile(any(byte[].class), anyString());
  }

  @Test
  @DisplayName("generate update personal details form and upload pdf successful")
  void updatePersonalDetailsSuccessful() throws FileUploadException {
    String responseFileKey = "claim-forms/contact-information/" + submittedContactInformationRequest.getAccessToWorkNumber() + "/" + uuid;

    when(msHtmlToPdfaConnector.post(anyString())).thenReturn(new byte[0]);
    when(s3Repository.createFile(any(byte[].class), anyString())).thenReturn(responseFileKey);

    assertEquals(responseFileKey, generateService.updateContactDetails(submittedContactInformationRequest));

    verify(msHtmlToPdfaConnector, times(1)).post(anyString());
    verify(s3Repository, times(1)).createFile(any(byte[].class), anyString());
  }

  @Test
  @DisplayName("generate update payee details form and upload pdf successful")
  void updatePayeeDetailsSuccessful() throws FileUploadException {
    String responseFileKey = "claim-forms/payee-information/" + newPayeeDetailsRequest.getAccessToWorkNumber() + "/" + uuid;

    when(msHtmlToPdfaConnector.post(anyString())).thenReturn(new byte[0]);
    when(s3Repository.createFile(any(byte[].class), anyString())).thenReturn(responseFileKey);

    assertEquals(responseFileKey, generateService.updatePayeeDetails(newPayeeDetailsRequest));

    verify(msHtmlToPdfaConnector, times(1)).post(anyString());
    verify(s3Repository, times(1)).createFile(any(byte[].class), anyString());
  }
}