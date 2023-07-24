package co.tiagoaguiar.course.instagram.Register

import androidx.annotation.StringRes
import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView

interface RegisterEmail {

    interface Presenter: BasePresenter {
        fun create(email: String)
    }

    interface View: BaseView<Presenter> {

        fun showProgress(enabled: Boolean)

        fun displayEmailFailure(@StringRes emailError: Int?)

        fun onEmailFailure(massege: String)

        fun gotToNameAndPassword(email: String)

    }

}