package com.zhangtao.domain.user;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class UserDetails {

    private Long dId;

    private Long dMid;

    private String dAccount;

    private String dName;

    private String dPassword;

    private String dSalt;

    private Date dCreateTime;

    private Date dModifyTime;

    private Date dTs;

    public UserDetails(Long dId, Long dMid, String dAccount, String dName, String dPassword, String dSalt, Date dCreateTime, Date dModifyTime, Date dTs) {
        this.dId = dId;
        this.dMid = dMid;
        this.dAccount = dAccount;
        this.dName = dName;
        this.dPassword = dPassword;
        this.dSalt = dSalt;
        this.dCreateTime = dCreateTime;
        this.dModifyTime = dModifyTime;
        this.dTs = dTs;
    }

    public UserDetails() {
        super();
    }

    public Long getdId() {
        return dId;
    }

    public void setdId(Long dId) {
        this.dId = dId;
    }

    public Long getdMid() {
        return dMid;
    }

    public void setdMid(Long dMid) {
        this.dMid = dMid;
    }

    public String getdAccount() {
        return dAccount;
    }

    public void setdAccount(String dAccount) {
        this.dAccount = dAccount == null ? null : dAccount.trim();
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName == null ? null : dName.trim();
    }

    public String getdPassword() {
        return dPassword;
    }

    public void setdPassword(String dPassword) {
        this.dPassword = dPassword == null ? null : dPassword.trim();
    }

    public String getdSalt() {
        return dSalt;
    }

    public void setdSalt(String dSalt) {
        this.dSalt = dSalt == null ? null : dSalt.trim();
    }

    public Date getdCreateTime() {
        return dCreateTime;
    }

    public void setdCreateTime(Date dCreateTime) {
        this.dCreateTime = dCreateTime;
    }

    public Date getdModifyTime() {
        return dModifyTime;
    }

    public void setdModifyTime(Date dModifyTime) {
        this.dModifyTime = dModifyTime;
    }

    public Date getdTs() {
        return dTs;
    }

    public void setdTs(Date dTs) {
        this.dTs = dTs;
    }
}