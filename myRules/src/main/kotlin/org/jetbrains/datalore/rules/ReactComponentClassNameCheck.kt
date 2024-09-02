package org.jetbrains.datalore.rules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.datalore.rules.reports.reportClassContainsComponentInItsName
import org.jetbrains.datalore.rules.reports.reportClassNameIsNotCapitalized
import org.jetbrains.datalore.rules.reports.reportComponentClassShouldBeTopLevelClass
import org.jetbrains.datalore.rules.utils.capitalizeFirst
import org.jetbrains.datalore.rules.utils.isFirstLetterCapital
import org.jetbrains.datalore.rules.utils.isReactComponent
import org.jetbrains.kotlin.psi.KtClass

class ReactComponentClassNameCheck(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        Severity.Style,
        "Check React Component Class is following the name convention",
        Debt.FIVE_MINS,
    )

    override fun visitClass(klass: KtClass) {
        if (klass.isReactComponent()) {
            val componentName = klass.name ?: return super.visitClass(klass)
            val containingFile = klass.containingFile

            if (!componentName.isFirstLetterCapital()) {
                reportClassNameIsNotCapitalized(componentName, klass)
            }

            if (componentName.contains("Component")) {
                reportClassContainsComponentInItsName(componentName, klass)
            }

            if (klass.parent != containingFile) {
                reportComponentClassShouldBeTopLevelClass(klass)
            }

            withAutoCorrect {
                if (!componentName.isFirstLetterCapital()) {
                    klass.setName(componentName.capitalizeFirst())
                }

                if (componentName.contains("Component")) {
                    klass.setName(componentName.replace("Component", ""))
                }
            }
        }

        super.visitClass(klass)
    }
}