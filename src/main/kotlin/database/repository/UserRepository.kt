package database.repository

import database.data.UserEntity
import dto.UserDTO
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {

    fun getUserById(userId: Int): UserEntity = transaction {
        UserEntity[userId]
    }

    fun addUser(user: UserDTO): UserEntity = transaction {
        UserEntity.new{
            mail = user.mail
            firstName = user.firstName
            lastName = user.lastName
            patronymic = user.patronymic
            phoneNumber = user.phoneNumber
        }
    }
}