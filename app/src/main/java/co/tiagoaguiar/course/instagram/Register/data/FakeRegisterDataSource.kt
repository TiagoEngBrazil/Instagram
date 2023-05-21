package co.tiagoaguiar.course.instagram.Register.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.model.DataBase
import co.tiagoaguiar.course.instagram.common.model.UserAuth

import java.util.*

class FakeRegisterDataSource : RegisterDataSource {

    override fun create(email: String, callBack: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = DataBase.usersAuth.firstOrNull { email == it.email }

            if (userAuth == null) {
                callBack.onSuccess()
            } else {
                callBack.onFailure("Usuário já cadastrado!")
            }

            callBack.onComplete()
        }, 2000)
    }

    override fun create(email: String, name: String, password: String, callBack: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = DataBase.usersAuth.firstOrNull { email == it.email }

            if (userAuth != null) {
                callBack.onFailure("usuário já existe")
            } else {
                val created = DataBase.usersAuth.add(
                    UserAuth(UUID.randomUUID().toString(), name, email, password)
                )

                if (created) {
                    callBack.onSuccess()
                } else {
                    callBack.onFailure("Erro interno do servidor!")
                }
            }

            callBack.onComplete()
        }, 2000)
    }
}