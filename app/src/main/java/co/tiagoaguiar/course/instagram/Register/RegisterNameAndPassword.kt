package co.tiagoaguiar.course.instagram.Register

import androidx.annotation.StringRes
import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView

interface RegisterNameAndPassword {

    interface Presenter: BasePresenter {
        fun create(email: String,name: String, password: String, confirm: String)
    }

    interface View: BaseView<Presenter> {

        fun showProgress(enabled: Boolean)

        fun displayNameFailure(@StringRes nameError: Int?)

        fun displayPasswordFailure(@StringRes passError: Int?)

        fun onCreateFailure(massege: String)

        fun onCreateSucess(name: String)
    }

}