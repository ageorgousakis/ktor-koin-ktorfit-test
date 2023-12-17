package test

import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import test.client.TestClient

fun clientsModule() = module {

    single {
        HttpClient {
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
        }
    }

    single {
        ktorfit {
            baseUrl(TestClient.baseUrl)
            httpClient(get() as HttpClient)
//            converterFactories(DefaultResponseConverterFactory())
        }.create<TestClient>()
    }

}
