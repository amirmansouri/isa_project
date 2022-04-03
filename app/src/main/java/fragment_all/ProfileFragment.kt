package fragment_all

import android.os.Bundle
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

    private lateinit var email: TextView

    private lateinit var database: DatabaseReference
    lateinit var firestore: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        readData()
        return view

    }

 private fun readData() {
            val db = FirebaseFirestore.getInstance()
            db.collection("userISA")
                .get().addOnCompleteListener {
                    val result: StringBuffer = StringBuffer()
                    if (it.isSuccessful) {
                        for (document in it.result!!) {
                            if (document.id.equals("salah")) {



                                profile_name.text=result.append(document.data.getValue("Name"))
                                profile_email.text=result.append(document.data.getValue("email"))
                            }


                        }

                    }
                }

        }




}


