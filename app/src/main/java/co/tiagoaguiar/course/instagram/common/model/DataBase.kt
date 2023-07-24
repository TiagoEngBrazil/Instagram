package co.tiagoaguiar.course.instagram.common.model

import android.net.Uri
import java.io.File
import java.util.*

object DataBase {

    val usersAuth = mutableListOf<UserAuth>()

    val posts = hashMapOf<String, MutableSet<Post>>()

    val feeds = hashMapOf<String, MutableSet<Post>>()

    val follwers = hashMapOf<String, Set<String>>()

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

        feeds[userA.uuid]?.addAll(
            arrayListOf(
                Post(UUID.randomUUID().toString(),
                    Uri.fromFile(File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-07-15-22-09-19-211.jpg")),
                    "desc",
                    System.currentTimeMillis(),
                    userA),
                Post(UUID.randomUUID().toString(),
                    Uri.fromFile(File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-07-15-22-09-19-211.jpg")),
                    "desc",
                    System.currentTimeMillis(),
                    userA),
                Post(UUID.randomUUID().toString(),
                    Uri.fromFile(File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-07-15-22-09-19-211.jpg")),
                    "desc",
                    System.currentTimeMillis(),
                    userA),
                Post(UUID.randomUUID().toString(),
                    Uri.fromFile(File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-07-15-22-09-19-211.jpg")),
                    "desc",
                    System.currentTimeMillis(),
                    userA),
            )
        )

        feeds[userA.uuid]?.toList()?.let {
            feeds[userB.uuid]?.addAll(it)
        }

        sessoinAuth = usersAuth.first()
    }

}