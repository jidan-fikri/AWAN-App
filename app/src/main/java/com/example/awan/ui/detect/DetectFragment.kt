package com.example.awan.ui.detect

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.example.awan.BuildConfig
import com.example.awan.R
import com.example.awan.databinding.FragmentDetectBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

private const val FILE_NAME = "photo.jpg"
private const val REQUEST_CODE = 42
private const val GALLERY_REQUEST_CODE = 2
private lateinit var photoFile : File
private lateinit var galleryphotoFile : File
private lateinit var FilePhoto : String
private lateinit var currentPhotoPath : String
lateinit var bitmap: Bitmap

class DetectFragment : Fragment() {
    //ImageView mImageview
    //var imagePicker: ImageView? = null
    //private var resolver: ContentResolver? = requireActivity().contentResolver
    lateinit var imageView: ImageView
    private lateinit var scanViewModel: DetectViewModel
    private var _binding: FragmentDetectBinding? = null
    private var fileUri: Uri? = null
    private var mImageFileLocation = ""
    private val binding get() = _binding!!

    companion object {
        const val SHOWCASE_ID = "SHOWCASE_ID_1"
        const val REQUEST_TAKE_PHOTO = 0
        const val REQUEST_PICK_PHOTO = 2
        const val CAMERA_PIC_REQUEST = 1111

        private val TAG = DetectFragment::class.java.simpleName

        const val MEDIA_TYPE_IMAGE = 1
        private const val IMAGE_DIRECTORY_NAME = "Android File Upload"

        private fun getOutputMediaFile(type: Int): File? {

            val mediaStorageDir = File(
                Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME
            )
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d(
                        TAG, "Oops! Failed create "
                                + IMAGE_DIRECTORY_NAME + " directory"
                    )
                    return null
                }
            }

            SimpleDateFormat(
                "yyyyMMdd_HHmmss",
                Locale.getDefault()
            ).format(Date())
            val mediaFile: File
            if (type == MEDIA_TYPE_IMAGE) {
                mediaFile = File(
                    mediaStorageDir.path + File.separator
                            + "IMG_" + ".jpg"
                )
            } else {
                return null
            }

            return mediaFile
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scanViewModel =
            ViewModelProvider(this).get(DetectViewModel::class.java)

        _binding = FragmentDetectBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.uploadBtn.setOnClickListener {

            //Create an Intent with action as ACTION_PICK
            val intent = Intent(Intent.ACTION_PICK)
            // Sets the type as image/*. This ensures only components of type image are selected
            intent.type = "image/*"
            //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            // Launching the Intent
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }

/*        binding.camBtn.setOnClickListener {
            captureImage()

        }*/

        return root
    }
    private fun captureImage(){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getPhotoFile(FILE_NAME)
        val outputUri = FileProvider.getUriForFile(
            requireActivity(),
            BuildConfig.APPLICATION_ID + ".provider",
            photoFile!!
        )
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri)
        startActivityForResult(takePictureIntent, REQUEST_CODE)

        if (context?.let { it1 -> takePictureIntent.resolveActivity(it1.packageManager) } != null) {

        } else {
            Toast.makeText(context, "unable to open camera", Toast.LENGTH_SHORT).show()
        }
    }



    private fun getOutputMediaFileUri(type: Int): Uri {
        return Uri.fromFile(getOutputMediaFile(type))
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    @Throws(IOException::class)
    internal fun createImageFile(): File {

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmSS").format(Date())
        val imageFileName = "IMAGE_" + timeStamp
        val storageDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/photo_saving_app")

        if (!storageDirectory.exists()) storageDirectory.mkdir()
        val image = File(storageDirectory, "$imageFileName.jpg")

        mImageFileLocation = image.absolutePath
        return image
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getPath(context: Context, uri: Uri?): String? {
        var result: String? = null
        val proj = arrayOf(MediaStore.Images.Media._ID)
        val cursor: Cursor? =
            uri?.let { context.contentResolver.query(it, proj, null, null, null) }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val column_index: Int = cursor.getColumnIndexOrThrow(proj[0])
                result = cursor.getString(column_index)
            }
            cursor.close()
        }
        if (result == null) {
            result = "Not found"
        }
        return result
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK)

            when (requestCode) {

                REQUEST_CODE -> {
                    val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
                    binding.imageAnalitics.setImageBitmap(takenImage)

                    binding.buttonProcess.setOnClickListener {
                        val nextIntent = Intent(context, DetectResultActivity::class.java)
                        nextIntent.putExtra("picture", photoFile.absolutePath)
                        Log.e(photoFile.absolutePath, photoFile.absolutePath)
                        startActivity(nextIntent)
                    }

                }

                GALLERY_REQUEST_CODE -> {


                    val selectedImage: Uri = data?.data ?: return
                    val mBitmap: Bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, selectedImage)
                    binding.imageAnalitics.setImageBitmap(mBitmap)

                    val picturePath = context?.let { getPath(it, selectedImage) }

//                    val galleryimage = BitmapFactory.decodeFile(galleryphotoFile.absolutePath)
//                    binding.imagecam.setImageBitmap(galleryimage)

//                    val returnUri: Uri? = data?.data
//                    val contentResolver = requireActivity().contentResolver
//                    val bitmapImage = MediaStore.Images.Media.getBitmap(contentResolver, returnUri)
                    //bitmapImage.scale(1,1)

//                    binding.imagecam.setImageBitmap(galleryimage)
                    //val takenImageGal = BitmapFactory.decodeFile(galleryphotoFile.absolutePath)
                    //binding.imagecam.setImageBitmap(takenImageGal)


                    //btngallery()

                    binding.buttonProcess.setOnClickListener {
                        val nextIntentGallery = Intent(context, DetectResultActivity::class.java)
                        nextIntentGallery.putExtra("picturegal", selectedImage)
                        nextIntentGallery.putExtra("imageUri", selectedImage)
                        if (picturePath != null) {
                            Log.e(picturePath,picturePath)
                        }
                        startActivity(nextIntentGallery)
                    }
                }

            }
    }
}