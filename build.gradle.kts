

plugins {
    //id("java")
    id ("java")
    id ("io.ebean") version ("15.4.0")

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

}


dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    implementation("io.vertx:vertx-core:4.5.8")
    implementation("io.vertx:vertx-web:4.5.8")
    implementation("io.vertx:vertx-web-client:4.5.8")
    implementation("io.vertx:vertx-sql-client-templates:4.4.3") // Note: Keep the version as required
    implementation("io.ebean:ebean:12.8.2")
    implementation("io.ebean:ebean-agent:12.8.2")
    implementation("io.ebean:ebean-querybean:12.8.2")
    implementation("mysql:mysql-connector-java:8.0.28")
    implementation("javax.persistence:javax.persistence-api:2.2")
    implementation("org.projectlombok:lombok:1.18.16")
    implementation("javax.inject:javax.inject:1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
    implementation("org.slf4j:slf4j-api:1.7.32") // Update to the latest version
    implementation("ch.qos.logback:logback-classic:1.2.6")
    implementation ("io.vertx:vertx-auth-jwt:4.2.4")
    implementation ("io.vertx:vertx-auth-oauth2:4.2.4")
    implementation ("io.jsonwebtoken:jjwt:0.9.1")
    implementation ("com.auth0:java-jwt:3.10.3")
    implementation ("io.vertx:vertx-auth-jwt:4.2.4")
    implementation ("io.vertx:vertx-auth-common:4.2.4")
    implementation ("com.google.code.gson:gson:2.8.7")
    implementation("com.squareup.retrofit2:converter-gson:2.7.1")
    implementation ("com.fasterxml.jackson.core:jackson-databind")
    implementation ("com.squareup.retrofit2:retrofit:2.1.0")
    implementation ("com.squareup.retrofit2:converter-jackson:2.1.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    implementation ("com.google.code.gson:gson:2.8.8")

    implementation ("org.json:json:20220924")

}
repositories {
    mavenCentral()
}


tasks.test {
    useJUnitPlatform()
}

