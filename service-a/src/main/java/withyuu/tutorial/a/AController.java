package withyuu.tutorial.a;

import brave.baggage.BaggageField;
import brave.baggage.BaggagePropagationCustomizer;
import brave.internal.baggage.ExtraBaggageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.BaggageInScope;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/a")
public class AController {

    private static Logger log = LoggerFactory.getLogger(AController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    BaggageField idField;

    @GetMapping("/message")
    public String message(
            @RequestHeader(value = "Correlation-Id", required = false) String correlationId) {
        log.info("Service-A is called with getid");


        long id = restTemplate.getForObject("http://localhost:8081/b/getid", long.class);
        this.idField.updateValue(String.valueOf(id));
        log.info("Service-A is called with id: {}", id);
        String bMsg = restTemplate.getForObject("http://localhost:8081/b/message", String.class);
        return "Message from B: " + bMsg;


    }


}
