package ecosystem.gamificationservice.service;

import ecosystem.gamificationservice.domain.pojo.response.RandomNumberResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestTemplateFacade {

    public ResponseEntity<RandomNumberResponse> getForRandomNumber(String url, RestTemplate restTemplate) {
        ResponseEntity<RandomNumberResponse> response;
        try {
            log.info("making GET request to url: {}", url);
            response = restTemplate.getForEntity(url, RandomNumberResponse.class);
        } catch (HttpStatusCodeException ex) {
            log.info("encountered http error during GET request to url: {} {}", url, ex);
            response = new ResponseEntity<>(ex.getStatusCode());
        } catch (Exception ex) {
            log.info("encountered http error during GET request to url: {}", url, ex);
            response = new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return response;
    }

}
