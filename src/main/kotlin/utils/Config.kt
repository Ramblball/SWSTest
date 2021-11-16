package utils

import com.papsign.ktor.openapigen.OpenAPIGen
import database.utils.initDB
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import route.apiRoute

fun Application.module(testing: Boolean = false) {
    initDB(testing)

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    install(OpenAPIGen) {
        serveSwaggerUi = true
        swaggerUiPath = "/swagger-ui"
    }
    install(CallLogging)
    routing {
        apiRoute()
    }
}