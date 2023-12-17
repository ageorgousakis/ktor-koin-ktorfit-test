package test

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import test.client.Person
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configure()
        }
        client.config {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            defaultRequest {
                headers {
                    HttpHeaders.Accept to "application/json"
                    HttpHeaders.ContentType to "application/json"
                }
            }
        }.get("/1").apply {
            assertEquals(HttpStatusCode.OK, status)
            body<Person>().apply {
                assertEquals("Luke Skywalker", name)
            }
        }
    }
}
