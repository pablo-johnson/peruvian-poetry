package com.johnson.pablo.poesiaperuana.data.datasource.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.johnson.pablo.poesiaperuana.data.datasource.db.entities.PoetEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by pjohnson on 2/06/17.
 */
@Dao
public interface PoetDao {

    @Query("SELECT * FROM poets")
    Flowable<List<PoetEntity>> loadAll();

    @Query("SELECT * FROM poets where isFavorite==1")
    Flowable<List<PoetEntity>> loadFavorites();

    @Query("SELECT * FROM poets WHERE uid IN (:poetsIds)")
    Flowable<List<PoetEntity>> loadAllByIds(int[] poetsIds);

    @Query("SELECT * FROM poets WHERE uid== :id ")
    Flowable<PoetEntity> findById(int id);

    @Insert
    void insert(PoetEntity poet);

    @Insert
    void insertAll(List<PoetEntity> poets);

    @Delete
    void delete(PoetEntity poet);

    @Query("DELETE FROM poets")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePoet(PoetEntity poetEntity);
}
