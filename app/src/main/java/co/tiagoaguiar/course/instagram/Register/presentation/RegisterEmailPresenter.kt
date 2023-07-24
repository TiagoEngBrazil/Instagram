package co.tiagoaguiar.course.instagram.Register.presentation

import android.util.Patterns
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.Register.RegisterEmail
import co.tiagoaguiar.course.instagram.Register.data.RegisterCallback
import co.tiagoaguiar.course.instagram.Register.data.RegisterRepository

class RegisterEmailPresenter(
    private var view: RegisterEmail.View?,
    private val repository: RegisterRepository
): RegisterEmail.Presenter {

    override fun create(email: String) {

        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (!isEmailValid) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }

        if (isEmailValid) {
            view?.showProgress(true)

            repository.create(email, object : RegisterCallback {
                override fun onSuccess() {
                    view?.gotToNameAndPassword(email)
                }

                override fun onFailure(massage: String) {
                    view?.onEmailFailure(massage)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }

            })
        }
    }

    override fun onDestroy() {
        view = null
    }

}