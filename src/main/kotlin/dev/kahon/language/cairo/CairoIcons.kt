package dev.kahon.language.cairo

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object CairoIcons {
    val FILE: Icon = IconLoader.getIcon("/icons/cairo.svg", CairoIcons::class.java)

    val MODULE = load("/icons/nodes/module.svg")

    val ENUM = load("/icons/nodes/enum.svg")
    val FUNCTION = load("/icons/nodes/function.svg")

    private fun load(path: String): Icon = IconLoader.getIcon(path, CairoIcons::class.java)
}
