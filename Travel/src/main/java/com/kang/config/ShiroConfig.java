package com.kang.config;

import com.kang.realm.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        SecurityManager securityManager=securityManager();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //设置拦截器
        Map<String,String> map=new LinkedHashMap<>();

        map.put("/user/login","anon");
        map.put("/user/register","anon");
        map.put("/login","anon");
        map.put("/header","anon");
        map.put("/footer","anon");
        map.put("/register","anon");
        map.put("/register_ok","anon");
        map.put("/sessionCode","anon");
        map.put("/user/checkCode","anon");
        map.put("/category/selectAll","anon");
        map.put("/user/echoName","anon");
        map.put("/user/isUsername","anon");
        map.put("/statics/**","anon");

        map.put("/index","user");
        map.put("/route/selectthemeAll","user");
        map.put("/route/selectCountAll","user");
        map.put("/route/selectDescAll","user");
        //配置退出过滤器
        map.put("/user/logout","logout");

        map.put("/**", "authc");

        //剩余全部拦截


        //默认访问登录界面
        shiroFilterFactoryBean.setLoginUrl("/login");

        //登录成功界面
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //没有权限的界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置Realm
        securityManager.setRealm(myShiroRealm());
        //注入缓存管理器
        //配置记住我 参考博客：
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean(name = "myShiroRealm")
    public ShiroRealm myShiroRealm(){
        ShiroRealm myShiroRealm = new ShiroRealm();
        //设置加密的凭证
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 密码匹配凭证管理器
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 采用MD5方式加密
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 设置加密次数
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager=new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(cookie());
        return cookieRememberMeManager;
    }

    public SimpleCookie cookie(){
        SimpleCookie simpleCookie=new SimpleCookie();
        simpleCookie.setMaxAge(86400);
        simpleCookie.setName("rememberMe");
        return simpleCookie;
    }
}
