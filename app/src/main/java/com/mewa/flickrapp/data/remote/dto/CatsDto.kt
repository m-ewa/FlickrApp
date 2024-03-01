package com.mewa.flickrapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatsDto(
    @Json(name = "title") val title: String?,
    @Json(name = "link") val link: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "modified") val modified: String?,
    @Json(name = "generator") val generator: String?,
    @Json(name = "items") val items: List<CatDto>?
) {

    @JsonClass(generateAdapter = true)
    data class CatDto(
        @Json(name = "title") val title: String?,
        @Json(name = "link") val link: String?,
        @Json(name = "media") val media: MediaDto?,
        @Json(name = "date_taken") val dateTaken: String?,
        @Json(name = "description") val descriptionHtml: String?,
        @Json(name = "published") val publishedString: String?,
        @Json(name = "author") val author: String?,
        @Json(name = "author_id") val authorId: String?,
        @Json(name = "tags") val tags: String?
    ) {

        @JsonClass(generateAdapter = true)
        data class MediaDto(
            @Json(name = "m") val imageUrl: String?
        )
    }
}