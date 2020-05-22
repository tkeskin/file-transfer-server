package tr.com.aa.dal.entity;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import tr.com.aa.util.Const;

@SuppressWarnings("PMD")
@Audited
@Data
@SQLDelete(sql = "UPDATE aa_file_transfer." + Const.TABLE_JOB + " SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@EqualsAndHashCode(of = {"id"}, callSuper = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = Const.TABLE_JOB)
@Entity
public class JobEntity extends AuditModel {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "CREATED_BY_ID")
  private UUID createdById;

  @Column(name = "AUTO_START")
  private boolean autoStart;

  @Column(name = "JOB_STATUS_ID")
  private Integer jobStatus = 0;

  @OnDelete(action = OnDeleteAction.NO_ACTION)
  @OneToMany(
      mappedBy = "jobEntity",
      fetch = FetchType.LAZY
  )
  Set<JobDestinationEntity> jobDestinationEntities;
}
