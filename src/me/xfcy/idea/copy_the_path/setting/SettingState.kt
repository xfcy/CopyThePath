/**
 * Copyright (c) 2021 ChenY <xfcypc@foxmail.com>
 * CopyThePath is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 * http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 */
package me.xfcy.idea.copy_the_path.setting

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
        name = "CopyThePathConfig",
        storages = [Storage(StoragePathMacros.WORKSPACE_FILE)]
)
class SettingState(var pathPrefix: String? = ""): PersistentStateComponent<SettingState> {

    companion object {
        fun getInstance(project: Project): SettingState? {
            return project.getService(SettingState::class.java)
        }
    }

    override fun getState(): SettingState? {
        return this
    }

    override fun loadState(s: SettingState) {
        XmlSerializerUtil.copyBean(s, this)
    }

    override fun noStateLoaded() {
        val state = SettingState("/")
        XmlSerializerUtil.copyBean(state, this)
    }
}