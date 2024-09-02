package org.jetbrains.datalore.rules.reports

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Rule
import org.jetbrains.kotlin.psi.KtClass

fun Rule.reportClassContainsComponentInItsName(
    componentName: String,
    klass: KtClass,
) {
    report(
        CodeSmell(
            issue = issue,
            entity = Entity.from(klass),
            message = "The class name $componentName should not contain 'Component' in its name",
        )
    )
}
