package co.tiagoaguiar.course.instagram.Register.View

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.View.CustonDialog
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterAddPhotoBinding

class RegisterAddPhotoFragment : Fragment(R.layout.fragment_register_add_photo) {

    private var binding: FragmentRegisterAddPhotoBinding? = null

    private var fragmentAttachListener: FragmentAttachListener? = null

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

    private fun openDialog() {
        val custonDialog = CustonDialog(requireContext())

        custonDialog.setTitle(R.string.app_name)

        custonDialog.addButton(R.string.photo, R.string.gallery, R.string.cancel) {
            when (it.id) {
                R.string.photo -> {
                    Log.i("teste", "foto")
                }
                R.string.gallery -> {
                    fragmentAttachListener?.goToGalleryScreen()
                }
            }
        }

        custonDialog.show()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}