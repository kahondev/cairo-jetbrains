package dev.kahon.language.cairo.lsp

import org.eclipse.lsp4j.CompletionParams
import org.eclipse.lsp4j.jsonrpc.services.JsonRequest
import org.eclipse.lsp4j.services.LanguageServer
import java.util.concurrent.CompletableFuture

class Completion(val content: String)
class CompletionResponse {
    val uri: String = ""
}

interface CairoLanguageServer: LanguageServer {
    @JsonRequest("vfs/provide")
    fun provideTextDocumentContent(params: CompletionParams): CompletableFuture<CompletionResponse>;
}