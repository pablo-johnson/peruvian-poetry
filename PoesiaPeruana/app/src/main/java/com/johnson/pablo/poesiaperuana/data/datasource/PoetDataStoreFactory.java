package com.johnson.pablo.poesiaperuana.data.datasource;

import com.johnson.pablo.poesiaperuana.data.datasource.db.DbPoetDataStore;
import com.johnson.pablo.poesiaperuana.data.datasource.rest.RestPoetsDataStore;


/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class PoetDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 2;

    public PoetDataStoreFactory() {
    }

    public PoetDataStore create(int dataSource) {
        PoetDataStore PoetDataStore = null;

        switch (dataSource) {
            case CLOUD:
                PoetDataStore = new RestPoetsDataStore();
                break;
            case DB:
                PoetDataStore = new DbPoetDataStore();
                break;
        }
        return PoetDataStore;
    }
}
