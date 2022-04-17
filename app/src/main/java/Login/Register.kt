package Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.isa_mobile.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.protobuf.Empty
//import com.google.firebase.firestore.FirebaseFirestore
import fragment_all.User
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_todo.view.*

class Register : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        //  val db = FirebaseFirestore.getInstance()
        btnlogin.setOnClickListener {
            intent = Intent(this, Login::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
        }

        register_compte.setOnClickListener {
            if (checking()) {
                register()
            } else {
                val email_vide = getString(R.string.email_registere_vide)
                EmailRegister.error = email_vide
                val phone_vide = getString(R.string.phone_vide)
                Phone.error = phone_vide
                val name_vide = getString(R.string.name_vide)
                Name.error = name_vide
                val pass_vide = getString(R.string.pass_vide)
                PasswordRegister.error = pass_vide
            }
        }
    }

    private fun checking(): Boolean {
        if (Name.text.toString().trim { it <= ' ' }.isNotEmpty()
            && Phone.text.toString().trim { it <= ' ' }.isNotEmpty()
            && EmailRegister.text.toString().trim { it <= ' ' }.isNotEmpty()
            && PasswordRegister.text.toString().trim { it <= ' ' }.isNotEmpty()
        ) {
            return true
        }
        return false
    }

    private fun register() {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("user")
        auth.createUserWithEmailAndPassword(
            EmailRegister.text.toString(),
            PasswordRegister.text.toString()
        )
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val currentUser = auth.currentUser
                    val currentUSerDatabase = databaseReference.child((currentUser?.uid!!))

                    currentUSerDatabase.child("email").setValue(EmailRegister.text.toString())
                    currentUSerDatabase.child("name").setValue(Name.text.toString())
                    currentUSerDatabase.child("phone").setValue(Phone.text.toString())

                    Toast.makeText(this, "Registration Success. ", Toast.LENGTH_LONG).show()
                    finish()

                } else {
                    Toast.makeText(
                        this,
                        "Registration failed, please try again! ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

    }

}




