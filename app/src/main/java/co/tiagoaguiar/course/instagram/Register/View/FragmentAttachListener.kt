package co.tiagoaguiar.course.instagram.Register.View

interface FragmentAttachListener {
    fun goToNameAndPasswordScreen(email: String)

    fun goToWelcomeScreen(name: String)

    fun goToPhotoScreen()
}