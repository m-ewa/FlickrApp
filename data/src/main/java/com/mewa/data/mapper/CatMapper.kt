package com.mewa.data.mapper

import com.mewa.data.extensions.parseDate
import com.mewa.data.extensions.removeHtmlTagsAndReplaceLastColon
import com.mewa.data.local.db.entity.CatEntity
import com.mewa.data.remote.dto.CatsDto
import com.mewa.domain.model.Cat
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