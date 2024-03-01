package com.mewa.flickrapp.data.mapper

import com.mewa.flickrapp.data.local.db.entity.CatEntity
import com.mewa.flickrapp.data.remote.dto.CatsDto
import com.mewa.flickrapp.domain.model.Cat

fun CatsDto.CatDto.toEntity(index: Int): CatEntity {
    return CatEntity(
        id = index + 1,
        imageUrl = media?.imageUrl ?: "",
        description = descriptionHtml?.removeHtmlTagsAndReplaceLastColon() ?: "",
        link = link ?: "",
        published = publishedString ?: ""
    )
}

fun CatEntity.toDomain(): Cat {
    return Cat(
        id = id,
        imageUrl = imageUrl,
        description = description,
        link = link,
    )
}

private fun String.removeHtmlTagsAndReplaceLastColon(): String {
    val removeHtmlTagsPattern = "<.*?>"
    var result = this.replace(Regex(removeHtmlTagsPattern), "").trim()
    if (result.endsWith(":")) {
        result = result.dropLast(1) + "."
    }
    return result
}
