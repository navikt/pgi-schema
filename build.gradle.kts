val avroVersion = "1.10.0"

group = "no.nav.pensjonsamhandling"

repositories {
    mavenCentral()
    maven(url = "https://dl.bintray.com/gradle/gradle-plugins")
    maven(url = "http://packages.confluent.io/maven/")
}

plugins {
    `maven-publish`
    id("com.commercehub.gradle.plugin.avro") version "0.9.1" //https://github.com/davidmc24/gradle-avro-plugin
}

dependencies {
    implementation("org.apache.avro:avro:$avroVersion")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/navikt/${rootProject.name}")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
