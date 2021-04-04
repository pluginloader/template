plugins{
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.serialization") version "1.4.31"
    id("pluginloader.api") version "1.8.22"
    id("pluginloader.paper") version "1.8.22"
    id("org.hidetake.ssh") version "2.10.1" apply false
    id("maven-publish")
}

repositories{
    maven{url = uri("https://repo.implario.dev/public")}
}

//plu.plu("configs")

dependencies{

}

task("rename") {
    doLast {
        val p = File("").absoluteFile.name
        val pl = File("src/main/kotlin/${rootProject.name}/Plugin.kt")
        pl.writeText(pl.readText().replace(rootProject.name, p))
        File("src/main/kotlin/${rootProject.name}").renameTo(File("src/main/kotlin/$p"))
        val settings = File("settings.gradle.kts")
        settings.writeText(settings.readText().replace(rootProject.name, p))
    }
}

tasks.withType<org.gradle.jvm.tasks.Jar>{
    archiveFileName.set("${rootProject.name}.jar")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.useIR = true
}

publishing {
    publications {
        create<MavenPublication>("maven"){
            from(components["java"])
        }
    }

    repositories {
        maven {
            url = uri(System.getenv("PLU_PUBLIC_URL"))

            credentials {
                username = System.getenv("PLU_PUBLIC_PUSH_USER")
                password = System.getenv("PLU_PUBLIC_PUSH_PASSWORD")
            }
        }
    }
}