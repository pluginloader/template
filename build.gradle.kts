plugins{
    kotlin("jvm") version "1.5.20"
    kotlin("plugin.serialization") version "1.5.20"
    id("maven-publish")
}

evaluationDependsOn("plu")

group = "pluginloader"
version = "1.0.0"

repositories{
    maven{url = uri("https://repo.implario.dev/public")}
}

//plu("configs")

dependencies{

}

fun plu(vararg plugins: String){
    plugins.forEach{project.dependencies.add("dependency", "pluginloader:${if(!it.contains(':')) "$it:1.0.0" else it}")}
}