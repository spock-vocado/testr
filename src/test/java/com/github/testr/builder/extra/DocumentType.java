package com.github.testr.builder.extra;

public class DocumentType {

    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DocumentType");
        sb.append("{code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
