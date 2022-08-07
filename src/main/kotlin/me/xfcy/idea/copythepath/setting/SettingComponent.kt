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
package me.xfcy.idea.copythepath.setting

import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class SettingComponent(project: Project) {

    private var prefix = ""
    private val examplePath = "path/to/your/file.ext"

    val mainPanel: JPanel
    val pathPrefixText: JBTextField
    val preview: JBLabel

    init {
        val state = SettingState.getInstance(project)
        prefix = state?.pathPrefix ?: ""
        pathPrefixText = JBTextField(prefix)
        preview = JBLabel(prefix + examplePath)

        mainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(JBLabel("Path prefix: "), pathPrefixText, 1, false)
                .addLabeledComponent(JBLabel("Preview: "), preview, 1, false)
                .addComponentFillVertically(JPanel(), 0)
                .panel

        pathPrefixText.document.addDocumentListener(object: DocumentListener {
            override fun insertUpdate(p0: DocumentEvent?) { changePreview() }
            override fun removeUpdate(p0: DocumentEvent?) { changePreview() }
            override fun changedUpdate(p0: DocumentEvent?) { changePreview() }
            fun changePreview() {
                preview.text = pathPrefixText.text + examplePath
            }
        })
    }
}