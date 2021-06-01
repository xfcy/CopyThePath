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

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import javax.swing.JComponent

class SettingConfigurable(private val project: Project): Configurable {

    private val component = SettingComponent(project)

    override fun createComponent(): JComponent? {
        return component.mainPanel
    }

    override fun isModified(): Boolean {
        val state = SettingState.getInstance(project)
        val prefix = state?.pathPrefix ?: ""
        return (prefix != component.pathPrefixText.text)
    }

    override fun apply() {
        val state = SettingState.getInstance(project)
        state?.pathPrefix = component.pathPrefixText.text
    }

    override fun getDisplayName(): String {
        return "Copy the Path"
    }

    override fun reset() {
        val state = SettingState.getInstance(project)
        component.pathPrefixText.text = state?.pathPrefix
    }
}