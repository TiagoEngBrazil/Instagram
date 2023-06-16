package co.tiagoaguiar.course.instagram.splash.presentation

import co.tiagoaguiar.course.instagram.splash.Splash
import co.tiagoaguiar.course.instagram.splash.data.SplashCallBack
import co.tiagoaguiar.course.instagram.splash.data.SplashRepository

class SplashPresenter(
    private var View: Splash.View?,
    private val repository: SplashRepository

): Splash.Presenter {
    override fun authenticated() {
        repository.session(object : SplashCallBack{
            override fun onSucess() {
                View?.goToMainScreen()
            }

            override fun onFailure() {
                View?.goToLoginScreen()
            }
        })
    }

    override fun onDestroy() {
        View = null
    }


}