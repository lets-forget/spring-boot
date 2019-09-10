package com.kang.realm;

import com.kang.bean.User;
import com.kang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShiroRealm extends AuthenticatingRealm {

    @Autowired
    private UserService userService;

    SimpleAuthenticationInfo info=null;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        //获取用户名
        String username=usernamePasswordToken.getUsername();

        //加盐
        ByteSource salt = ByteSource.Util.bytes(username);
        //密码
        String newPs = new SimpleHash("MD5", usernamePasswordToken.getPassword(), salt, 1024).toHex();

        //查询数据库是否有该用户名
        User user = userService.selectByName(username);
        if (user==null){
            throw new UnknownAccountException("用户名不存在");
        }else if (user.getStatus()==0){
            throw new LockedAccountException("您的账号未激活，请去邮箱激活吧");
        }else if (!newPs.equals(user.getPassword())){
            throw new ExpiredCredentialsException("密码错误");
        } else{
            // 如果查询到了，封装查询结果，返回给我们的调用
            Object principal =  user.getUsername();
            Object credentials = user.getPassword();
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.
                            getRequestAttributes()).getRequest();
            HttpSession session=request.getSession();//创建session对象
            session.setAttribute("user",user);

            String realmName=this.getName();

            // 将账户名，密码，盐值，realmName实例化到SimpleAuthenticationInfo中交给Shiro来管理
            return  info=new SimpleAuthenticationInfo(principal,credentials,salt,realmName);

        }
    }

}
