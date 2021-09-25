plugins {
    java
}

group = "io.github.luizotavio"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://repo.mikeprimm.com")
}

dependencies {
    compileOnly("org.spigotmc:spigot:1.7.10-R0.1-SNAPSHOT")
    compileOnly(fileTree("./libraries"))
}


