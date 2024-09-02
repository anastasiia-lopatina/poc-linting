package org.jetbrains.datalore.rules.reports

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Rule
import org.jetbrains.kotlin.psi.KtClass

fun Rule.reportClassNameIsNotCapitalized(
    componentName: String,
    klass: KtClass,
) {
    report(
        CodeSmell(
            issue = issue,
            entity = Entity.from(klass),
            message = "The class name $componentName does not start with a capital letter.",
        )
    )
}
