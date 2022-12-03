plugins {
    alias libs.plugins.android.application
    alias libs.plugins.kotlin.android
    alias libs.plugins.hilt.android.plugin
    alias libs.plugins.kover.plugin
    alias libs.plugins.kotlin.kapt
    alias libs.plugins.kotlin.serialization
}

android {
    compileSdk libs.versions.compileSdk.get().toInt()

    signingConfigs {
        release
    }

    def isRunningOnBitrise = System.getenv("BITRISE") == "true"
    if (isRunningOnBitrise) {
        signingConfigs.release.storeFile = file(System.getenv("HOME") + "/keystores/release.jks")
        signingConfigs.release.storePassword = System.getenv("BITRISEIO_ANDROID_KEYSTORE_PASSWORD")
        signingConfigs.release.keyAlias = System.getenv("BITRISEIO_ANDROID_KEYSTORE_ALIAS")
        signingConfigs.release.keyPassword = System.getenv("BITRISEIO_ANDROID_KEYSTORE_PRIVATE_KEY_PASSWORD")

    } else {
        Properties keyProps = new Properties()
        keyProps.load(new FileInputStream(file('../../keystore.properties')))
        signingConfigs.release.storeFile = file(keyProps["store"])
        signingConfigs.release.keyAlias = keyProps["alias"]
        signingConfigs.release.storePassword = keyProps["storePass"]
        signingConfigs.release.keyPassword = keyProps["pass"]
    }

    defaultConfig {
        applicationId "uk.ryanwong.skycatnews"
        minSdk libs.versions.minSdk.get().toInteger()
        targetSdk libs.versions.targetSdk.get().toInteger()
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "uk.ryanwong.skycatnews.app.ui.CustomTestRunner"
        vectorDrawables {
            useSupportLibrary true
        }

        // Bundle output filename
        setProperty("archivesBaseName", "skycatnews-" + versionName + "-" + new Date().format('yyyyMMdd-HHmmss'))

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += ["room.schemaLocation": "$projectDir/schemas".toString()]
            }
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix '.debug'
            minifyEnabled false

            signingConfig signingConfigs.release
            applicationVariants.all { variant ->
                variant.outputs.all { output ->
                    def date = new Date()
                    def formattedDate = date.format('yyyyMMdd-HHmmss')
                    outputFileName = "skycatnews-${variant.name}-${variant.versionName}-${formattedDate}.apk"
                }
            }
        }

        release {
            shrinkResources true
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'

            signingConfig signingConfigs.release
            applicationVariants.all { variant ->
                variant.outputs.all { output ->
                    def date = new Date()
                    def formattedDate = date.format('yyyyMMdd-HHmmss')
                    outputFileName = "skycatnews-${variant.name}-${variant.versionName}-${formattedDate}.apk"
                }
            }
        }
    }

    flavorDimensions "datasource"
    productFlavors {
        fake {
            dimension 'datasource'
            buildConfigField "String", "DEFAULT_BASE_URL", '""'
        }
        prod {
            dimension 'datasource'
            buildConfigField "String", "DEFAULT_BASE_URL", '"https://ryanwong.co.uk/restapis"'
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    buildFeatures {
        compose true
    }
    composeOptions {
        kotlinCompilerExtensionVersion libs.versions.compose.compiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/*"
        }
    }
    testOptions {
        animationsDisabled true

        unitTests {
            includeAndroidResources = true
        }

        unitTests.all {
            useJUnitPlatform()
        }
    }
}

dependencies {

    implementation libs.androidx.core.ktx
    implementation libs.androidx.lifecycle.runtime.compose
    implementation libs.androidx.activity.compose
    implementation libs.kotlin.reflect
    implementation libs.androidx.navigation.compose
    implementation libs.timber
    debugImplementation libs.leakcanary.android
    implementation libs.bundles.coil
    implementation libs.bundles.coroutines
    implementation libs.accompanist.webview
    implementation libs.accompanist.swiperefresh

    // compose
    def composeBom = platform(libs.androidx.compose.bom)
    implementation composeBom
    androidTestImplementation composeBom
    implementation libs.androidx.compose.material
    implementation libs.androidx.compose.ui.tooling.preview
    debugImplementation libs.androidx.compose.ui.tooling
    androidTestImplementation libs.androidx.compose.ui.test.junit4
    debugImplementation libs.androidx.compose.ui.test.manifest

    testImplementation libs.junit
    testImplementation libs.bundles.kotest
    androidTestImplementation libs.androidx.test.junit4
    androidTestImplementation libs.androidx.test.rules
    androidTestImplementation libs.androidx.test.espresso.core
    androidTestImplementation libs.androidx.test.espresso.idling.resource
    androidTestImplementation libs.kotest.assertions.core

    // Dagger-Hilt
    implementation libs.hilt.android
    kapt libs.hilt.compiler
    implementation libs.hilt.navigation.compose
    kaptAndroidTest libs.hilt.android.compiler
    androidTestImplementation libs.hilt.android.testing

    // Ktor
    implementation libs.bundles.ktor
    testImplementation libs.ktor.client.mock
    implementation libs.kotlinx.serialization.json

    // RoomDB
    implementation libs.bundles.room
    kapt libs.androidx.room.compiler
    testImplementation libs.androidx.room.testing

    // Mockk
    testImplementation libs.mockk
    testImplementation libs.mockk.agent.jvm
    androidTestImplementation libs.mockk.android
}


ktlint {
    android = true
    ignoreFailures = false
    disabledRules = ["max-line-length"]
    reporters {
        reporter "plain"
        reporter "checkstyle"
        reporter "sarif"
    }
}

tasks.getByPath("preBuild").dependsOn("ktlintFormat")

tasks.koverMergedHtmlReport {
    excludes = ['dagger.hilt.internal.aggregatedroot.codegen.*',
                'hilt_aggregated_deps.*',
                'uk.ryanwong.skycatnews.app.ui.*',
                'uk.ryanwong.skycatnews.*.ui.screen.*',
                'uk.ryanwong.skycatnews.*.di.*',
                'uk.ryanwong.skycatnews.*.Hilt_*',
                'uk.ryanwong.skycatnews.*.*_Factory*',
                'uk.ryanwong.skycatnews.*.*_HiltModules*',
                'uk.ryanwong.skycatnews.*.*Module_*',
                'uk.ryanwong.skycatnews.*.*MembersInjector*',
                'uk.ryanwong.skycatnews.*.*_Impl*',
                'uk.ryanwong.skycatnews.ComposableSingletons*',
                'uk.ryanwong.skycatnews.BuildConfig*',
                'uk.ryanwong.skycatnews.*.Fake*',
                'uk.ryanwong.skycatnews.*.previewparameter*',
                'uk.ryanwong.skycatnews.app.ComposableSingletons*']
}

tasks.koverMergedXmlReport {
    excludes = ['dagger.hilt.internal.aggregatedroot.codegen.*',
                'hilt_aggregated_deps.*',
                'uk.ryanwong.skycatnews.app.ui.*',
                'uk.ryanwong.skycatnews.*.ui.screen.*',
                'uk.ryanwong.skycatnews.*.di.*',
                'uk.ryanwong.skycatnews.*.Hilt_*',
                'uk.ryanwong.skycatnews.*.*_Factory*',
                'uk.ryanwong.skycatnews.*.*_HiltModules*',
                'uk.ryanwong.skycatnews.*.*Module_*',
                'uk.ryanwong.skycatnews.*.*MembersInjector*',
                'uk.ryanwong.skycatnews.*.*_Impl*',
                'uk.ryanwong.skycatnews.ComposableSingletons*',
                'uk.ryanwong.skycatnews.BuildConfig*',
                'uk.ryanwong.skycatnews.*.Fake*',
                'uk.ryanwong.skycatnews.*.previewparameter*',
                'uk.ryanwong.skycatnews.app.ComposableSingletons*']
}