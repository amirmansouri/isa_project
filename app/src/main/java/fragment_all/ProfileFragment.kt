package fragment_all


import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.isa_mobile.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var TextView: TextView
    private lateinit var database: DatabaseReference
    lateinit var firestore: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
//        val myString = arguments?.getString("message")
//        Toast.makeText(this@ProfileFragment.context, " msg $myString", Toast.LENGTH_LONG).show()
        if (getArguments() != null) {
         val   mParam1 = getArguments()?.getString("params");
            Toast.makeText(this@ProfileFragment.context, " msg $mParam1", Toast.LENGTH_LONG).show()
        }

 //    readData()
        return view

    }
    private fun readData() {

        //      profile_email.text=emailConnected.toString()

//            val db = FirebaseFirestore.getInstance()
//            db.collection("userISA")
//                .get().addOnCompleteListener {
//                    val result: StringBuffer = StringBuffer()
//                    if (it.isSuccessful) {
//                        for (document in it.result!!) {
//
//
//                      //  if (document.id.equals("salah")) {
//db.collection("userISA").document("amir").collection("Name").get().then(que)
//
//
//                              profile_name.text=result.append(document.data.getValue("Name"))
//                                profile_email.text=(document.data.getValue("email") as CharSequence?)
//                             //   profile_num.text=result.append(document.data.getValue("phone"))
//                            }
//
//
//                        }
//
//                    }
        val amir = "amirmansouri@gmail.fr"
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("userISA").document(amir)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    profile_email.text = document.data?.getValue("email").toString()
                    profile_name.text = document.data?.getValue("Name").toString()

                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }
}





