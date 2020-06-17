package tr.com.tkeskin.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Audited
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"CREATED_DATETIME", "CREATED_BY",
                "UPDATED_DATETIME", "UPDATED_BY", "DELETED"},
        allowGetters = true
)
public abstract class AuditModel implements Serializable {

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATETIME", nullable = false, updatable = false)
    @CreatedDate
    private Date createdDateTime;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATETIME", nullable = false)
    @LastModifiedDate
    private Date updatedDateTime;

    @Column(name = "DELETED")
    private boolean deleted;

}
