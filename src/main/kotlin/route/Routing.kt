package route

import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.apiRoute() {
    route("/static") {
        resource("openapi.yaml")
    }
    get("/api") {
        call.respondRedirect("/swagger-ui/index.html?url=/static/openapi.yaml", true)
    }
    route("/user") {
        user()
    }
}