import com.android.build.api.dsl.ApkSigningConfig

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

object BuildType {
    const val DEBUG = "debug"
    const val RELEASE = "release"
}

android {
    namespace = "com.evenclose.versalistpro"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.evenclose.versalistpro"
        minSdk = 28
        targetSdk = 35
        versionCode = 23
        versionName = "2.7.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    signingConfigs {
        getByName("debug") {
            applyConfigs()
        }
        create("release") {
            applyConfigs()
        }
    }

    buildTypes {
        getByName(BuildType.DEBUG) {
/*            buildConfigField(
                "String",
                "BIG_DATA_CLOUD_API_KEY",
                "\"${findProperty("BIG_DATA_CLOUD_API_KEY") ?: ""}\""
            )*/
        }
        getByName(BuildType.RELEASE) {
/*            buildConfigField(
                "String",
                "BIG_DATA_CLOUD_API_KEY",
                "\"${findProperty("BIG_DATA_CLOUD_API_KEY") ?: ""}\""
            )*/
            signingConfig = signingConfigs.getByName(BuildType.RELEASE)
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            ndk {
                debugSymbolLevel = "FULL"
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

/*    composeOptions {
        kotlinCompilerExtensionVersion '1.4.4'
    }
    packagingOptions {
        resources {
            excludes += '/META-INF/{AL2.0,LGPL2.1}'
        }
    }
    hilt {
        enableAggregatingTask = true
    }*/
}

fun ApkSigningConfig.applyConfigs() {
    storeFile = file(project.findProperty("STORE_FILE") as String)
    storePassword = project.findProperty("STORE_PASSWORD") as String
    keyAlias = project.findProperty("KEY_ALIAS") as String
    keyPassword = project.findProperty("KEY_PASSWORD") as String
}

dependencies {

    ksp(libs.hilt.android.compiler)
    ksp(libs.room.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    //implementation(libs.retrofit)
    //implementation(libs.converter.gson)
    //implementation(libs.logging.interceptor)
    implementation(libs.lottie.compose)
    implementation(libs.material.icons.extended)
    implementation (libs.androidx.legacy.support.v4)
    implementation (libs.kotlinx.coroutines.core)


}