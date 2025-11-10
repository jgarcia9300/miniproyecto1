plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // KSP debe llevar versión si no usas alias desde libs.versions.toml
    id("com.google.devtools.ksp") version "2.0.21-1.0.25"
}

android {
    namespace = "com.univalle.miniproyecto1"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.univalle.miniproyecto1"
        minSdk = 24        // 21 también funciona si lo prefieres
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    // Recomendado para AGP/Compose actuales
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }

    buildFeatures {
        compose = true
        // dataBinding no es necesario para Compose; si no lo usas, quítalo:
        // dataBinding = false
    }
}

dependencies {
    // --- Compose base (catálogo) ---
    implementation("androidx.compose.runtime:runtime-saveable")
    implementation("androidx.compose.foundation:foundation")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // --- Extras que pide tu HU-4 ---
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui-text") // <- habilita KeyboardOptions/KeyboardType

    // --- Room + KSP (versiones recientes) ---
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // --- Coroutines recientes ---
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // --- Si usas vistas/clásico, puedes mantener estas (opcional) ---
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

    // Navegación clásica (si la usas)
    val navVersion = "2.3.5"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-common:$navVersion")

    // Otros
    implementation("com.getbase:floatingactionbutton:1.10.1")
    implementation("androidx.biometric:biometric:1.1.0")

    // Tests (deja solo uno por lib si ya lo tienes en el catálogo)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.mockito:mockito-inline:3.12.4")
    testImplementation("org.mockito:mockito-android:3.11.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    debugImplementation("org.jacoco:org.jacoco.core:0.8.7")

    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.compose.ui:ui")          // base
    implementation("androidx.compose.ui:ui-text")     // << necesario para KeyboardOptions/KeyboardType
    implementation("androidx.compose.material3:material3")

}
