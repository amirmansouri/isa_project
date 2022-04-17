package fragment_all

import Login.Login
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.isa_mobile.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("user")
        loadProfile()
        view.out.setOnClickListener {
            //  Toast.makeText(this@ProfileFragment.context, "mmqg ", Toast.LENGTH_LONG).show()
            val intent = Intent(this@ProfileFragment.context, Login::class.java)
            startActivity(intent)
        }
        return view
    }

    private fun loadProfile() {
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)
        //profile_email.text = user?.email
        userreference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                profile_name.text = snapshot.child("name").value.toString()
                profile_email.text = snapshot.child("email").value.toString()
                profile_num.text = snapshot.child("phone").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}





