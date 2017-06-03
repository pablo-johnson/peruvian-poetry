package com.johnson.pablo.poesiaperuana;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.johnson.pablo.poesiaperuana.data.datasource.db.PoetDatabase;
import com.johnson.pablo.poesiaperuana.data.datasource.db.daos.PoetDao;
import com.johnson.pablo.poesiaperuana.data.datasource.db.entities.PoetEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.assertThat;

/**
 * Created by pjohnson on 3/06/17.
 */
@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {
    private PoetDao mUserDao;
    private PoetDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, PoetDatabase.class).build();
        mUserDao = mDb.poetDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        PoetEntity poet = new PoetEntity();
        poet.setName("george");
        mUserDao.insert(poet);
        Flowable<PoetEntity> byName = mUserDao.findById(0);
        TestSubscriber<PoetEntity> test = byName.test();
        test.assertValue(poet);
    }
}
