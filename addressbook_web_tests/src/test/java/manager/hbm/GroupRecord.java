package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_list")
public class GroupRecord {

    @Id
    public int group_id;
    @Column(name = "group_name")
    public String name;
    public String group_header;
    public String group_footer;
}
