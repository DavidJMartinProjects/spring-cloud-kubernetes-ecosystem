package ecosystem.gamificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ecosystem.gamificationservice.domain.pojo.response.RandomNumber;
import ecosystem.gamificationservice.service.facade.RestTemplateFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NumberService {

    public static final String NUMBER_SERVICE_URL = "http://number-service:9091/number-service/random";

    @Autowired
    private RestTemplateFacade restTemplateFacade;

    public int getNextNumber() throws JsonProcessingException {
        String randomNumberResponse = restTemplateFacade.getForEntity(NUMBER_SERVICE_URL).getBody();
        try {
            return new ObjectMapper().readValue(randomNumberResponse, RandomNumber.class).getNumber();
        } catch (JsonProcessingException ex) {
            log.info("encountered error parsing response from url: {}, {}", NUMBER_SERVICE_URL, ex.getMessage());
            throw ex;
        }
    }

}
