package ecosystem.gamificationservice.service.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestTemplateFacade {

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> getForEntity(String url) {
        ResponseEntity<String> response;
        try {
            log.info("making GET request to url: {}", url);
            response = restTemplate.getForEntity(url, String.class);
        } catch (HttpStatusCodeException ex) {
            log.info("encountered http error during GET request to url: {} {}", url, ex);
            response = new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
        } catch (Exception ex) {
            log.info("encountered http error during GET request to url: {} {}", url, ex);
            response = new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return response;
    }

}
