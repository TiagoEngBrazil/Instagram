package co.tiagoaguiar.course.instagram.login.data


interface LoginCallback {
    fun onSuccess()
    fun onFailure(massage: String)
    fun onComplete()
}