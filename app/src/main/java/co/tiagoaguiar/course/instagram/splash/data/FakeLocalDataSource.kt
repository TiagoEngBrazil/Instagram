package co.tiagoaguiar.course.instagram.splash.data

import co.tiagoaguiar.course.instagram.common.model.DataBase

class FakeLocalDataSource: SplashDataSource {
    override fun seccion(callback: SplashCallBack) {
        if (DataBase.sessoinAuth != null) {
            callback.onSucess()
        } else {
            callback.onFailure()
        }
    }
}