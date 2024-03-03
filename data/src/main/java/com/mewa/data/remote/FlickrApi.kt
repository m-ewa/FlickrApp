package com.mewa.data.remote

import com.mewa.data.remote.dto.CatsDto
import retrofit2.Response
import retrofit2.http.GET

interface FlickrApi {

    companion object {
        const val BASE_URL = "https://api.flickr.com/"
    }

    @GET("services/feeds/photos_public.gne/?format=json&tags=cat&nojsoncallback=1")
    suspend fun downloadCats(): Response<CatsDto>
}