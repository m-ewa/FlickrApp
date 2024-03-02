package com.mewa.flickrapp.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.parseDate(): Date? {
    val pattern = "yyyy-MM-dd'T'HH:mm:ss"
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    return format.parse(this)
}

fun String.removeHtmlTagsAndReplaceLastColon(): String {
    val removeHtmlTagsPattern = "<.*?>"
    var result = this.replace(Regex(removeHtmlTagsPattern), "").trim()
    if (result.endsWith(":")) {
        result = result.dropLast(1) + "."
    }
    return result
}