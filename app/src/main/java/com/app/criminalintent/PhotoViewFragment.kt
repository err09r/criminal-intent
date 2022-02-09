package com.app.criminalintent

import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

private const val ARG_PHOTO_URI = "photoUri"

class PhotoViewFragment : DialogFragment() {
    private lateinit var imageView: ImageView
    private var image: Bitmap? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_photo_view, container, false)
        imageView = view.findViewById(R.id.image_holder)
        imageView.setOnClickListener {
            this.dismiss()
        }

        dialog?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        val photoUri = arguments?.getParcelable(ARG_PHOTO_URI) as Uri? ?: return view
        image = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, photoUri)
        imageView.setImageBitmap(image)
        return view
    }


    companion object {
        fun newInstance(photoUri: Uri): PhotoViewFragment {
            val args = Bundle().apply { putParcelable(ARG_PHOTO_URI, photoUri) }
            return PhotoViewFragment().apply {
                arguments = args
            }
        }
    }
}