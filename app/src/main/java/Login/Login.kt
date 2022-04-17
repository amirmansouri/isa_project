package Login

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.isa_mobile.R
import com.google.firebase.auth.FirebaseAuth
import fragment_all.ProfileFragment
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class Login : AppCompatActivity() {
    // private val mAuth = FirebaseAuth.getInstance()
    var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()


        Registerbtn.setOnClickListener {
            var intent = Intent(this, Register::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            // finish()
        }
        changeLangue.setOnClickListener {
            showlang()
        }
        Login.setOnClickListener {
            if (checking()) {
                val email = Email.text.toString()
                val password = Password.text.toString()

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->

                        if (task.isSuccessful) {
                            var intent = Intent(this, homeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            val wrong = getString(R.string.wrong_data)
                            Toast.makeText(this, "$wrong", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                val detail = getString(R.string.Enter_the_Details)
                Toast.makeText(this, "$detail", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checking(): Boolean {
        if (Email.text.toString().trim { it <= ' ' }.isNotEmpty()
            && Password.text.toString().trim { it <= ' ' }.isNotEmpty()

        ) {
            return true
        }
        return false
    }

    private fun showlang() {
        val listlangue = arrayOf("عربي")
        val mBuilder = AlertDialog.Builder(this@Login)
        mBuilder.setTitle("chose Lang")
        mBuilder.setSingleChoiceItems(listlangue, -1) { dialog, which
            ->
            if (which == 0) {
                setLocate("ar")
                recreate()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }

    private fun setLocate(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }
//    override fun onResume() {
//
//        auth = FirebaseAuth.getInstance()
//        val currentUser = auth.currentUser
//        if(currentUser != null){
//            val i = Intent(this,homeActivity::class.java)
//            startActivity(i)
//        }
//        super.onResume()
//    }
}



