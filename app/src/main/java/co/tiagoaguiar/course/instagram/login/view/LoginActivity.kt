package co.tiagoaguiar.course.instagram.login.view


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.tiagoaguiar.course.instagram.Register.View.RegisterActivity
import co.tiagoaguiar.course.instagram.common.base.DependencyInjector
import co.tiagoaguiar.course.instagram.common.util.txtWatcher
import co.tiagoaguiar.course.instagram.databinding.ActivityLoginBinding
import co.tiagoaguiar.course.instagram.login.Login
import co.tiagoaguiar.course.instagram.login.presentation.LoginPresenter
import co.tiagoaguiar.course.instagram.main.view.MainActivity

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding

    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        presenter = LoginPresenter(this, DependencyInjector.longinRepository())

        with(binding) {

            editEmail.addTextChangedListener(watcher)

            editEmail.addTextChangedListener(txtWatcher{
                displayEmailFailure(null)
            })

            editPassword.addTextChangedListener(watcher)
            editPassword.addTextChangedListener(txtWatcher {
                displayPasswordFailure(null)
            })

            loginBtnEnter.setOnClickListener {
                presenter.login(editEmail.text.toString(), editPassword.text.toString())
            }

            loginTxtRegister.setOnClickListener {
                gotToResgisterScreen()
            }
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private val watcher = txtWatcher {
        binding.loginBtnEnter.isEnabled =
            binding.editEmail.text.toString().isNotEmpty() && binding.editPassword.text.toString()
                .isNotEmpty()
    }

    private fun gotToResgisterScreen() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun showProgress(enable: Boolean) {
        binding.loginBtnEnter.showProgress(enable)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.editEmailInput.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.editPasswordInput.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        val intent = Intent(this, MainActivity :: class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onUserUnauthorized(massage: String) {
        Toast.makeText(this, massage, Toast.LENGTH_LONG).show()
    }
}