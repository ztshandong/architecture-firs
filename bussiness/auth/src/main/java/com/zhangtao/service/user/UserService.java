package com.zhangtao.service.user;

import com.zhangtao.annotation.AuthToken;
import com.zhangtao.annotation.UserDBReadDataSource;
import com.zhangtao.dao.user.UserDetailsMapper;
import com.zhangtao.domain.user.UserDetails;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

@Service
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class UserService {

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    //    @Transactional

    @UserDBReadDataSource
    public UserDetails findById(Long id) {
        UserDetails u = this.userDetailsMapper.selectByPrimaryKey(id);
        return u;
    }

    @UserDBReadDataSource
    public UserDetails getUserInfoBeforeLogin(String dAccount) {
        try {
            UserDetails u = this.userDetailsMapper.getUserInfoBeforeLogin(dAccount);
            return u;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private UserService getService() {
        if (AopContext.currentProxy() != null) {
            return (UserService) AopContext.currentProxy();
        } else {
            return this;
        }

    }

}
