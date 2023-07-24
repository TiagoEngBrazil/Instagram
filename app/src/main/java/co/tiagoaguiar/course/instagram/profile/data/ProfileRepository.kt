package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

class ProfileRepository(private val datasourceFactory: ProfileDataSourceFactory) {

    fun clearcache() {
        val localDataSource = datasourceFactory.createLocalDataSource()
        localDataSource.putPosts(null)
    }

    fun fetchUserProfile(callback: RequestCallback<UserAuth>) {
        val localDataSource = datasourceFactory.createLocalDataSource()
        val userAuth = localDataSource.fetchSession()

        val dataSource = datasourceFactory.createFromUser()

        dataSource.fetchUserProfile(userAuth.uuid, object : RequestCallback<UserAuth> {
            override fun onSuccess(data: UserAuth) {
                localDataSource.putUser(data)
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }

    fun fetchUserPosts(callback: RequestCallback<List<Post>>) {
        val localDataSource = datasourceFactory.createLocalDataSource()
        val userAuth = localDataSource.fetchSession()

        val dataSource = datasourceFactory.createFromPosts()

        dataSource.fetchUserPosts(userAuth.uuid, object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                localDataSource.putPosts(data)
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }
}