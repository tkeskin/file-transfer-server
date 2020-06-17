package tr.com.tkeskin.activemq;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import tr.com.tkeskin.models.JobDestinationDto;

@Slf4j
@DependsOn("appConfig")
@Component
public class Producer {

  @Autowired
  private JmsTemplate jmsTemplate;

  @Value("${aa.download.queue}")
  private String downloadQueue;

  @Value("${aa.upload.queue}")
  private String uploadQueue;

  public void sendUpload(String message) {

    jmsTemplate.convertAndSend(uploadQueue, message);
    log.info("Sent message = {} from {}", message, uploadQueue);
  }

  public void sendDownload(JobDestinationDto jobDestinationDto) {

    Random random = new Random();
    jmsTemplate.setExplicitQosEnabled(true);
    jmsTemplate.setMessageIdEnabled(true);
    jmsTemplate.setPriority(random.ints(0, 10)
        .findFirst()
        .getAsInt());
    jmsTemplate.convertAndSend(downloadQueue, jobDestinationDto.getId().toString());
    log.info("Sent message= {} from {}", jobDestinationDto, downloadQueue);

  }
}