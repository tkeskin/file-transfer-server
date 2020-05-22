package tr.com.aa.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tr.com.aa.util.Const;

@Data
@EqualsAndHashCode(of = {"id"})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = Const.TABLE_JOB_STATUS)
@Entity
public class JobStatusEntity {

  @Id
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NAME")
  private String name;

}
