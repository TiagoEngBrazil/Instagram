package co.tiagoaguiar.course.instagram.profile.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.DataBase
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

class ProfileFakeRemoteDataSource : ProfileDataSource {
    override fun fetchUserProfile(useUUID: String, callback: RequestCallback<Pair<UserAuth, Boolean?>>) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = DataBase.usersAuth.firstOrNull { useUUID == it.uuid }

            if (userAuth != null) {
                if (userAuth == DataBase.sessoinAuth) {
                    callback.onSuccess(Pair(userAuth, null))
                } else {
                    val followings = DataBase.follwers[DataBase.sessoinAuth!!.uuid]

                    val destUser = followings?.firstOrNull { it == useUUID }
                    // destUder != null -> estou seguindo!

                    callback.onSuccess(Pair(userAuth, destUser != null))
                }
            } else {
                callback.onFailure("Usuário já cadastrado!")
            }

            callback.onComplete()
        }, 2000)
    }

    override fun fetchUserPosts(useUUID: String, callback: RequestCallback<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({

            val posts = DataBase.posts[useUUID]

            callback.onSuccess(posts?.toList() ?: emptyList())


            callback.onComplete()
        }, 2000)
    }
}