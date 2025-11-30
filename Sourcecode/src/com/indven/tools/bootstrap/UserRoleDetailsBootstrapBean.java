package com.indven.tools.bootstrap;

import javax.persistence.*;

@Entity
@Table(name = "omds_userroledetails")
public class UserRoleDetailsBootstrapBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "RoleMasterFkId")
    private Long RoleMasterFkId;

    @Column(name = "UserLoginDetailsFkId")
    private Long UserLoginDetailsFkId;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getRoleMasterFkId() {
        return RoleMasterFkId;
    }

    public void setRoleMasterFkId(Long RoleMasterFkId) {
        this.RoleMasterFkId = RoleMasterFkId;
    }

    public Long getUserLoginDetailsFkId() {
        return UserLoginDetailsFkId;
    }

    public void setUserLoginDetailsFkId(Long UserLoginDetailsFkId) {
        this.UserLoginDetailsFkId = UserLoginDetailsFkId;
    }

}
