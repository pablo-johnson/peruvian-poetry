package com.johnson.pablo.poesiaperuana.data.datasource.db.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by pjohnson on 3/06/17.
 */
public class DateConverter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
