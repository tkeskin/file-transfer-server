package tr.com.tkeskin.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Const {

    public final int DOWNLOAD_POOL_SIZE = 10;
    public final int PENDING_JOB = 0;
    public final int COMPLETED_JOB = 3;

    public final String TABLE_FTP_SERVER = "ftp_server";
    public final String TABLE_PROJECT = "project";
    public final String TABLE_USER = "user_ftp";
    public final String TABLE_JOB = "job";
    public final String TABLE_JOB_DESTINATION = "job_destination";
    public final String TABLE_JOB_STATUS = "job_status";

    public class Request {

        public static final String FTP_SERVER = "/ftp-server";
        public static final String DELETE_FTP_SERVER = "/ftp-server/{id}";
        public static final String JOB = "/job";
        public static final String DELETE_JOB = "/delete-job/{id}";
        public static final String DETAIL_JOB = "/detail-job/{id}";
        public static final String QUERY_JOB = "/query-job/{createdById}";
        public static final String TEST_CONNECTION = "/test-connection";
        public static final String PROJECT = "/project";
        public static final String DELETE_PROJECT = "/project/{id}";
    }
}