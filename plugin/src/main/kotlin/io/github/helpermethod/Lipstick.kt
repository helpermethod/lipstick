package io.github.helpermethod

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class Lipstick : DefaultTask() {
    @TaskAction
    fun generate() {
        println("Hello Lipstick!")
    }
}
