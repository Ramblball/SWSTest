package route

import database.repository.UserRepository
import database.service.UserService
import dto.UserDTO
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.user() {
    val userService = UserService(UserRepository())
    
    get("/{id}") {
        val userId = call.parameters["id"]?.toIntOrNull()
        if (userId == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val user = userService.getUserById(userId)
            if (user == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(HttpStatusCode.OK, user)
            }
        }
    }

    post("/") {
        val userRequest = call.receive<UserDTO>()
        if (userRequest.validate()) {
            if (userService.addUser(userRequest)) {
                call.respond(HttpStatusCode.Accepted)
            } else {
                call.respond(HttpStatusCode.InternalServerError)
            }
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}