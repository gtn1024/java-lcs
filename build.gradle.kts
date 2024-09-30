plugins {
    `java-library`
    `maven-publish`
    signing
    id("io.github.jeadyx.sonatype-uploader") version "2.8"
}

group = "io.github.gtn1024"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "io.github.gtn1024"
            artifactId = "java-lcs"
            version = "1.0.0"
            from(components["java"])
            pom {
                name = "java-lcs"
                description = "Java implementation of the Longest Common Subsequence algorithm"
                url = "https://github.com/gtn1024/java-lcs"
                licenses {
                    license {
                        name = "Apache-2.0"
                        url = "https://raw.githubusercontent.com/gtn1024/java-lcs/refs/heads/master/LICENSE"
                    }
                }
                developers {
                    developer {
                        name = "Taoning Ge"
                        email = "gtn1024o@outlook.com"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/gtn1024/java-lcs.git"
                    developerConnection = "scm:git:ssh://github.com/gtn1024/java-lcs.git"
                    url = "https://github.com/gtn1024/java-lcs"
                }
            }
        }
    }
    repositories {
        maven {
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

sonatypeUploader {
    repositoryPath = layout.buildDirectory.dir("repo").get().asFile.path
    tokenName = System.getenv("SONATYPE_USERNAME")
    tokenPasswd = System.getenv("SONATYPE_PASSWORD")
}

signing {
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}
