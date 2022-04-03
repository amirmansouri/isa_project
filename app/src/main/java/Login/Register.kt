package Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.isa_mobile.MainActivity
import com.example.isa_mobile.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
      val  auth= FirebaseAuth.getInstance()
        val db= FirebaseFirestore.getInstance()
        btnlogin.setOnClickListener{
            intent = Intent(this,Login::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)

        }
        register_compte.setOnClickListener {
            if(checking())
            {
                var email=EmailRegister.text.toString()
                var password= PasswordRegister.text.toString()
                var name=Name.text.toString()
              //  var phone=Phone.text.toString()
                val user= hashMapOf(
                    "Name" to name,
                  //  "Phone" to phone,
                    "email" to email,
                )
                val userISA=db.collection("userISA")
                val query =userISA.whereEqualTo("email",email).get()
                    .addOnSuccessListener {
                            tasks->
                        if(tasks.isEmpty)
                        {
                            auth.createUserWithEmailAndPassword(email,password)
                                .addOnCompleteListener(this){
                                        task->
                                    if(task.isSuccessful)
                                    {
                                        userISA.document(name).set(user)
                                        val intent=Intent(this,Login::class.java)
                                        intent.putExtra("email",email)
                                        startActivity(intent)
                                        finish()
                                    }
                                    else
                                    {
                                        Toast.makeText(this,"Authentication Failed", Toast.LENGTH_LONG).show()
                                    }
                                }
                        }
                        else
                        {
                            Toast.makeText(this,"User Already Registered", Toast.LENGTH_LONG).show()
                            val intent= Intent(this, Login::class.java)
                            startActivity(intent)
                        }
                    }
            }
            else{
                Toast.makeText(this,"Enter the Details", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun checking():Boolean{
        if(Name.text.toString().trim{it<=' '}.isNotEmpty()
         //   && Phone.text.toString().trim{it<=' '}.isNotEmpty()
            && EmailRegister.text.toString().trim{it<=' '}.isNotEmpty()
            && PasswordRegister.text.toString().trim{it<=' '}.isNotEmpty()
        )
        {
            return true
        }
        return false
    }
    }
