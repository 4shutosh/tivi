// Copyright 2023, Google LLC, Christopher Banes and the Tivi project contributors
// SPDX-License-Identifier: Apache-2.0


plugins {
    id("app.tivi.kotlin.multiplatform")
    alias(libs.plugins.ksp)
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.core.base)
                api(projects.api.trakt)
                api(projects.api.tmdb)
                api(projects.core.logging)
                api(projects.data.models)
                implementation(projects.data.db)

                api(libs.store)

                implementation(libs.kotlininject.runtime)
            }
        }
    }
}

dependencies {
    add("kspJvm", libs.kotlininject.compiler)
}
