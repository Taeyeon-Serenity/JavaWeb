package cn.web.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Taeyeon-Serenity on 2017/4/9.
 */
public class User {
    private Long uid;
    private String username;
    private String password;

    private Set<Role> roles=new HashSet<Role>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
