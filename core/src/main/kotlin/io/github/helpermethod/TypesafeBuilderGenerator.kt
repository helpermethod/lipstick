package io.github.helpermethod

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier.INLINE
import com.squareup.kotlinpoet.MemberName
import io.github.classgraph.ClassGraph
import java.net.http.HttpClient
import java.nio.file.Path
import java.util.Locale

fun generate(vararg packages: String) =
    ClassGraph()
        .enableAllInfo()
        .enableSystemJarsAndModules()
        .acceptPackages(*packages)
        .scan()
        .use { scanResult ->
            scanResult
                .allClasses
                .mapNotNull { classInfo ->
                    classInfo
                        .methodInfo
                        .find { methodInfo -> methodInfo.isStatic && methodInfo.name == "newBuilder" }
                        ?.let {
                            classInfo to it
                        }
                }
                .map { (classInfo, methodInfo) ->
                    FunSpec
                        .builder(classInfo.simpleName.replaceFirstChar { it.lowercase(Locale.getDefault()) })
                        .addModifiers(INLINE)
                        .addStatement("return %M().apply(block).%M()", MemberName(classInfo.packageName, methodInfo.name), MemberName(classInfo.packageName, "build"))
                        .returns(classInfo.loadClass())
                        .build()
                }
                .forEach {
                    FileSpec
                        .builder("io.github.helpermethod", "Test")
                        .addFunction(it)
                        .build()
                        .writeTo(Path.of("test.kt"))
                }
        }

fun main() {
    generate("java.net.http")

    HttpClient.newBuilder().build()
}