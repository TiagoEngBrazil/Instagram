package co.tiagoaguiar.course.instagram.Register.data

interface RegisterCallback {
    fun onSuccess()
    fun onFailure(massage: String)
    fun onComplete()
}