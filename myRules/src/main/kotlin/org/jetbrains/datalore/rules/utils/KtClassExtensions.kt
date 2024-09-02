package org.jetbrains.datalore.rules.utils

import org.jetbrains.kotlin.psi.KtClass

fun KtClass.isReactComponent(): Boolean =
    // TODO: It's a simplified variant. Should be checked by fully qulified name
    superTypeListEntries.any { it.typeAsUserType?.referencedName == "RComponent" }

fun KtClass.isReactProps(): Boolean =
    // TODO: It's a simplified variant. Should be checked by fully qulified name
    superTypeListEntries.any { it.typeAsUserType?.referencedName == "RProps" }

fun KtClass.isReactState(): Boolean =
    // TODO: It's a simplified variant. Should be checked by fully qulified name
    superTypeListEntries.any { it.typeAsUserType?.referencedName == "RState" }