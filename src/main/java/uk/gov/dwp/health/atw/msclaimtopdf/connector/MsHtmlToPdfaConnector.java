package uk.gov.dwp.health.atw.msclaimtopdf.connector;

import java.net.URI;
import java.util.Base64;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.gov.dwp.health.atw.msclaimtopdf.config.ApplicationConfig;
import uk.gov.dwp.health.atw.msclaimtopdf.models.ServiceConfig;

@Service
public class MsHtmlToPdfaConnector {

  final ApplicationConfig applicationConfig;
  final RestTemplate restTemplate;

  public MsHtmlToPdfaConnector(ApplicationConfig applicationConfig, RestTemplate restTemplate) {
    this.applicationConfig = applicationConfig;
    this.restTemplate = restTemplate;
  }

  public byte[] post(String body) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("page_html", body);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

    ServiceConfig config = applicationConfig.getMsHtmlToPdfA();
    URI uri = URI.create(config.getBaseUri() + "/generatePdf");
    String encodedResponse = restTemplate.postForObject(uri, request, String.class);

    return Base64.getDecoder().decode(encodedResponse);
  }
}
