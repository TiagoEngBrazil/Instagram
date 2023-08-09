package co.tiagoaguiar.course.instagram.common.model

import android.net.Uri
import java.io.File
import java.util.*

object DataBase {

    val usersAuth = mutableListOf<UserAuth>()

    val posts = hashMapOf<String, MutableSet<Post>>()

    val feeds = hashMapOf<String, MutableSet<Post>>()

    val follwers = hashMapOf<String, MutableSet<String>>()

    var sessoinAuth: UserAuth? = null


    init {
        val userA = UserAuth(UUID.randomUUID().toString(),
            "UserA",
            "userA@gmail.com",
            "12345678",
            Uri.fromFile(File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-07-15-22-09-19-211.jpg")))
        val userB = UserAuth(UUID.randomUUID().toString(),
            "UserB",
            "userB@gmail.com",
            "87654321",
            Uri.fromFile(File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-07-15-22-09-19-211.jpg")))

        usersAuth.add(userA)
        usersAuth.add(userB)

        follwers[userA.uuid] = hashSetOf()
        posts[userA.uuid] = hashSetOf()
        feeds[userA.uuid] = hashSetOf()

        follwers[userB.uuid] = hashSetOf()
        posts[userB.uuid] = hashSetOf()
        feeds[userB.uuid] = hashSetOf()

        for (i in 0..30) {
            val user = UserAuth(UUID.randomUUID().toString(), "User$i", "User$i@gmail.com", "123123123", null)
            usersAuth.add(user)
        }

        sessoinAuth = usersAuth.first()

        follwers[sessoinAuth!!.uuid]?.add(usersAuth[2].uuid)
    }

}