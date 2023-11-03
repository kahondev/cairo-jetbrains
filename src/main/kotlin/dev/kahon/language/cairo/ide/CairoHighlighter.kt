package dev.kahon.language.cairo.ide

import com.intellij.ide.highlighter.custom.SyntaxTable
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import org.rust.ide.highlight.RsHighlighter
import org.rust.lang.core.lexer.RsHighlightingLexer


/**
 * We extend the RsHighlighter class as
 * Cairo is a Rust-like language.
 *
 * When semantic highlighting is implemented
 * on the JetBrains side, then we can remove this.
 */
class CairoHighlighter : SyntaxHighlighterBase() {

    private val rsHighlighter = RsHighlighter()
    override fun getHighlightingLexer(): RsHighlightingLexer = rsHighlighter.getHighlightingLexer()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> =
        rsHighlighter.getTokenHighlights(tokenType)

    companion object {
        val keywords = CairoKeywords()
    }
}


class CairoKeywords {
    init {
        val table = SyntaxTable()
        table.lineComment = "//"
    }
}
