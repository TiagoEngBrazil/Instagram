package co.tiagoaguiar.course.instagram.common.base

import co.tiagoaguiar.course.instagram.Register.data.RegisterRepository
import co.tiagoaguiar.course.instagram.login.data.FakeDataSource
import co.tiagoaguiar.course.instagram.login.data.LoginRepository

object DependencyInjector {
    fun longinRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository() : RegisterRepository {
        return RegisterRepository(co.tiagoaguiar.course.instagram.Register.data.FakeRegisterDataSource())
    }
}