package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.base.Cache
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.DataBase
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

class ProfileLocalDataSource(
    private val profileCache: Cache<Pair<UserAuth, Boolean?>>,
    private val postsCache: Cache<List<Post>>
): ProfileDataSource {
    override fun fetchUserProfile(useUUID: String, callback: RequestCallback<Pair<UserAuth, Boolean?>>) {
        val userAuth = profileCache.get(useUUID)
        if (userAuth != null) {
            callback.onSuccess(userAuth)
        } else {
            callback.onFailure("Usuário não encontrado!!")
        }
        callback.onComplete()
    }

    override fun fetchUserPosts(useUUID: String, callback: RequestCallback<List<Post>>) {
        val posts = postsCache.get(useUUID)
        if (posts != null) {
            callback.onSuccess(posts)
        } else {
            callback.onFailure("Não existem posts!")
        }
        callback.onComplete()
    }

    override fun fetchSession(): UserAuth {
        return DataBase.sessoinAuth ?: throw RuntimeException("Usuário não logado!!")
    }

    override fun putUser(response: Pair<UserAuth, Boolean?>) {
        profileCache.put(response)
    }

    override fun putPosts(response: List<Post>?) {
        postsCache.put(response)
    }
}