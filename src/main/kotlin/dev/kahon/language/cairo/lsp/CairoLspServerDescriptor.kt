package dev.kahon.language.cairo.lsp

import com.intellij.execution.ExecutionException
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationType
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import com.intellij.platform.lsp.api.customization.LspFormattingSupport
import dev.kahon.language.cairo.CairoBundle
import dev.kahon.language.cairo.CairoFileType
import dev.kahon.language.cairo.ide.showNotification
import dev.kahon.language.cairo.settings.CairoSettings
import dev.kahon.language.cairo.settings.CairoSettingsConfigurable
import org.eclipse.lsp4j.MessageParams
import org.eclipse.lsp4j.MessageType
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification
import org.eclipse.lsp4j.services.LanguageServer
import java.io.File


class CairoLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Cairo") {
    override fun isSupportedFile(file: VirtualFile) = file.fileType == CairoFileType

    override fun createCommandLine(): GeneralCommandLine {
        val settings = CairoSettings.getInstance(project)
        val scarbPath = settings.homePath

        if (!File(scarbPath).exists()) {
            showNotification(
                project = project,
                title = CairoBundle.message("settings.cairo.scarb.path.required.title"),
                content = CairoBundle.message("settings.cairo.scarb.path.required.content"),
                type = NotificationType.WARNING,
                NotificationAction.create(CairoBundle.message("settings.cairo.scarb.path.required.configure")) { _, notification ->
                    ShowSettingsUtil.getInstance()
                        .showSettingsDialog(project, CairoSettingsConfigurable::class.java)
                    notification.expire()
                },
            )
            throw ExecutionException(CairoBundle.message("settings.cairo.scarb.not.found"))
        }

        return GeneralCommandLine().apply {
            withCharset(Charsets.UTF_8)
            withExePath(scarbPath)
            addParameter("cairo-language-server")
            addParameter("--stdio")
        }
    }

    override val lspGoToDefinitionSupport = true

    override val lspFormattingSupport = object : LspFormattingSupport() {
        override fun shouldFormatThisFileExclusivelyByServer(
            file: VirtualFile,
            ideCanFormatThisFileItself: Boolean,
            serverExplicitlyWantsToFormatThisFile: Boolean
        ) =
            file.fileType is CairoFileType
    }

    override fun createLsp4jClient(handler: LspServerNotificationsHandler): Lsp4jClient {
        val client = CairoLsp4jClient(handler)
        return client
    }

    override val lsp4jServerClass: Class<out LanguageServer> = CairoLanguageServer::class.java
}


class CairoLsp4jClient(handler: LspServerNotificationsHandler) : Lsp4jClient(
    handler
) {
    @JsonNotification("scarb/resolving-start")
    fun onResolvingStart() {
        this.showMessage(MessageParams(MessageType.Info, CairoBundle.message("scarb.resolving.start")))
    }

    @JsonNotification("scarb/resolving-finish")
    fun onResolvingFinish() {
        this.showMessage(MessageParams(MessageType.Info, CairoBundle.message("scarb.resolving.finished")))
    }
}