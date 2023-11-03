package dev.kahon.language.cairo

import com.intellij.DynamicBundle
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey

@NonNls
const val CAIRO_BUNDLE = "messages.CairoBundle"

object CairoBundle : DynamicBundle(CAIRO_BUNDLE) {

    @Suppress("SpreadOperator")
    @JvmStatic
    fun message(@PropertyKey(resourceBundle = CAIRO_BUNDLE) key: String, vararg params: Any) =
        getMessage(key, *params)

    @Suppress("SpreadOperator", "unused")
    @JvmStatic
    fun messagePointer(@PropertyKey(resourceBundle = CAIRO_BUNDLE) key: String, vararg params: Any) =
        getLazyMessage(key, *params)
}