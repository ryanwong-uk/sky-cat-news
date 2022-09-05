# Sky Cat News

[![Build Status](https://app.bitrise.io/app/a0e8541b47b30002/status.svg?token=Q40Sl8puw7pk7BT7AbF_8w&branch=master)](https://app.bitrise.io/app/a0e8541b47b30002) [![codecov](https://codecov.io/gh/ryanwong-uk/sky-cat-news/branch/main/graph/badge.svg?token=UG926FRXVG)](https://codecov.io/gh/ryanwong-uk/sky-cat-news)

## Scenario
Sky has recently decided to move into the local cat news industry. To enable this, we need to build a prototype app to demonstrate to stakeholders. The basic premise of the app is to allow users to look at stories of cute cats nearby. 

This is a prototype of the app. As the backend isn't developed yet, this prototype currently mock out the feed locally, with an option in place to switch to the real feed later.


## TL;DR - Status 

* 73 Unit tests for repositories, models, Ktor API services are done.
* 16 Unit tests for RoomDB DAOs are done.


## High level architecture

* Android-Kotlin
* MVVM architecture with use-cases
* Single activity
* Jetpack Compose UI

### Major libraries used

* [`Dagger Hilt`](https://dagger.dev/hilt/) - DI
* [`Kotlin Coroutines`](https://github.com/Kotlin/kotlinx.coroutines)
* [`Kotlin Flow`](https://kotlinlang.org/docs/flow.html)
* [`Jetpack Room`](https://developer.android.com/jetpack/androidx/releases/room) - Database
* [`Ktor`](https://ktor.io/) - HTTP Client
* [`Kotlin Serialization`](https://kotlinlang.org/docs/serialization.html) - For JSON parsing
* [`Timber`](https://github.com/JakeWharton/timber) - Logging
* [`LeakCanary`](https://github.com/square/leakcanary) - Memory leak detection
* [`JUnit 4`](https://github.com/junit-team/junit4) - Instrumented tests
* [`KOTest`](https://kotest.io/) - Test framework and assertion library
* [`MockK`](https://mockk.io/) - Mocking library
* [`Bitrise`](https://app.bitrise.io/) - CI
* [`Kover`](https://github.com/Kotlin/kotlinx-kover) - code coverage
* [`codecov`](https://codecov.io/) - code coverage
* [`Ktlint Gradle`](https://github.com/jlleitschuh/ktlint-gradle) - ktlint plugin to check and apply code formatting

## Requirements

* Android Studio Chipmunk | 2021.2.1 Patch 2
* Android device or simulator running Android 6.0+ (API 23)

## Setting up the keystore

### Local 
* Android keystore is not being stored in this repository. You need your own keystore to generate
  the apk / App Bundle

* If your project folder is at `/app/skycatnews/`, the keystore file and `keystore.properties`
  should be placed at `/app/`

* The format of `keystore.properties` is:
  ```
     store=/app/release-key.keystore
     alias=<alias>
     pass=<alias password>
     storePass=<keystore password>
  ```
  
### CI environment
* This project has been configured to build automatically on Bitrise.

* The following environment variables have been set to provide the keystore:
  ```
     BITRISE = true
     HOME = <the home directory of the bitrise environment>
     BITRISEIO_ANDROID_KEYSTORE_PASSWORD = <your keystore password>
     BITRISEIO_ANDROID_KEYSTORE_ALIAS = <your keystore alias>
     BITRISEIO_ANDROID_KEYSTORE_PRIVATE_KEY_PASSWORD = <your keystore private key password>
  ```

* The keystore file should be securely uploaded and then placed to `$HOME/keystores/release.jks`

## Building the App

This project can be built by following the usual gradle build process.

### Run unit tests and generate coverage report

   ```
   ./gradlew check
   ```

### Build and install on the connected device

   ```
   ./gradlew installDebug
   // or
   // ./gradlew installRelease
   ```

* Options are: `Debug`, `Release`
* Debug builds will have an App package name suffix `.debug`

### Build and sign a bundle for distribution

   ```
   ./gradlew clean bundleRelease
   ```

### Build and sign an apk for distribution

   ```
   ./gradlew clean assembleRelease
   ```

* The generated apk(s) will be stored under `app/build/outputs/apk/`
* Other usages can be listed using `./gradelew tasks`