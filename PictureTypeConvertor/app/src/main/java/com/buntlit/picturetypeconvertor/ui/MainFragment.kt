package com.buntlit.picturetypeconvertor.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.buntlit.picturetypeconvertor.databinding.FragmentMainBinding
import com.buntlit.picturetypeconvertor.presenter.MainFragmentPresenter
import com.buntlit.picturetypeconvertor.view.MainFragmentView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.time.LocalDate

class MainFragment : MvpAppCompatFragment(), MainFragmentView {
    private var binding: FragmentMainBinding? = null
    private val presenter by moxyPresenter { MainFragmentPresenter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding?.root
    }

    @SuppressLint("IntentReset")
    override fun init() {
        binding?.buttonChoosePicture?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, EXTERNAL_CONTENT_URI)
            intent.type = "image/jpg"
            getUri.launch(intent)
        }
    }

    private val getUri =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                val pathway = it.data?.data.toString()
                presenter.setPathway(pathway)
                showAlert()
            }
        }

    private fun showAlert() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Convertation alert")
            .setMessage("Convertation?")
            .setCancelable(false)
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton("Yes") { _, _ ->
                convertImage()
            }
            .show()
    }

    @SuppressLint("Recycle")
    @Suppress("DEPRECATION")
    private fun convertImage() {
        val pathway = presenter.getPathway()
        if (pathway != "") {
            val contentResolver = requireContext().contentResolver
            val bitmap: Bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(
                    contentResolver, Uri.parse(pathway)
                )
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(
                    contentResolver, Uri.parse(pathway)
                )
            }
            val contentValues = ContentValues().apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    put(
                        MediaStore.Images.Media.DISPLAY_NAME,
                        "ConvertedImage_${LocalDate.now()}.png"
                    )
                    put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Converted")
                }
            }

            val uri = contentResolver.insert(EXTERNAL_CONTENT_URI, contentValues)
            if (uri != null) {
                contentResolver.openOutputStream(uri).use { outputStream ->
                    if (outputStream != null) {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                        Log.d("SOSIGENES", "convertImage: success")
                    }
                }
            }
        } else {
            Log.d("SAUSAGES", "convertImage: null")
        }
    }
}