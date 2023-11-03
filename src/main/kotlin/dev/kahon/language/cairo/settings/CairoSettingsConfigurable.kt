package dev.kahon.language.cairo.settings

import com.intellij.openapi.components.service
import com.intellij.openapi.options.BoundSearchableConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import dev.kahon.language.cairo.CairoBundle
import dev.kahon.language.cairo.CairoServerService

class CairoSettingsConfigurable(internal val project: Project) :
    BoundSearchableConfigurable(
        CairoBundle.message("configurable.name"),
        CairoBundle.message("configurable.name")
    ) {
    override fun createPanel(): DialogPanel {
        val settings: CairoSettings = CairoSettings.getInstance(project)
        val cairoServerService = project.service<CairoServerService>()

        return panel {
            row(CairoBundle.message("settings.cairo.scarb.path.chooser.title")) {
                textFieldWithBrowseButton(CairoBundle.message("settings.cairo.scarb.path.chooser.description")) {
                    fileChosen(
                        it
                    )
                }
                    .bindText(settings::homePath)
            }

            onApply {
                cairoServerService.restartCairoServer()
                cairoServerService.notifyRestart()
            }
        }

    }

    fun fileChosen(file: VirtualFile): String {
        return file.path
    }
}