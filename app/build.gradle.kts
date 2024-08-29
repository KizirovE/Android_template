plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinSerialization)
}

android {
    namespace = libs.versions.namespace.get() + ".template"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.namespace.get() + "template"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        resourceConfigurations.addAll(arrayOf("ru", "kk"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    bundle {
        language {
            enableSplit = false
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("dev") {
            dimension = "version"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            resValue("string", "app_name", "DEV" + libs.versions.appName.get())
            buildConfigField("String", "BASE_URL", "\"https://dog.ceo/\"")
        }
        create("tst") {
            dimension = "version"
            applicationIdSuffix = ".tst"
            versionNameSuffix = "-tst"
            resValue("string", "app_name", "TST" + libs.versions.appName.get())
            buildConfigField("String", "BASE_URL", "\"https://dog.ceo/\"")
        }
        create("preprod") {
            dimension = "version"
            applicationIdSuffix = ".preprod"
            versionNameSuffix = "-preprod"
            resValue("string", "app_name", "PREPROD" + libs.versions.appName.get())
            buildConfigField("String", "BASE_URL", "\"https://dog.ceo/\"")
        }
        create("master") {
            dimension = "version"
            resValue("string", "app_name", libs.versions.appName.get())
            buildConfigField("String", "BASE_URL", "\"https://dog.ceo/\"")
        }
    }
}
configurations{
    implementation.get().exclude(mapOf("group" to "org.jetbrains", "module" to "annotations"))
}
dependencies {
    implementation(project(":core:core"))
    implementation(project(":features:main"))
    implementation(project(":features:template"))
    implementation(project(":data:template_api"))
    implementation(project(":domain:example"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.voyager.navigator)
    implementation(libs.voyager.screenmodel)
    implementation(libs.voyager.koin)
    implementation(libs.koin.android)
    implementation(libs.koin.androidX.compose)
    implementation(libs.paperdb)
    implementation(libs.room.runtime)
    implementation(libs.trikita.log)

    implementation(libs.ktor.core)
    implementation(libs.ktor.android)
    implementation(libs.ktor.auth)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.content)
    implementation(libs.ktor.serialization)
}