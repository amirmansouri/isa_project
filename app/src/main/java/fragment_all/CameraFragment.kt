package fragment_all

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.SystemClock
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.isa_mobile.R
import com.example.isa_mobile.databinding.ActivityHomeBinding
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_camera.*
import kotlinx.android.synthetic.main.fragment_camera.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*


class cameraFragment : Fragment() {
    lateinit var binding: ActivityHomeBinding
    lateinit var ImageUri: URI
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_camera, container, false)


        view.bcamera.setOnClickListener(View.OnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 100);
            //  uploadImag();
        })


        return view
    }

    private fun uploadImag() {
        val progressDialog = ProgressDialog(this@cameraFragment.context)
        progressDialog.setMessage("uploading ....")
        progressDialog.setCancelable(false)
        progressDialog.show()
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance("images/$fileName")
        //  storageReference.putFile(ImageUri)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            val takeimage = data?.extras?.get("data") as Bitmap
            imagescan.setImageBitmap(takeimage)

        } else {

        }
    }

}