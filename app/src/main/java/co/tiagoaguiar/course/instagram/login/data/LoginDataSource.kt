package co.tiagoaguiar.course.instagram.login.data


interface LoginDataSource {
    fun login(email: String, password: String, callBack: LoginCallback)
}