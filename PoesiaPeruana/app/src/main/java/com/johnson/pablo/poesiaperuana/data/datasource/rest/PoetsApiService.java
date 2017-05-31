package com.johnson.pablo.poesiaperuana.data.datasource.rest;

import com.johnson.pablo.poesiaperuana.domain.model.Poet;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public interface PoetsApiService {

    @GET("poetas/.json")
    Observable<List<Poet>> loadPoets();
}
