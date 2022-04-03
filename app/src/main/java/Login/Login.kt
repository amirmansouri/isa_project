package Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.isa_mobile.R
import com.google.firebase.auth.FirebaseAuth
import fragment_all.ProfileFragment
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {
   // private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
      val  auth= FirebaseAuth.getInstance()

        Registerbtn.setOnClickListener {
            var intent =Intent(this,Register::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
           // finish()
        }


        Login.setOnClickListener {
            if(checking()){
                val email=Email.text.toString()
                val password= Password.text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            var intent =Intent(this,homeActivity::class.java)
                            intent.putExtra("email",email)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Wrong Details", Toast.LENGTH_LONG).show()
                        }
                    }
            }
            else{
                Toast.makeText(this,"Enter the Details",Toast.LENGTH_LONG).show()
            }
        }
    }



    private fun checking():Boolean
    {
        if(Email.text.toString().trim{it<=' '}.isNotEmpty()
            && Password.text.toString().trim{it<=' '}.isNotEmpty())
        {
            return true
        }
        return false
                }

        }
//        val textV = findViewById<TextView>(R.id.textView2)
//        val buttonClick = findViewById<Button>(R.id.blogin)
//val signb = findViewById<TextView>(R.id.signupb)
//        signb.setOnClickListener{
//            val intent = Intent(this, Register::class.java)
//            startActivity(intent)
//        }
//        buttonClick.setOnClickListener {
//              //  val intent = Intent(this, homeActivity::class.java)
//
//login()
//
//        }
//
//    }
//    private fun login () {
//        val emailTxt = findViewById<View>(R.id.emailTxt) as EditText
//        var email = emailTxt.text.toString()
//        val passwordTxt = findViewById<View>(R.id.passwordTxt) as EditText
//        var password = passwordTxt.text.toString()
//
//        if (!email.isEmpty() && !password.isEmpty()) {
//            this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener ( this, OnCompleteListener<AuthResult> { task ->
//                if (task.isSuccessful) {
//                    //     startActivity(Intent(this, Timeline::class.java))
//                    Toast.makeText(this, "mrgla ", Toast.LENGTH_LONG).show()
//
//                    val intent = Intent(this, homeActivity::class.java)
//            startActivity(intent)
//                } else {
//                    Toast.makeText(this, "thabett", Toast.LENGTH_SHORT).show()
//                }
//            })
//
//        }else {
//            Toast.makeText(this, "8alet", Toast.LENGTH_SHORT).show()
//        }


