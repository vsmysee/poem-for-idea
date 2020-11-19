package com.github.vsmysee.poemforidea.services

import com.intellij.openapi.project.Project
import com.github.vsmysee.poemforidea.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
