package co.tiagoaguiar.course.instagram.profile

import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView
import co.tiagoaguiar.course.instagram.common.model.UserAuth
import co.tiagoaguiar.course.instagram.common.model.Post

interface Profile {

    interface Presenter: BasePresenter {
        fun fetchUserProfile()
        fun fetchUserPosts()
        fun clear()
    }

    interface View: BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayUserProfile(userAuth: UserAuth)
        fun displayRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
    }

}