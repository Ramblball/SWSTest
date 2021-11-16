import com.google.gson.GsonBuilder
import dto.UserDTO
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import utils.module
import kotlin.test.assertEquals

class UserTests {
    private val gson = GsonBuilder().create()
    private val storedUser = UserDTO(
        1, "mail", "first", "last", "patr", "111"
    )
    private val newValidUser = UserDTO(
        null, "test", "root", "root", "root", "111111111"
    )
    private val newInvalidUser = UserDTO(
        null, "test", "root", "root", "root", "222"
    )

    @Test
    fun testSuccessGetUserRequest() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/user/${storedUser.id}").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(gson.toJson(storedUser), response.content)
            }
        }
    }
    @Test
    fun testDeniedGetUserRequestByNotFound() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/user/500").apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
            }
        }
    }

    @Test
    fun testSuccessCreateUserRequest() = withTestApplication(Application::module) {
        with(handleRequest(HttpMethod.Post, "/user") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(
                listOf(
                    "mail" to newValidUser.mail,
                    "firstName" to newValidUser.firstName,
                    "lastName" to newValidUser.lastName,
                    "patronymic" to newValidUser.patronymic,
                    "phoneNumber" to newValidUser.phoneNumber
                ).formUrlEncode()
            )
        }) {
            assertEquals(HttpStatusCode.Accepted, response.status())
        }
    }

    @Test
    fun testDeniedCreateUserRequestByInvalidData() = withTestApplication(Application::module) {
        with(handleRequest(HttpMethod.Post, "/user") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(
                listOf(
                    "mail" to newInvalidUser.mail,
                    "firstName" to newInvalidUser.firstName,
                    "lastName" to newInvalidUser.lastName,
                    "patronymic" to newInvalidUser.patronymic,
                    "phoneNumber" to newInvalidUser.phoneNumber
                ).formUrlEncode()
            )
        }) {
            assertEquals(HttpStatusCode.BadRequest, response.status())
        }
    }
}