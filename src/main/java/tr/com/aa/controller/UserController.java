package tr.com.aa.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tr.com.aa.dal.entity.User;
import tr.com.aa.service.FileTransferService;

@RestController
public class UserController {

  @Autowired
  private FileTransferService fileTransferService;

  @PostMapping(value = "/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity saveUsers(@RequestParam(value = "files") MultipartFile[] files)
      throws Exception {

    for (MultipartFile file : files) {
      fileTransferService.saveUsers(file);
    }
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public CompletableFuture<ResponseEntity> findAllUsers() {

    return fileTransferService.findAllUsers().thenApply(ResponseEntity::ok);
  }

  @GetMapping(value = "/getUsersByThread", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getUsers() {

    CompletableFuture<List<User>> users1 = fileTransferService.findAllUsers();
    CompletableFuture<List<User>> users2 = fileTransferService.findAllUsers();
    CompletableFuture<List<User>> users3 = fileTransferService.findAllUsers();
    CompletableFuture.allOf(users1, users2, users3).join();
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
