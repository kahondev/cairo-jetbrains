package dev.kahon.language.cairo

import com.intellij.notification.NotificationType
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.LspServerManager
import dev.kahon.language.cairo.ide.showNotification
import dev.kahon.language.cairo.lsp.CairoLspServerSupportProvider
import dev.kahon.language.cairo.settings.CairoSettings
import java.io.File


@Service(Service.Level.PROJECT)
class CairoServerService(private val project: Project) {
    fun restartCairoServer() {
        LspServerManager.getInstance(project).stopAndRestartIfNeeded(CairoLspServerSupportProvider::class.java)
    }

    fun notifyRestart() {
        val settings = CairoSettings.getInstance(project)
        val (titleKey, contentKey, type) = if (File(settings.homePath).exists()) {
            Triple("settings.cairo.scarb.path.configured.title", "settings.cairo.scarb.path.configured.content", NotificationType.INFORMATION)
        } else {
            Triple("settings.cairo.scarb.path.required.title", "settings.cairo.scarb.path.required.content", NotificationType.WARNING)
        }

        val title = CairoBundle.message(titleKey)
        val content = CairoBundle.message(contentKey)

        showNotification(project, title, content, type)
    }
}