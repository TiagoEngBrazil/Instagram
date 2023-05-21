package co.tiagoaguiar.course.instagram.Register.data


interface RegisterDataSource {
    fun create(email: String, callBack: RegisterCallback)

    fun create(email: String, name: String, password: String, callBack: RegisterCallback)
}