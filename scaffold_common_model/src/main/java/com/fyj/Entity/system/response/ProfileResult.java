package com.fyj.Entity.system.response;

import com.fyj.Entity.User;
import com.fyj.Entity.system.Permission;
import com.fyj.Entity.system.Role;
import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
public class ProfileResult implements Serializable, AuthCachePrincipal {

    private String userId;
    private String phone;
    private String company;
    private String companyId;
    private Map<String,Object> roles = new HashMap<>();

    /**
     *
     * @param user
     */
    public ProfileResult(User user, List<Permission> list) {
        this.userId = user.getUserId();
        this.phone = user.getPhone();
        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();

        for (Permission perm : list) {
            String code = perm.getCode();
            if(perm.getType() == 1) {
                menus.add(code);
            }else if(perm.getType() == 2) {
                points.add(code);
            }else {
                apis.add(code);
            }
        }
        this.roles.put("menus",menus);
        this.roles.put("points",points);
        this.roles.put("apis",apis);
    }
    public ProfileResult(User user) {
        this.userId = user.getUserId();
        this.phone = user.getPhone();
        Set<Role> roles = user.getRoles();
        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();
        for (Role role : roles) {
            Set<Permission> perms = role.getPermissions();
            for (Permission perm : perms) {
                String code = perm.getCode();
                if(perm.getType() == 1) {
                    menus.add(code);
                }else if(perm.getType() == 2) {
                    points.add(code);
                }else {
                    apis.add(code);
                }
            }
        }
        this.roles.put("menus",menus);
        this.roles.put("points",points);
        this.roles.put("apis",apis);
    }

    public String getAuthCacheKey() {
        return null;
    }

}
