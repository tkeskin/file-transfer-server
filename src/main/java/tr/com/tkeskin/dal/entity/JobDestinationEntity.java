package tr.com.tkeskin.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import tr.com.tkeskin.util.Const;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@SuppressWarnings("PMD")
@Audited
@Data
@EqualsAndHashCode(exclude = "jobEntity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = Const.TABLE_JOB_DESTINATION)
@Entity
public class JobDestinationEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "DOWNLOAD_PATH")
    private String downloadPath;

    @Column(name = "IS_DOwNLOAD")
    private Boolean download = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DOWNLOAD_DATETIME")
    private Date downloadDateTime;

    @Column(name = "DOWNLOAD_URL")
    private String downloadUrl;

    @Column(name = "UPLOAD_PATH")
    private String uploadPath;

    @Column(name = "IS_SEND")
    private Boolean send = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SEND_DATETIME")
    private Date sendDateTime;

    @Column(name = "FTP_SERVER_ID")
    private UUID ftpServerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_ID")
    private JobEntity jobEntity;

}