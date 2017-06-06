package com.johnson.pablo.poesiaperuana.data.datasource.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.johnson.pablo.poesiaperuana.domain.model.Version;

/**
 * Created by pjohnson on 5/04/17.
 */
@Entity(tableName = "version")
public class VersionEntity {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private Double version;

    public VersionEntity() {
    }

    public VersionEntity(Version version) {
        this.version = version.getVersion();
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public Version toVersion() {
        Version version = new Version();
        version.setVersion(this.version);
        return version;
    }
}
