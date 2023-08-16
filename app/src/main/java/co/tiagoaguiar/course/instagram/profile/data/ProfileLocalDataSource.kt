package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.base.Cache
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.User
import com.google.firebase.auth.FirebaseAuth

class ProfileLocalDataSource(

    private val profileCache: Cache<Pair<User, Boolean?>>,
    private val postsCache: Cache<List<Post>>

): ProfileDataSource {
    override fun fetchUserProfile(useUUID: String, callback: RequestCallback<Pair<User, Boolean?>>) {
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

    override fun fetchSession(): String {
        return FirebaseAuth.getInstance().uid ?: throw RuntimeException("Usuário não logado!!")
    }

    override fun putUser(response: Pair<User, Boolean?>) {
        profileCache.put(response)
    }

    override fun putPosts(response: List<Post>?) {
        postsCache.put(response)
    }
}