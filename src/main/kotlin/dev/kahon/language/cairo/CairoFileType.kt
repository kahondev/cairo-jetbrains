package dev.kahon.language.cairo

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon


object CairoFileType : LanguageFileType(CairoLanguage) {


    override fun getName(): String = "Cairo"
    override fun getDescription(): String = "Cairo language file"
    override fun getDefaultExtension(): String ="cairo"
    override fun getIcon(): Icon = CairoIcons.FILE


}
