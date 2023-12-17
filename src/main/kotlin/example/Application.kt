package example

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import example.client.TestClient

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configure()
}

fun Application.configure() {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
    install(Koin) {
        slf4jLogger()
        modules(
            clientsModule(),
        )
    }
    routing {
        val client: TestClient by inject()

        get("/") {
            call.respond(mapOf("status" to "OK"))
        }

        get("/{id}") {
            val id = call.parameters.getOrFail("id")
            call.respond(client.getPersonById(id))
        }
    }
}




