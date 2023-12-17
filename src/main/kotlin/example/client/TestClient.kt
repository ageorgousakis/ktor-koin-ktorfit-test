package example.client

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path

interface TestClient {
    companion object {
        const val baseUrl = "https://swapi.dev/api/"
    }

    @GET("people/{id}/")
    suspend fun getPersonById(@Path("id") peopleId: String): Person
}
