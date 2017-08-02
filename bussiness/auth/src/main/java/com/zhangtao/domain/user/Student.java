package com.zhangtao.domain.user;

public class Student {
    private Long sId;

    private String sName;

    private Integer classId;

    private String sJsonfield;

    public Student(Long sId, String sName, Integer classId, String sJsonfield) {
        this.sId = sId;
        this.sName = sName;
        this.classId = classId;
        this.sJsonfield = sJsonfield;
    }

    public Student() {
        super();
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getsJsonfield() {
        return sJsonfield;
    }

    public void setsJsonfield(String sJsonfield) {
        this.sJsonfield = sJsonfield == null ? null : sJsonfield.trim();
    }
}