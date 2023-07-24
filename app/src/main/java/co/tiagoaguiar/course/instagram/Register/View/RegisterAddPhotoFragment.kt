package co.tiagoaguiar.course.instagram.Register.View

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.Register.RegisterPhoto
import co.tiagoaguiar.course.instagram.Register.presentation.RegisterPhotoPresenter
import co.tiagoaguiar.course.instagram.common.View.CropperImageFragment
import co.tiagoaguiar.course.instagram.common.View.CustonDialog
import co.tiagoaguiar.course.instagram.common.base.DependencyInjector
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterAddPhotoBinding

class RegisterAddPhotoFragment : Fragment(R.layout.fragment_register_add_photo), RegisterPhoto.View {

    private var binding: FragmentRegisterAddPhotoBinding? = null

    private var fragmentAttachListener: FragmentAttachListener? = null

    override lateinit var presenter: RegisterPhoto.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("cropkey") { requestKey, bundle ->
            val uri = bundle.getParcelable<Uri>(CropperImageFragment.KEY_URI)
            onCropImageResult(uri)
        }
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        return inflater.inflate(R.layout.fragment_register_add_photo, container, false)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterAddPhotoBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()

        presenter = RegisterPhotoPresenter(this, repository)



        binding?.let {
            with(it) {
                registerBtnJump.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }

                registerBtnNext.isEnabled = true

                registerBtnNext.setOnClickListener {
                    openDialog()
                }

            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener)
            fragmentAttachListener = context
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerBtnNext?.showProgress(enabled)
    }

    override fun onUpdateFailure(massege: String) {
        Toast.makeText(requireContext(), massege, Toast.LENGTH_LONG).show()
    }

    override fun onUpdateSucess() {
        fragmentAttachListener?.goToMainScreen()
    }

    private fun openDialog() {
        val custonDialog = CustonDialog(requireContext())

        custonDialog.setTitle(R.string.app_name)

        custonDialog.addButton(R.string.photo, R.string.gallery) {
            when (it.id) {
                R.string.photo -> {
                    fragmentAttachListener?.goToCameraScreen()
                }
                R.string.gallery -> {
                    fragmentAttachListener?.goToGalleryScreen()
                }
            }
        }

        custonDialog.show()
    }

    private fun onCropImageResult(uri: Uri?) {
        if (uri != null) {
            val bitmap = if(Build.VERSION.SDK_INT >= 28) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            }

            binding?.registerImgProfile?.setImageBitmap(bitmap)

            presenter.updateUser(uri)
        }
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }
}