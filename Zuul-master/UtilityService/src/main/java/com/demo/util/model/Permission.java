package com.demo.util.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Table(name = "permission")
@Entity
public class Permission implements Serializable {


    private static final long serialVersionUID = 8087275050725156377L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "permission_id")
    private int permissionId;

    @Column(name = "permission_name")
    private String permissionName;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Permission( ) {
        super( );
    }

    public Permission( int permissionId, String permissionName ) {
        super( );
        this.permissionId = permissionId;
        this.permissionName = permissionName;
    }

    public int getPermissionId( ) {
        return permissionId;
    }

    public void setPermissionId( int permissionId ) {
        this.permissionId = permissionId;
    }

    public String getPermissionName( ) {
        return permissionName;
    }

    public void setPermissionName( String permissionName ) {
        this.permissionName = permissionName;
    }

    public Set<Role> getRoles( ) {
        return roles;
    }

    public void setRoles( Set<Role> roles ) {
        this.roles = roles;
    }


}
