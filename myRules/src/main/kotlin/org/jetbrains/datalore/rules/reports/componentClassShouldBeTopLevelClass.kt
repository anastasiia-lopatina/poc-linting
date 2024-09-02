package org.jetbrains.datalore.rules.reports

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Rule
import org.jetbrains.kotlin.psi.KtClass

fun Rule.reportComponentClassShouldBeTopLevelClass(klass: KtClass) {
    report(
        CodeSmell(
            issue = issue,
            entity = Entity.from(klass),
            message = "All the component classes should be defined on top level (not as nested/inner classes).",
        )
    )
}