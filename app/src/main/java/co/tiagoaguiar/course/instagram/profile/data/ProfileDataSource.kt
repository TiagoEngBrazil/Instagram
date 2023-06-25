package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

interface ProfileDataSource {
    fun fetchUserProfile(useUUID: String, callback: RequestCallback<UserAuth>)

    fun fetchUserPosts(useUUID: String, callback: RequestCallback<List<Post>>)
}