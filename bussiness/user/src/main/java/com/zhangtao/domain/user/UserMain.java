package com.zhangtao.domain.user;

import java.util.Date;

public class UserMain {
    private Long mId;

    private Integer mFlag;

    private Integer mMultiLogin;

    private Date mCreateTime;

    private Date mModifyTime;

    private Date mTs;

    public UserMain(Long mId, Integer mFlag, Integer mMultiLogin, Date mCreateTime, Date mModifyTime, Date mTs) {
        this.mId = mId;
        this.mFlag = mFlag;
        this.mMultiLogin = mMultiLogin;
        this.mCreateTime = mCreateTime;
        this.mModifyTime = mModifyTime;
        this.mTs = mTs;
    }

    public UserMain() {
        super();
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public Integer getmFlag() {
        return mFlag;
    }

    public void setmFlag(Integer mFlag) {
        this.mFlag = mFlag;
    }

    public Integer getmMultiLogin() {
        return mMultiLogin;
    }

    public void setmMultiLogin(Integer mMultiLogin) {
        this.mMultiLogin = mMultiLogin;
    }

    public Date getmCreateTime() {
        return mCreateTime;
    }

    public void setmCreateTime(Date mCreateTime) {
        this.mCreateTime = mCreateTime;
    }

    public Date getmModifyTime() {
        return mModifyTime;
    }

    public void setmModifyTime(Date mModifyTime) {
        this.mModifyTime = mModifyTime;
    }

    public Date getmTs() {
        return mTs;
    }

    public void setmTs(Date mTs) {
        this.mTs = mTs;
    }
}