package org.jetbrains.datalore.rules.reports

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Rule
import org.jetbrains.datalore.rules.utils.capitalizeFirst
import org.jetbrains.kotlin.com.intellij.psi.PsiFile

fun Rule.reportFileNameIsNotInCamelCase(componentName: String, file: PsiFile) {
    report(CodeSmell(
        issue = issue,
        entity = Entity.from(file),
        message = "The file name should be in camel case (the first letter is in lowercase). So, it should be '${componentName.capitalizeFirst()}.kt'."
    ))
}