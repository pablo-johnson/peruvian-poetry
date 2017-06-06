package com.johnson.pablo.poesiaperuana.data.datasource.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.johnson.pablo.poesiaperuana.data.datasource.db.entities.VersionEntity;

import io.reactivex.Flowable;

/**
 * Created by pjohnson on 2/06/17.
 */
@Dao
public interface VersionDao {

    @Query("SELECT * FROM version order by version desc limit 1 ")
    Flowable<VersionEntity> getMaxVersion();

    @Insert
    void insert(VersionEntity version);

}
