package co.tiagoaguiar.course.instagram.add.data

import android.net.Uri
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.DataBase
import co.tiagoaguiar.course.instagram.common.model.Post
import java.util.*

class AddFakeRemoteDataSource : AddDataSource {

    override fun creatPost(
        userUUID: String,
        uri: Uri,
        caption: String,
        callback: RequestCallback<Boolean>,
    ) {
        android.os.Handler(Looper.getMainLooper()).postDelayed({

            var posts = DataBase.posts[userUUID]

            if (posts == null) {
                posts = mutableSetOf()
                DataBase.posts[userUUID] = posts
            }

            val post = Post(UUID.randomUUID().toString(),
                null,
                caption,
                System.currentTimeMillis(),
                null)

            posts.add(post)

            var followers = DataBase.follwers[userUUID]

            if (followers == null) {
                followers = mutableSetOf()
                DataBase.follwers[userUUID] = followers
            } else {
                for (follower in followers) {
                    DataBase.feeds[follower]?.add(post)
                }

                DataBase.feeds[userUUID]?.add(post)
            }

            callback.onSuccess(true)

        }, 1000)
    }

}