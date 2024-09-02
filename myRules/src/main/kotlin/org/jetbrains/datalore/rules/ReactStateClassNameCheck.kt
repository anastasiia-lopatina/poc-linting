package org.jetbrains.datalore.rules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.datalore.rules.reports.*
import org.jetbrains.datalore.rules.utils.isReactComponent
import org.jetbrains.datalore.rules.utils.isReactState
import org.jetbrains.kotlin.psi.KtClass

class ReactStateClassNameCheck(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        Severity.Style,
        "Check React State Class is following the name convention",
        Debt.FIVE_MINS,
    )

    override fun visitClass(klass: KtClass) {
        if (klass.isReactState()) {
            val stateName = klass.name ?: return super.visitClass(klass)
            val containingFile = klass.containingFile

            if (!stateName.endsWith("State")) {
                reportStateClassShouldEndWithStateSuffix(klass)
            }

            if (klass.parent != containingFile) {
                reportStateClassShouldBeTopLevelClass(klass)
            }

            val supposedComponentName = stateName.substringBefore("State")
            val componentClass = containingFile.children.find {
                it is KtClass && it.isReactComponent()
            } as? KtClass
            val actualComponentName = componentClass?.name

            if (componentClass == null) {
                reportStateClassShouldBeInTheSameFileAsComponentClass(klass)
            } else if (actualComponentName != null && actualComponentName != supposedComponentName) {
                reportStateShouldBeNamedTheSameAsTheComponentClass(actualComponentName, klass)
            }

            withAutoCorrect {
                if (!stateName.endsWith("State")) {
                    klass.setName(stateName + "State")
                }

                if (actualComponentName != null && actualComponentName != supposedComponentName) {
                    klass.setName(actualComponentName + "State")
                }
            }

            super.visitClass(klass)
        }
    }
}