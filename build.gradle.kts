val avroVersion = "1.10.0"

group = "no.nav.pgi"

repositories {
    mavenCentral()
    maven(url = "https://dl.bintray.com/gradle/gradle-plugins")
    maven(url = "https://packages.confluent.io/maven/")
}

plugins {
    `maven-publish`
    id("com.commercehub.gradle.plugin.avro") version "0.9.1" //https://github.com/davidmc24/gradle-avro-plugin
    id("se.patrikerdes.use-latest-versions") version "0.2.14"
    id("net.researchgate.release") version "2.8.1"
}

dependencies {
    implementation("org.apache.avro:avro:$avroVersion")
}

release {
    newVersionCommitMessage = "[Release Plugin] - next version commit: "
    tagTemplate = "release-\${version}"
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

tasks.withType<Wrapper> {
    gradleVersion = "6.6"
}