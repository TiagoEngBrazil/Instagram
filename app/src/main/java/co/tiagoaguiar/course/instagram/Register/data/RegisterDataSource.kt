package co.tiagoaguiar.course.instagram.Register.data

import android.net.Uri


interface RegisterDataSource {

    fun create(email: String, callBack: RegisterCallback)

    fun create(email: String, name: String, password: String, callBack: RegisterCallback)

    fun upDateUser(photoUri: Uri, callBack: RegisterCallback)
}