package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

class ProfileRepository(private val datasource: ProfileDataSource) {

    fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>) {
        datasource.fetchUserProfile(userUUID, callback)
    }

    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        datasource.fetchUserPosts(userUUID, callback)
    }
}