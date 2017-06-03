package com.johnson.pablo.poesiaperuana.domain.model;

/**
 * Created by pjohnson on 3/06/17.
 */
public class Version {

    private Integer version;

    public Version() {
    }

    public Version(int i) {
        version = i;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
