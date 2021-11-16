package database.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Users: IntIdTable() {
    val mail = varchar("mail", 255).uniqueIndex()
    val firstName = varchar("first_name", 63)
    val lastName = varchar("last_name", 63)
    val patronymic = varchar("patronymic", 63)
    val phoneNumber = varchar("phone_number", 15).uniqueIndex()
}

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserEntity>(Users)

    var mail by Users.mail
    var firstName by Users.firstName
    var lastName by Users.lastName
    var patronymic by Users.patronymic
    var phoneNumber by Users.phoneNumber

    override fun toString(): String = "User($mail, $firstName, $lastName, $patronymic, $phoneNumber)"
}