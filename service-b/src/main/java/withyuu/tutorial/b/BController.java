package withyuu.tutorial.b;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/b")
public class BController {

  private static Logger log = LoggerFactory.getLogger(BController.class);

  @GetMapping("/message")
  public String message(
          @RequestHeader(value = "Correlation-Id", required = false) String correlationId, @RequestHeader(value = "id", required = false) String id, @RequestHeader HttpHeaders reader) {
    log.info("Service-B is called with Correlation-Id: {}", correlationId);
    log.info("Service-B is called with id: {}", id);
    return "Greetings from Service-B!";
  }

  @GetMapping("/getid")
  public long getID() {

    long longid = new Random().nextLong();
    log.info("Service-B is getID: {}",longid);
    return longid;
  }

}
