package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
public class ArmNodeMetricService {

    private static final String ARM_IP = "192.168.1.216";
    private static final String URL = "http://" + ARM_IP + ":8080";

    public Integer getSignal(String mac) {
        return getAllSignals().get(mac);
    }

    public Map<String, Integer> getAllSignals() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);

        String json = response.getBody();
        if (response.getStatusCode() != HttpStatus.OK) {
            json = "{}";
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, new TypeReference<Map<String, Integer>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyMap();
    }
}
