package tr.com.aa.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Const {

  public final int POOL_SIZE = 10;

  public final String TABLE_FTP_SERVER = "ftp_server";
  public final String TABLE_PROJECT = "project";
  public final String TABLE_USER = "user_ftp";
  public final String TABLE_JOB = "job";
  public final String TABLE_JOB_DESTINATION = "job_destination";
  public final String TABLE_JOB_STATUS = "job_status";

  public final String JSON = "application/json";

  public class Request {

    public static final String FTP_SERVER = "/ftp-server";
    public static final String DELETE_FTP_SERVER = "/ftp-server/{id}";
    public static final String JOB = "/job";
    public static final String START_JOB = "/start-job/{id}";
    public static final String DELETE_JOB = "/delete-job/{id}";
    public static final String JOB_DESTINATION = "/job-destination/{id}";
    public static final String START_DOWNLOAD = "/start-download/{id}";
    public static final String TEST_CONNECTION = "/test-connection";
    public static final String PROJECT = "/project";
    public static final String DELETE_PROJECT = "/project/{id}";
  }
}