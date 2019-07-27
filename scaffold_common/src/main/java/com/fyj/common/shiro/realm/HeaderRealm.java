package com.fyj.common.shiro.realm;

import com.fyj.Entity.system.response.ProfileResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

//公共的Realm：获取安全数据，构造权限信息
public class HeaderRealm extends AuthorizingRealm {

    public void setName(String name) {
        super.setName("headerRealm");
    }


    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取安全数据源
        ProfileResult result = (ProfileResult) principalCollection.getPrimaryPrincipal();
        //2.获取权限信息
        Set<String> apiPerm = (Set<String>) result.getRoles().get("apis");
        //3.构造权限数据，返回值
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(apiPerm);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
