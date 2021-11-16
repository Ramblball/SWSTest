package database.service

import database.exception.DataBaseException
import database.repository.UserRepository
import dto.UserDTO
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.BatchUpdateException
import java.sql.SQLIntegrityConstraintViolationException

class UserService(
    private val repository: UserRepository,
) {

    private val logger: Logger = LoggerFactory.getLogger(UserService::class.simpleName)

    fun getUserById(userId: Int): UserDTO? {
        return try {
            val userEntity = repository.getUserById(userId)
            return UserDTO(
                userEntity.id.value,
                userEntity.mail,
                userEntity.firstName,
                userEntity.lastName,
                userEntity.patronymic,
                userEntity.phoneNumber,
            )
        } catch (e: Exception) {
            val dbException = DataBaseException("Unable to find user: $userId", e)
            when ((e as? ExposedSQLException)?.cause) {
                is SQLIntegrityConstraintViolationException, is BatchUpdateException ->
                    logger.error("SQL constraint violated", dbException)
                else ->
                    logger.error("Unexpected exception: ${e.message}", dbException)
            }
            null
        }
    }

    fun addUser(user: UserDTO): Boolean {
        return try {
            repository.addUser(user)
            true
        } catch (e: Exception) {
            val dbException = DataBaseException("Unable to create user: $user", e)
            when ((e as? ExposedSQLException)?.cause) {
                is SQLIntegrityConstraintViolationException, is BatchUpdateException ->
                    logger.error("SQL constraint violated", dbException)
                else ->
                    logger.error("Unexpected exception: ${e.message}", dbException)
            }
            false
        }
    }
}