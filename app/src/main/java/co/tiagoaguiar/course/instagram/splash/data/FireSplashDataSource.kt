package co.tiagoaguiar.course.instagram.splash.data

import com.google.firebase.auth.FirebaseAuth

class FireSplashDataSource: SplashDataSource {
    override fun seccion(callback: SplashCallBack) {
        if (FirebaseAuth.getInstance().uid != null) {
            callback.onSucess()
        } else {
            callback.onFailure()
        }
    }
}