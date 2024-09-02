package org.jetbrains.datalore.rules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.datalore.rules.reports.*
import org.jetbrains.datalore.rules.utils.capitalizeFirst
import org.jetbrains.datalore.rules.utils.isReactComponent
import org.jetbrains.datalore.rules.utils.isReactProps
import org.jetbrains.kotlin.psi.KtClass

class ReactPropsClassNameCheck(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        Severity.Style,
        "Check React Props Class is following the name convention",
        Debt.FIVE_MINS,
    )

    override fun visitClass(klass: KtClass) {
        if (klass.isReactProps()) {
            val propsName = klass.name ?: return super.visitClass(klass)
            val containingFile = klass.containingFile

            if (!propsName.endsWith("Props")) {
                reportPropsClassShouldEndWithPropsSuffix(klass)
            }

            if (klass.parent != containingFile) {
                reportPropsClassShouldBeTopLevelClass(klass)
            }

            val supposedComponentName = propsName.substringBefore("Props")
            val componentClass = containingFile.children.find {
                it is KtClass && it.isReactComponent()
            } as? KtClass
            val actualComponentName = componentClass?.name?.capitalizeFirst()

            if (componentClass == null) {
                reportPropsClassShouldBeInTheSameFileAsComponentClass(klass)
            } else if (actualComponentName != null && actualComponentName != supposedComponentName) {
                reportPropsShouldBeNamedTheSameAsTheComponentClass(actualComponentName, klass)
            }

            withAutoCorrect {
                if (!propsName.endsWith("Props")) {
                    klass.setName(propsName + "Props")
                }

                if (actualComponentName != null && actualComponentName != supposedComponentName) {
                    klass.setName(actualComponentName + "Props")
                }
            }
        }

        super.visitClass(klass)
    }
}