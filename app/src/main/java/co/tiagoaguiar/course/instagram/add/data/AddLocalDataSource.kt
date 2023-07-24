package co.tiagoaguiar.course.instagram.add.data

import co.tiagoaguiar.course.instagram.common.model.DataBase
import co.tiagoaguiar.course.instagram.common.model.UserAuth

class AddLocalDataSource: AddDataSource {
    override fun fetchSesion(): UserAuth {
        return DataBase.sessoinAuth ?: throw RuntimeException("usuário não logado!!")
    }
}