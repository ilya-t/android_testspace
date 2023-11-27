plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    buildToolsVersion = Dependencies.buildToolsVersion
    defaultConfig {
        applicationId = "com.testspace"
        minSdk  = Dependencies.minSdkVersion
        compileSdk = Dependencies.targetSdkVersion
        targetSdk = Dependencies.targetSdkVersion
        versionCode = 1
        versionName = "1.00"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        names.forEach {
            getByName(it) {
                buildConfigField("String", "EXPERIMENT_NAME", "\"${BranchResolver.resolve(project)}\"")
            }
        }
    }
    flavorDimensions += listOf("default")
    namespace = "com.testspace"
}

dependencies {
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.0") {
        exclude(group = "com.android.support", module = "support-annotations")
    }

    implementation("androidx.appcompat:appcompat:${Dependencies.androidxVersion}")
    testImplementation("junit:junit:4.13.2")

    // kotlin-related dependencies
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Dependencies.kotlinVersion}")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${Dependencies.kotlinVersion}")

    // UI Tests
    androidTestImplementation("androidx.ui:ui-test:${Dependencies.androidxVersion}")
}