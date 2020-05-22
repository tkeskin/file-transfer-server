package tr.com.aa.artemis;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JmsProducer {

  @Autowired
  private JmsTemplate jmsTemplate;

  @Value("${aa.download.queue}")
  private String downloadQueue;

  @Value("${aa.upload.queue}")
  private String uploadQueue;

  @Value("${aa.autostart.queue}")
  private String autoStartQueue;

  public void sendUpload(UUID message) {

    jmsTemplate.convertAndSend(uploadQueue, message);
    log.info("Sent message= {} from {}", message, uploadQueue);
  }

  public void sendDownload(UUID message) {

    jmsTemplate.convertAndSend(downloadQueue, message);
    log.info("Sent message= {} from {}", message, downloadQueue);

  }

  public void autoStart(UUID message) {

    jmsTemplate.convertAndSend(autoStartQueue, message);
    log.info("Sent message= {} from {}", message, autoStartQueue);

  }
}