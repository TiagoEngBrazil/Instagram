package co.tiagoaguiar.course.instagram.add

import android.net.Uri
import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView

interface Add {

    interface Presenter: BasePresenter {
        fun creatPost(uri: Uri, caption: String)
    }

    interface View: BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayRequestSuccess()
        fun displayRequestFalure(message: String)
    }

}