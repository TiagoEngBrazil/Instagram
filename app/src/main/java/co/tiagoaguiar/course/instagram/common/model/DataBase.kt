package co.tiagoaguiar.course.instagram.common.model

import java.util.*

object DataBase {

    val usersAuth = hashSetOf<UserAuth>()

    var sessoinAuth: UserAuth? = null

    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "UserA", "userA@gmail.com", "12345678"))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "UserB","userB@gmail.com", "87654321"))
    }

}