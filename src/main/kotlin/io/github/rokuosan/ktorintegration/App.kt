package io.github.rokuosan.ktorintegration

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class App: JavaPlugin() {

    override fun onEnable() {
        object: BukkitRunnable(){
            override fun run(){
                embeddedServer(Netty, 8080){
                    routing {
                        get("/"){
                            call.respondText("Hello World")
                            println("Connection OK")
                        }
                    }
                }.start(true)
            }
        }.runTaskAsynchronously(this)
    }
}