package org.jetbrains.datalore.rules.reports

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Rule
import org.jetbrains.datalore.rules.utils.camelcase
import org.jetbrains.kotlin.com.intellij.psi.PsiFile

fun Rule.reportFileNameDoesntMatchComponentName(componentName: String, file: PsiFile) {
    report(CodeSmell(
        issue = issue,
        entity = Entity.from(file),
        message = "The file name doesn't match React component name. It should be '${componentName.camelcase()}.kt'."
    ))
}