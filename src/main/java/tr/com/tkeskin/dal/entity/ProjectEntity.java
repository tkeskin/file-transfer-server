package tr.com.tkeskin.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import tr.com.tkeskin.util.Const;

import javax.persistence.*;
import java.util.UUID;

@Data
@SQLDelete(
        sql = "UPDATE file_transfer." + Const.TABLE_PROJECT + " SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@EqualsAndHashCode(of = {"id"}, callSuper = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = Const.TABLE_PROJECT)
@Entity
public class ProjectEntity extends AuditModel {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "DOWNLOAD_PATH", unique = true)
    private String downloadPath;

}
