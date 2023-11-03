package dev.kahon.language.cairo.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import dev.kahon.language.cairo.CairoFileType


class CairoLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(
        project: Project,
        file: VirtualFile,
        serverStarter: LspServerSupportProvider.LspServerStarter
    ) {
        if (file.fileType != CairoFileType) return

        serverStarter.ensureServerStarted(CairoLspServerDescriptor(project))
    }
}
