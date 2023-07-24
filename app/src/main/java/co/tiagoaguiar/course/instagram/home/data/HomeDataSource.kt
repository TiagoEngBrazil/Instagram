package co.tiagoaguiar.course.instagram.home.data

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth
import kotlin.UnsupportedOperationException

interface HomeDataSource {

    fun fetchFeed(useUUID: String, callback: RequestCallback<List<Post>>)

    fun fetchSession(): UserAuth { throw UnsupportedOperationException() }

    fun putUser(response: UserAuth) { throw UnsupportedOperationException() }

    fun putFeed(response: List<Post>?) { throw UnsupportedOperationException() }
}