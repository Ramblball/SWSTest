package dto

class UserDTO(
    val id: Int?,
    val mail: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val phoneNumber: String
) {
    fun validate(): Boolean {
        return phoneNumber.length == 11
    }

    override fun toString(): String {
        return "User($mail, $firstName, $lastName, $patronymic, $phoneNumber)"
    }
}