package tr.com.tkeskin.util;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

@Slf4j
public class FileUtil {

  public File getFile(String filePath) {

    return new File(filePath);
  }

  public void moveFileToDirectory(String oldPath, String newPath) throws IOException {

    FileUtils.moveToDirectory(getFile(oldPath), getFile(newPath), true);
  }

  public static void createLocalDirectory(String directoryPath) {

    File file = new File(directoryPath);
    if (file.isDirectory() && file.exists()) {
      log.info("Directory exists!");
    } else {
      boolean directoryCreated = file.mkdirs();

      if (!directoryCreated) {
        log.debug("Directory was not created!");
      }
    }

  }

  public static String ensureTrailingSlash(String path) {

    if (!path.endsWith("/")) {
      return path + "/";
    }

    return path;
  }
}
