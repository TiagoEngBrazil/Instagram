package co.tiagoaguiar.course.instagram.splash.data

class SplashRepository(private val dataSource: SplashDataSource) {
     fun session(callBack: SplashCallBack) {
         dataSource.seccion(callBack)
     }

}