package org.jetbrains.datalore.rules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.datalore.rules.reports.reportFileNameDoesntMatchComponentName
import org.jetbrains.datalore.rules.reports.reportFileNameIsNotInCamelCase
import org.jetbrains.datalore.rules.utils.camelcase
import org.jetbrains.datalore.rules.utils.capitalizeFirst
import org.jetbrains.datalore.rules.utils.isFirstLetterLow
import org.jetbrains.datalore.rules.utils.isReactComponent
import org.jetbrains.kotlin.psi.KtClass

class FileWithReactComponentNameCheck(config: Config): Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        Severity.Style,
        "Check files containing React Component Class are following the name convention",
        Debt(mins = 1),
    )

    override fun visitClass(klass: KtClass) {
        if (klass.isReactComponent()) {
            val componentName = klass.name ?: return super.visitClass(klass)
            val componentFile = klass.containingFile
            val fileName = componentFile.name.substringAfterLast('/').substringBeforeLast('.')

            if (fileName.capitalizeFirst() != componentName.capitalizeFirst()) {
                reportFileNameDoesntMatchComponentName(componentName, componentFile)
                return super.visitClass(klass)
            }

            if (!fileName.isFirstLetterLow()) {
                reportFileNameIsNotInCamelCase(componentName, componentFile)
            }

        }

        super.visitClass(klass)
    }
}