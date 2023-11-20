package com.example.astonhw4_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide

class EditUserFragment : Fragment() {

    private var imagePickerLauncher: ActivityResultLauncher<Intent>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextFirstName = view.findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = view.findViewById<EditText>(R.id.editTextLastName)
        val editTextPhoneNumber = view.findViewById<EditText>(R.id.editTextPhoneNumber)
        val imageViewPhoto = view.findViewById<ImageView>(R.id.userPhoto)
        val user = arguments?.getSerializable(USER_EXTRA) as User

        editTextFirstName.setText(user.firstName)
        editTextLastName.setText(user.lastName)
        editTextPhoneNumber.setText(user.phoneNumber)
        Glide.with(view)
            .load(user.photoUrl)
            .error(R.drawable.ic_launcher_background)
            .into(imageViewPhoto)


        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImageUri = result.data?.data

                selectedImageUri?.let {
                    Glide.with(this)
                        .load(it)
                        .into(imageViewPhoto)
                }
                user.photoUrl = selectedImageUri.toString()
            }
        }

        imageViewPhoto.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            imagePickerLauncher?.launch(galleryIntent)
        }

        view.findViewById<Button>(R.id.btnSave).setOnClickListener {
            if(editTextFirstName.text.isEmpty() ||
                editTextLastName.text.isEmpty() ||
                editTextPhoneNumber.text.isEmpty()){
                Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
            else{
                val newUser = User(user.id, editTextFirstName.text.toString(), editTextLastName.text.toString(), editTextPhoneNumber.text.toString(), user.photoUrl)
                (requireActivity() as SaveButtonListener).onSaveButtonListener(newUser)
            }
        }
    }

    interface SaveButtonListener{
        fun onSaveButtonListener(user: User)
    }

    companion object {
        const val EDIT_USER_FRAGMENT = "EDIT_USER_FRAGMENT"
        private const val USER_EXTRA = "USER"

        fun newInstance(user: User) = EditUserFragment().apply {
            arguments = Bundle().apply {
                putSerializable(USER_EXTRA, user)
            }
        }
    }
}