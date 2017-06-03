package com.johnson.pablo.poesiaperuana.data.datasource.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.johnson.pablo.poesiaperuana.data.datasource.db.converters.DateConverter;
import com.johnson.pablo.poesiaperuana.data.datasource.db.daos.PoetDao;
import com.johnson.pablo.poesiaperuana.data.datasource.db.daos.VersionDao;
import com.johnson.pablo.poesiaperuana.data.datasource.db.entities.PoetEntity;
import com.johnson.pablo.poesiaperuana.data.datasource.db.entities.VersionEntity;

/**
 * Created by pjohnson on 2/06/17.
 */
@Database(entities = {PoetEntity.class, VersionEntity.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class PoetDatabase extends RoomDatabase {
    private static PoetDatabase INSTANCE;

    public abstract PoetDao poetDao();

    public abstract VersionDao versionDao();

    public static PoetDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), PoetDatabase.class, "PoetDatabase")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }
}
