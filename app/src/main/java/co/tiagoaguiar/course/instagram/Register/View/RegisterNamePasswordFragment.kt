package co.tiagoaguiar.course.instagram.Register.View


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.Register.RegisterNameAndPassword
import co.tiagoaguiar.course.instagram.Register.presentation.RegisterNameAndPasswordPresenter
import co.tiagoaguiar.course.instagram.common.base.DependencyInjector
import co.tiagoaguiar.course.instagram.common.util.txtWatcher
import co.tiagoaguiar.course.instagram.databinding.FragmentNewNamePasswordBinding

class RegisterNamePasswordFragment : Fragment(R.layout.fragment_new_name_password),
    RegisterNameAndPassword.View {

    private var binding: FragmentNewNamePasswordBinding? = null
    private var framgmentAttachListener: FragmentAttachListener? = null

    override lateinit var presenter: RegisterNameAndPassword.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNewNamePasswordBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()

        presenter = RegisterNameAndPasswordPresenter(this, repository)

        val email =
            arguments?.getString(KEY_EMAIL) ?: throw IllegalArgumentException("e-mail not found")

        binding?.let {
            with(it) {
                registerTxtLogin.setOnClickListener {
                    activity?.finish()
                }

                registerNameBtnNext.setOnClickListener {
                    presenter.create(
                        email,
                        registerEditName.text.toString(),
                        registerEditPassword.text.toString(),
                        registerEditConfirm.text.toString()
                    )
                }

                registerEditName.addTextChangedListener(watcher)
                registerEditPassword.addTextChangedListener(watcher)
                registerEditConfirm.addTextChangedListener(watcher)

                registerEditName.addTextChangedListener(txtWatcher {
                    displayNameFailure(null)
                })

                registerEditPassword.addTextChangedListener(txtWatcher {
                    displayPasswordFailure(null)
                })

                registerEditConfirm.addTextChangedListener(txtWatcher {
                    displayPasswordFailure(null)
                })
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            framgmentAttachListener = context
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerNameBtnNext?.showProgress(enabled)
    }

    override fun displayNameFailure(nameError: Int?) {
        binding?.registerEditNameInput?.error = nameError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passError: Int?) {
        binding?.registerPasswordInput?.error = passError?.let { getString(it) }
    }

    override fun onCreateFailure(massege: String) {
        Toast.makeText(requireContext(), massege, Toast.LENGTH_LONG).show()
    }

    override fun onCreateSucess(name: String) {
        framgmentAttachListener?.goToWelcomeScreen(name)
    }

    private val watcher = txtWatcher {
        binding?.registerNameBtnNext?.isEnabled =
            binding?.registerEditName?.text.toString()
                .isNotEmpty() && binding?.registerEditPassword?.text.toString()
                .isNotEmpty() && binding?.registerEditConfirm?.text.toString().isNotEmpty()
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }


    companion object {
        const val KEY_EMAIL = "key_email"
    }
}