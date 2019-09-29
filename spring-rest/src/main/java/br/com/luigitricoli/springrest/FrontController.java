package br.com.luigitricoli.springrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Instant;

@RestController
public class FrontController {

    Logger logger = LoggerFactory.getLogger(FrontController.class);

    @RequestMapping("/tcp")
    public String index(@RequestParam String server, @RequestParam String message) {
        logger.info("Message param: " + message);

        TcpClient client = new TcpClient(server);
        String m = null;
        try {
            m = client.send(message);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return "Return from " + server + "at: " + Instant.now() + "<br>" + m;
    }

}
