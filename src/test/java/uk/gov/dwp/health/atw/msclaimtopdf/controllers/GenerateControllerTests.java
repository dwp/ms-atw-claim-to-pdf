package uk.gov.dwp.health.atw.msclaimtopdf.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.ContactInformationTestData.submittedContactInformationRequest;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.EquipmentOrAdaptationTestData.validEquipmentOrAdaptationSubmitRequest;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.NewPayeeDetailsTestData.newPayeeDetailsRequest;
import static uk.gov.dwp.health.atw.msclaimtopdf.testData.SupportWorkerTestData.supportWorkerWhoIsEmployedClaimRequest;
import static uk.gov.dwp.health.atw.msclaimtopdf.utils.TestUtils.asJsonString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import uk.gov.dwp.health.atw.msclaimtopdf.controllers.utils.ServiceExceptionHandler;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ClaimRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.ContactInformationRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.requests.NewPayeeDetailsRequest;
import uk.gov.dwp.health.atw.msclaimtopdf.models.responses.GeneratedResponse;
import uk.gov.dwp.health.atw.msclaimtopdf.services.GenerateService;
import uk.gov.dwp.health.atw.msclaimtopdf.testData.TravelToWorkTestData;

@SpringBootTest(classes = GenerateController.class)
@EnableWebMvc
@AutoConfigureMockMvc
@ImportAutoConfiguration(ServiceExceptionHandler.class)
public class GenerateControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GenerateService generateService;

  TravelToWorkTestData travelToWorkTestData;

  @BeforeEach
  void setup() {
    travelToWorkTestData  = new TravelToWorkTestData();
  }

  @Test
  @DisplayName("/generate/claim-form endpoint for Equipment Or Adaptation - should return 200")
  void callGenerateForEquipmentOrAdaptations() throws Exception {

    GeneratedResponse response = new GeneratedResponse("7/435b379c-c84f-427a-903c-0925ba1418b1");

    when(generateService.generateFormAndUploadPdf(any(ClaimRequest.class)))
        .thenReturn("7/435b379c-c84f-427a-903c-0925ba1418b1");

    mockMvc.perform(post("/generate/claim-form")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(validEquipmentOrAdaptationSubmitRequest)))
        .andExpect(status().isOk())
        .andExpect(content().json(asJsonString(response)));

    verify(generateService, times(1)).generateFormAndUploadPdf(any());
  }

  @Test
  @DisplayName("/generate/claim-form endpoint for Support Worker - should return 200")
  void callGenerateForSupportWorker() throws Exception {

    GeneratedResponse response = new GeneratedResponse("7/435b379c-c84f-427a-903c-0925ba1418b1");

    when(generateService.generateFormAndUploadPdf(any(ClaimRequest.class)))
        .thenReturn("7/435b379c-c84f-427a-903c-0925ba1418b1");

    mockMvc.perform(post("/generate/claim-form")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(supportWorkerWhoIsEmployedClaimRequest)))
        .andExpect(status().isOk())
        .andExpect(content().json(asJsonString(response)));

    verify(generateService, times(1)).generateFormAndUploadPdf(any());
  }

  @Test
  @DisplayName("/generate/claim-form endpoint for Travel To Work - should return 200")
  void callGenerateForTravelToWork() throws Exception {

    GeneratedResponse response = new GeneratedResponse("7/435b379c-c84f-427a-903c-0925ba1418b1");

    when(generateService.generateFormAndUploadPdf(any(ClaimRequest.class)))
        .thenReturn("7/435b379c-c84f-427a-903c-0925ba1418b1");

    mockMvc.perform(post("/generate/claim-form")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(travelToWorkTestData.taxiTravelTypeWhoIsEmployedTWClaimRequest)))
        .andExpect(status().isOk())
        .andExpect(content().json(asJsonString(response)));

    verify(generateService, times(1)).generateFormAndUploadPdf(any());
  }

  @Test
  @DisplayName("/generate/update-personal-details endpoint - should return 200")
  void callUpdatePersonalDetails() throws Exception {

    GeneratedResponse response = new GeneratedResponse("7/435b379c-c84f-427a-903c-0925ba1418b1");

    when(generateService.updateContactDetails(any(ContactInformationRequest.class)))
        .thenReturn("7/435b379c-c84f-427a-903c-0925ba1418b1");

    mockMvc.perform(post("/generate/update-contact-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(submittedContactInformationRequest)))
        .andExpect(status().isOk())
        .andExpect(content().json(asJsonString(response)));

    verify(generateService, times(1)).updateContactDetails(any());
  }

  @Test
  @DisplayName("/generate/create-payee endpoint - should return 200")
  void callUpdatePayeeDetails() throws Exception {

    GeneratedResponse response = new GeneratedResponse("7/435b379c-c84f-427a-903c-0925ba1418b1");

    when(generateService.updatePayeeDetails(any(NewPayeeDetailsRequest.class)))
        .thenReturn("7/435b379c-c84f-427a-903c-0925ba1418b1");

    mockMvc.perform(post("/generate/create-payee")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(newPayeeDetailsRequest)))
        .andExpect(status().isOk())
        .andExpect(content().json(asJsonString(response)));

    verify(generateService, times(1)).updatePayeeDetails(any());
  }
}
