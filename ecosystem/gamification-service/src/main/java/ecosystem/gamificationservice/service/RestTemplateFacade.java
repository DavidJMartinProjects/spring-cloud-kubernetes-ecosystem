package ecosystem.gamificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestTemplateFacade {

    private static final String NUMBER_SERVICE_URL = "http://number-service-svc/random";

    private final RestTemplate restTemplate = new RestTemplate();

    public int getRandomNumber() {
        try {
            return restTemplate.getForObject(NUMBER_SERVICE_URL, int.class);
        } catch (Exception exception) {
            log.info("exception occurred attempting to contact number-service. {} {}",
                    exception.getMessage(), exception);
            throw new RuntimeException("cannot contact number-service {}", exception);
        }
    }

}
