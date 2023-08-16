package co.tiagoaguiar.course.instagram.home.data

import co.tiagoaguiar.course.instagram.common.base.Cache
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import com.google.firebase.auth.FirebaseAuth

class HomeLocalDataSource( private val feedCache: Cache<List<Post>>): HomeDataSource {

    override fun fetchFeed(useUUID: String, callback: RequestCallback<List<Post>>) {
        val posts = feedCache.get(useUUID)
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

    override fun putFeed(response: List<Post>?) {
        feedCache.put(response)
    }
}