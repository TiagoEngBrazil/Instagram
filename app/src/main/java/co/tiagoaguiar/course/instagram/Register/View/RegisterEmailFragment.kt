package co.tiagoaguiar.course.instagram.Register.View


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.Register.RegisterEmail
import co.tiagoaguiar.course.instagram.Register.presentation.RegisterEmailPresenter
import co.tiagoaguiar.course.instagram.common.base.DependencyInjector
import co.tiagoaguiar.course.instagram.common.util.txtWatcher
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterEmailBinding

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {

    private var binding: FragmentRegisterEmailBinding? = null
    private var framgmentAttachListener: FragmentAttachListener? = null

    override lateinit var presenter: RegisterEmail.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterEmailBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterEmailPresenter(this, repository)

        binding?.let {
            with(it) {
                registerTxtLogin.setOnClickListener {
                    activity?.finish()
                }

                registerBtnNext.setOnClickListener {
                    presenter.create(
                        registerEmail.text.toString()
                    )
                }

                registerEmail.addTextChangedListener(watcher)
                registerEmail.addTextChangedListener(txtWatcher {
                    displayEmailFailure(null)
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

    override fun onDestroy() {
        binding = null
        framgmentAttachListener = null
        presenter.onDestroy()

        super.onDestroy()
    }

    private val watcher = txtWatcher {
        binding?.registerBtnNext?.isEnabled =
            binding?.registerEmail?.text.toString()
                .isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerBtnNext?.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding?.registerEmailInput?.error = emailError?.let { getString(it) }
    }

    override fun onEmailFailure(massege: String) {
        binding?.registerEmailInput?.error = massege
    }

    override fun gotToNameAndPassword(email: String) {
        framgmentAttachListener?.goToNameAndPasswordScreen(email)
    }
}