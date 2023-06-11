package co.tiagoaguiar.course.instagram.Register

import android.net.Uri
import androidx.annotation.StringRes
import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView

interface RegisterPhoto {

    interface Presenter: BasePresenter {
        fun updateUser(photoUri: Uri)
    }

    interface View: BaseView<Presenter> {

        fun showProgress(enabled: Boolean)

        fun onUpdateFailure(massege: String)

        fun onUpdateSucess()
    }

}