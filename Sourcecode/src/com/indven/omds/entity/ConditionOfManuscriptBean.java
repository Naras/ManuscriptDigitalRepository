package com.indven.omds.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "omds_conditionofmanuscript")
public class ConditionOfManuscriptBean  implements Serializable {

    private static final long serialVersionUID = -8940020506815003138L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name")
    private String name = null;

    @Column(name = "isdeleted")
    private Boolean isDeleted=Boolean.TRUE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
