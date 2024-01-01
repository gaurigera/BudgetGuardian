// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.16" apply false
}
buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.9.21"))
    }
}