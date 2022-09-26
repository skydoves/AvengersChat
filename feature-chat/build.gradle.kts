import io.getstream.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  id(libs.plugins.android.library.get().pluginId)
  id(libs.plugins.kotlin.android.get().pluginId)
  id(libs.plugins.kotlin.kapt.get().pluginId)
  id(libs.plugins.navigation.get().pluginId)
  id(libs.plugins.hilt.plugin.get().pluginId)
}

android {
  compileSdk = Configuration.compileSdk

  defaultConfig {
    minSdk = Configuration.minSdk
    targetSdk = Configuration.targetSdk
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  implementation(project(":core-data"))
  implementation(project(":core-uicomponents"))

  // data binding
  implementation(libs.bindables)

  // hilt
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
}