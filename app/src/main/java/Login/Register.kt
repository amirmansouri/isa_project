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
    override fun onCreate(savedInstanceState: Bundle?) {
        val email = findViewById<EditText>(R.id.EmailRegister)

        lateinit var database: DatabaseReference
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
            // auth()
            savedata()
        }


//        register_compte.setOnClickListener {
//            if(checking())
//            {
//                var email=EmailRegister.text.toString()
//                var password= PasswordRegister.text.toString()
//                var name=Name.text.toString()
//                var phone=Phone.text.toString()
//                //  var phone=Phone.text.toString()
//                val user= hashMapOf(
//                    "Name" to name,
//                    "Phone" to phone,
//                    "email" to email,
//                )
//                val userISA=db.collection("userISA")
//                val query =userISA.whereEqualTo("email",email).get()
//                    .addOnSuccessListener {
//                            tasks->
//                        if(tasks.isEmpty)
//                        {
//                            auth.createUserWithEmailAndPassword(email,password)
//                                .addOnCompleteListener(this){
//                                        task->
//                                    if(task.isSuccessful)
//                                    {
//                                        userISA.document(email).set(user)
//                                        val intent=Intent(this,Login::class.java)
//                                        intent.putExtra("email",email)
//                                        startActivity(intent)
//                                        finish()
//                                    }
//                                    else
//                                    {
//                                        Toast.makeText(this,"Authentication Failed", Toast.LENGTH_LONG).show()
//                                    }
//                                }
//                        }
//                        else
//                        {
//                            Toast.makeText(this,"User Already Registered", Toast.LENGTH_LONG).show()
//                            val intent= Intent(this, Login::class.java)
//                            startActivity(intent)
//                        }
//                    }
//            }
//            else{
//                Toast.makeText(this,"Enter the Details", Toast.LENGTH_LONG).show()
//            }
//        }
    }

    private fun savedata() {

        val email = EmailRegister.text.toString().trim()
        val name = Name.text.toString().trim()
        val phone = Phone.text.toString().trim()
        val pass = PasswordRegister.text.toString().trim()
        if (email.isEmpty() && name.isEmpty() && phone.isEmpty() && pass.isEmpty()) {
            val email_vide = getString(R.string.email_registere_vide)
            EmailRegister.error = email_vide
            val phone_vide = getString(R.string.phone_vide)
            Phone.error = phone_vide
            val name_vide = getString(R.string.name_vide)
            Name.error = name_vide
            val pass_vide = getString(R.string.pass_vide)
            PasswordRegister.error = pass_vide

        }

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val Nameid = ref.push().key
        val user = User(Nameid.toString(), email, name, phone)
//            if (Nameid != null) {
//                ref.child(name).setValue(user).addOnCompleteListener {
//                    Toast.makeText(this, " s7i7a ", Toast.LENGTH_LONG).show()
//                }
//
//        }

        if (name.isNotEmpty()) {
            ref.child(name).setValue(user).addOnCompleteListener {
                Toast.makeText(this, " s7i7a ", Toast.LENGTH_LONG).show()

            }

        } else {
            Toast.makeText(this, " thabet f donnees te3k ms ", Toast.LENGTH_LONG).show()
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

    private fun auth() {
        val name = EmailRegister.text.toString().trim()

        val auth = FirebaseAuth.getInstance()

        val email = EmailRegister.text.toString()
        val password = PasswordRegister.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    savedata()
                    Toast.makeText(this, "creation d compte", Toast.LENGTH_LONG).show()
                }
            }
    }
}
