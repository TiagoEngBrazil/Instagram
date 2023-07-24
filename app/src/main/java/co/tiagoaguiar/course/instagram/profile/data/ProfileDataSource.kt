package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth
import kotlin.UnsupportedOperationException

interface ProfileDataSource {
    fun fetchUserProfile(useUUID: String, callback: RequestCallback<UserAuth>)

    fun fetchUserPosts(useUUID: String, callback: RequestCallback<List<Post>>)

    fun fetchSession(): UserAuth { throw UnsupportedOperationException() }

    fun putUser(response: UserAuth) { throw UnsupportedOperationException() }

    fun putPosts(response: List<Post>?) { throw UnsupportedOperationException() }
}