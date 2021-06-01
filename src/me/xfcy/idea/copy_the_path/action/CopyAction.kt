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
package me.xfcy.idea.copy_the_path.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.ServiceManager
import me.xfcy.idea.copy_the_path.setting.SettingState
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

class CopyAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {

        val project = e.project
        val projectPath = project?.basePath ?: ""

        val state = project?.let { ServiceManager.getService(it, SettingState::class.java) }
        val prefix = state?.pathPrefix ?: ""

        val file = CommonDataKeys.VIRTUAL_FILE.getData(e.dataContext)
        var filePath = file?.canonicalPath ?: ""

        if (projectPath.isNotBlank() && filePath.startsWith(projectPath)) {
            val subStart = projectPath.length + 1
            filePath = prefix + filePath.substring(subStart)
        }

        val stringSelection = StringSelection(filePath)
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(stringSelection, stringSelection)

    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabledAndVisible = (e.project != null)
    }
}