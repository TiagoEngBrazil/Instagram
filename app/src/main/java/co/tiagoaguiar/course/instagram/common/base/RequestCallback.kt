package co.tiagoaguiar.course.instagram.common.base

interface RequestCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(massage: String)
    fun onComplete()
}