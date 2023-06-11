package co.tiagoaguiar.course.instagram.Register.presentation

import android.net.Uri
import co.tiagoaguiar.course.instagram.Register.RegisterPhoto
import co.tiagoaguiar.course.instagram.Register.data.RegisterCallback
import co.tiagoaguiar.course.instagram.Register.data.RegisterRepository

class RegisterPhotoPresenter(
    private var view: RegisterPhoto.View?,
    private val repository: RegisterRepository,
) : RegisterPhoto.Presenter {

    override fun updateUser(photoUri: Uri) {
        view?.showProgress(true)

        repository.upDateUser(photoUri, object : RegisterCallback {
            override fun onSuccess() {
                view?.onUpdateSucess()
            }

            override fun onFailure(massage: String) {
                view?.onUpdateFailure(massage)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }

        })
    }

    override fun onDestroy() {
        view = null
    }

}