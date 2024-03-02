package com.mewa.flickrapp.data.mapper

import com.mewa.flickrapp.data.local.db.entity.CatEntity
import com.mewa.flickrapp.data.remote.dto.CatsDto
import com.mewa.flickrapp.domain.model.Cat
import com.mewa.flickrapp.extensions.parseDate
import com.mewa.flickrapp.extensions.removeHtmlTagsAndReplaceLastColon
import java.util.Date

fun CatsDto.CatDto.toEntity(): CatEntity {
    return CatEntity(
        imageUrl = media?.imageUrl ?: "",
        description = descriptionHtml?.removeHtmlTagsAndReplaceLastColon() ?: "",
        link = link ?: "",
        published = publishedString?.parseDate() ?: Date(0)
    )
}

fun CatEntity.toDomain(): Cat {
    return Cat(
        imageUrl = imageUrl,
        description = description,
        link = link,
        published = published,
    )
}
