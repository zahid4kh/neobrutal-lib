plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    `maven-publish`
    signing
}

group = "io.github.zahid4kh"
version = "1.1.0"

android {
    namespace = "zahid.neobrutal"
    compileSdk = 36

    defaultConfig {
        minSdk = 28

        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "2.0.21"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation(libs.ui)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.material.icons.core)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            artifactId = "neobrutal"

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("NeoBrutal")
                description.set("Neo-Brutalism UI components for Jetpack Compose: bold shapes, high contrast colors and thick borders.")
                url.set("https://github.com/zahid4kh/neobrutal-lib")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/zahid4kh/neobrutal-lib/blob/main/LICENSE.md")
                    }
                }
                developers {
                    developer {
                        id.set("zahid4kh")
                        name.set("Zahid Khalilov")
                        url.set("https://github.com/zahid4kh")
                    }
                }
                scm {
                    url.set("https://github.com/zahid4kh/neobrutal-lib")
                    connection.set("scm:git:https://github.com/zahid4kh/neobrutal-lib.git")
                    developerConnection.set("scm:git:ssh://git@github.com/zahid4kh/neobrutal-lib.git")
                }
            }
        }
    }
    // Maven Central takes a zip of a Maven repository layout: built here, uploaded by hand
    // at central.sonatype.com › Publish Component.
    repositories { maven { name = "staging"; url = uri(layout.buildDirectory.dir("staging")) } }
}

// Signs with the gpg command and its agent: pass -Psigning.gnupg.keyName=<key id>
// (or put it in ~/.gradle/gradle.properties).
signing {
    useGpgCmd()
    sign(publishing.publications["release"])
}
tasks.withType<Sign>().configureEach {
    onlyIf { gradle.taskGraph.hasTask("${project.path}:centralBundle") }
    doFirst {
        if (project.findProperty("signing.gnupg.keyName") == null)
            throw GradleException("Set the signing key: -Psigning.gnupg.keyName=<gpg key id>")
    }
}

tasks.register<Zip>("centralBundle") {
    group = "publishing"
    description = "Builds the signed bundle to upload to the Maven Central Portal"
    val staging = layout.buildDirectory.dir("staging")
    dependsOn("publishReleasePublicationToStagingRepository")
    from(staging) {
        include("${project.group.toString().replace('.', '/')}/neobrutal/${project.version}/**")
    }
    archiveFileName.set("neobrutal-${project.version}-bundle.zip")
    destinationDirectory.set(layout.buildDirectory.dir("distributions"))
}
afterEvaluate {
    tasks.named("publishReleasePublicationToStagingRepository") {
        doFirst { delete(layout.buildDirectory.dir("staging")) }
    }
}
