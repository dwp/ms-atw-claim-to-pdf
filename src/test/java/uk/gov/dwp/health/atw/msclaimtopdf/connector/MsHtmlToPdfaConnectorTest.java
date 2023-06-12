package uk.gov.dwp.health.atw.msclaimtopdf.connector;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Base64;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import uk.gov.dwp.health.atw.msclaimtopdf.config.ApplicationConfig;
import uk.gov.dwp.health.atw.msclaimtopdf.models.ServiceConfig;

@SpringBootTest(classes = MsHtmlToPdfaConnector.class)
class MsHtmlToPdfaConnectorTest {

  @MockBean
  private RestTemplate restTemplate;

  @MockBean
  private ApplicationConfig applicationConfig;

  @Autowired
  MsHtmlToPdfaConnector connector;

  @Test
  @DisplayName("successfully converting html to pdf")
  void successfulConversion() {
    String encodedResponseBody = "encodedResponseBody";
    byte[] postForResponse = Base64.getDecoder().decode(encodedResponseBody);

    when(applicationConfig.getMsHtmlToPdfA())
        .thenReturn(new ServiceConfig("http://host:1234"));

    when(restTemplate.postForObject(any(), any(), any()))
        .thenReturn(encodedResponseBody);

    assertArrayEquals(connector.post("encodedInputBody"), postForResponse);
  }

}