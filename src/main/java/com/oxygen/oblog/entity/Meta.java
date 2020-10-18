package com.oxygen.oblog.entity;

public class Meta {
    private Integer mid;

    private String metaName;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMetaName() {
        return metaName;
    }

    public void setMetaName(String metaName) {
        this.metaName = metaName == null ? null : metaName.trim();
    }
}