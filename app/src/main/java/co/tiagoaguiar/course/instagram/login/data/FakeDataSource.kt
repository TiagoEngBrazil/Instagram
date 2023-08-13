package co.tiagoaguiar.course.instagram.login.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.model.DataBase

class FakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callBack: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = DataBase.usersAuth.firstOrNull { email == it.email }

            when {
                userAuth == null -> {
                    callBack.onFailure("Usuário não encontrado!")
                }
                userAuth.password != password -> {
                    callBack.onFailure("Senha incorreta!")
                }
                else -> {
                    DataBase.sessoinAuth = userAuth
                    callBack.onSuccess()
                }
            }

            callBack.onComplete()
        }, 2000)
    }
}