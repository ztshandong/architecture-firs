package com.zhangtao.service.user;

import com.zhangtao.annotation.AliUserDBWriteDataSource;
import com.zhangtao.annotation.AuthToken;
import com.zhangtao.annotation.UserDBReadDataSource;
import com.zhangtao.annotation.UserDBWriteDataSource;
import com.zhangtao.dao.user.StudentMapper;
import com.zhangtao.dao.user.UserDetailsMapper;
import com.zhangtao.domain.user.Student;
import com.zhangtao.domain.user.UserDetails;
import com.zhangtao.util.Snowflake;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class UserService {

    @Autowired
    private UserDetailsMapper userDetailsMapper;
    @Autowired
    private StudentMapper studentMapper;
    //    @Transactional



    @UserDBWriteDataSource
    public int adduserDetails() throws Exception {
        try {
            UserDetails userDetails = new UserDetails();
            userDetails.setdId(Snowflake.nextId());
            userDetails.setdMid(Snowflake.nextId());
            userDetails.setdAccount("18017268474");
            userDetails.setdName("zhangsan");
            userDetails.setdPassword("ddpassword");
            userDetails.setdSalt("abc");
            userDetails.setdCreateTime(new Date());
            userDetails.setdModifyTime(new Date());
            userDetails.setdTs(new Date());
            userDetailsMapper.insert(userDetails);
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @AliUserDBWriteDataSource
    public int addStudent() throws Exception {
        try {
            Student student = new Student();
            student.setClassId(10);
            student.setsId(Snowflake.nextId());
            student.setsName("zhangsan");
            studentMapper.insert(student);
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

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
