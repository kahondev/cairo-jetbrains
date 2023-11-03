package dev.kahon.language.cairo.settings

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.ApiStatus

@Service(Service.Level.PROJECT)
@State(name = "CairoSettings", storages = [(Storage("CairoSettings.xml"))])
class CairoSettings :
    SimplePersistentStateComponent<CairoSettingsState>(CairoSettingsState()) {
    var homePath: String
        get() = state.homePath ?: ""
        set(value) {
            state.homePath = value
        }

    companion object {
        @JvmStatic
        fun getInstance(project: Project): CairoSettings = project.service()
    }
}

@Service
@ApiStatus.Internal
class CairoSettingsState : BaseState() {
    var homePath by string()
}
