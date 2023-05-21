package co.tiagoaguiar.course.instagram.Register.data

import co.tiagoaguiar.course.instagram.common.model.UserAuth

interface RegisterCallback {
    fun onSuccess()
    fun onFailure(massage: String)
    fun onComplete()
}