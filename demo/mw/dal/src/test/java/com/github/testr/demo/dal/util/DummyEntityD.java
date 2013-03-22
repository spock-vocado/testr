package com.github.testr.demo.dal.util;

@BusinessEntity(keys = {"code"}, addToString = {"student"})
public class DummyEntityD extends AbstractBusinessEntity {

    private String code;
    private DummyEntityC student;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DummyEntityC getStudent() {
        return student;
    }

    public void setStudent(DummyEntityC student) {
        this.student = student;
    }
}
