@file:Suppress("UnstableApiUsage")

plugins {
    alias(libs.plugins.aliucord.core)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
}

android {
    namespace = "com.aliucord.stubs"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    lint {
        disable += "SetTextI18n"
    }
}

kotlin {
    jvmToolchain(21)

    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xno-call-assertions",
            "-Xno-param-assertions",
            "-Xno-receiver-assertions",
            "-Xannotation-default-target=param-property",
            "-Xallow-kotlin-package", // Workaround to adding kotlin.enums.EnumEntries polyfill
        )
    }
}

dependencies {
    compileOnly(libs.appcompat)
    compileOnly(libs.constraintlayout)
    compileOnly(libs.discord)
    compileOnly(libs.kotlin.stdlib)
    compileOnly(libs.material)
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(arrayOf(
        "-Xlint:deprecation",
    ))
}
