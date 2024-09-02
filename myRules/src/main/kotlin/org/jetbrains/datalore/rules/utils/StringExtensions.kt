package org.jetbrains.datalore.rules.utils

fun String.capitalizeFirst(): String =
    if (this.isEmpty()) {
        this
    } else {
        replaceFirstChar { char ->
            if (char.isLowerCase()) char.titlecase() else char.toString()
        }
    }

fun String.camelcase(): String =
if (this.isEmpty()) {
    this
} else {
    replaceFirstChar { char ->
        if (char.isUpperCase()) char.lowercase() else char.toString()
    }
}

fun String.isFirstLetterCapital(): Boolean =
    isNotEmpty() || first().isUpperCase()

fun String.isFirstLetterLow(): Boolean =
    isNotEmpty() || first().isLowerCase()