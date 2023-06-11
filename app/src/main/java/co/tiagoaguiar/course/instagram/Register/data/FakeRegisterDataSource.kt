package co.tiagoaguiar.course.instagram.Register.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.model.DataBase
import co.tiagoaguiar.course.instagram.common.model.Photo
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
                val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password)

                val created = DataBase.usersAuth.add(newUser)

                if (created) {
                    DataBase.sessoinAuth = newUser
                    callBack.onSuccess()
                } else {
                    callBack.onFailure("Erro interno do servidor!")
                }
            }

            callBack.onComplete()
        }, 2000)
    }

    override fun upDateUser(photoUri: Uri, callBack: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = DataBase.sessoinAuth

            if (userAuth == null) {
                callBack.onFailure("usuário não encontrado!")

            } else {
                val newPhoto = Photo(userAuth.uuid, photoUri)

                val created = DataBase.photos.add(newPhoto)

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