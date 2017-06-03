package com.johnson.pablo.poesiaperuana.data.datasource.rest;

import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.domain.model.Version;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public interface PoetsApi {

    @GET("poetas/.json")
    Flowable<List<Poet>> loadPoets();

    @GET("version/.json")
    Flowable<Version> getPoetsVersion();
}
