plugins {
    kotlin("jvm") version "2.1.20"
    alias(libs.plugins.spotless)
}

kotlin {
    jvmToolchain(22)
}

dependencies {
    implementation(libs.classgraph)
    implementation(libs.kotlinpoet)
}

spotless {
    kotlin {
        ktlint()
    }
    kotlinGradle {
        ktlint()
    }
}
