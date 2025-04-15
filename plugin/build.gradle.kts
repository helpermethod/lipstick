plugins {
    `kotlin-dsl`
    alias(libs.plugins.spotless)
}

kotlin {
    jvmToolchain(22)
}

dependencies {
    implementation(project(":core"))
}

spotless {
    kotlin {
        ktlint()
    }
    kotlinGradle {
        ktlint()
    }
}

gradlePlugin {
    plugins {
        create("lipstick") {
            id = "io.github.helpermethod.lipstick"
            implementationClass = "io.github.helpermethod.lipstick.LipstickPlugin"
        }
    }
}
