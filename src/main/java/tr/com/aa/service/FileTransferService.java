package tr.com.aa.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tr.com.aa.dal.entity.User;
import tr.com.aa.dal.repository.UserRepository;

@Slf4j
@Service
public class FileTransferService {

  @Autowired
  private UserRepository repository;

  @Autowired
  private FtpServerService ftpServerService;

  @Async
  public CompletableFuture<List<User>> saveUsers(MultipartFile file) throws Exception {

    long start = System.currentTimeMillis();
    List<User> users = parseCSVFile(file);
    log.info("saving list of users of size {}", users.size(),
        "" + Thread.currentThread().getName());
    users = repository.saveAll(users);
    long end = System.currentTimeMillis();
    log.info("Total time {}", (end - start));
    return CompletableFuture.completedFuture(users);
  }

  @Async
  public CompletableFuture<List<User>> findAllUsers() {

    log.info("get list of user by " + Thread.currentThread().getName());
    long start = System.currentTimeMillis();
    List<User> users = repository.findAll();
    long end = System.currentTimeMillis();
    log.info("Total time {}", (end - start));
    return CompletableFuture.completedFuture(users);
  }

  private List<User> parseCSVFile(final MultipartFile file) throws Exception {

    final List<User> users = new ArrayList<>();
    try {
      try (final BufferedReader br = new BufferedReader(
          new InputStreamReader(file.getInputStream()))) {
        String line;
        while ((line = br.readLine()) != null) {
          final String[] data = line.split(",");
          final User user = new User();
          user.setName(data[0]);
          user.setEmail(data[1]);
          user.setGender(data[2]);
          users.add(user);
        }
        return users;
      }
    } catch (final IOException e) {
      log.error("Failed to parse CSV file {}", e);
      throw new Exception("Failed to parse CSV file {}", e);
    }
  }

  /*@Async
  public Boolean fileUpload(Map.Entry<UUID, List<JobDestinationDto>> file) {

    log.info("{} Start upload thread : {}", file.getKey(), Thread.currentThread().getName());
    long start = System.currentTimeMillis();
    for (JobDestinationDto jobDestinationDto : file.getValue()) {
      connectUpload(file.getKey(), jobDestinationDto);
    }
    long end = System.currentTimeMillis();
    log.info("{} Total time {}", Thread.currentThread().getName(), (end - start));
    return true;
  }

  public void connectUpload(UUID uuid,
                            JobDestinationDto jobDestinationDto) {

    FtpServerDto ftpServerDto = ftpServerService.findById(uuid);
    log.info("{} connect&upload thread : {}", ftpServerDto.getUsername(),
        Thread.currentThread().getName());
    Client client = new ClientFactory().createClient(ClientFactory.Protocol.FTP);
    client.setHost(ftpServerDto.getHostAdress());
    client.setPort(ftpServerDto.getPort());
    client.setCredentials(
        new UserCredentials(ftpServerDto.getUsername(), ftpServerDto.getPassword()));
    Connection connection = client.connect();
    connection.uploadFile(jobDestinationDto.getDownloadPath(), jobDestinationDto.getUploadPath(),
        true);
    client.disconnect();
  }*/

}