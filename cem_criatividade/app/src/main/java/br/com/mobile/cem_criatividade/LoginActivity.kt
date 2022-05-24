package br.com.mobile.cem_criatividade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.util.Log
import br.com.mobile.cem_criatividade.view.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener {

            if(editTextEmail.text.trim().toString().isNotEmpty() || editTextPassword.text.trim().toString().isNotEmpty()){
                signInUser(editTextEmail.text.trim().toString(), editTextPassword.text.trim().toString());

            }else{
                Toast.makeText(this,"Input required",Toast.LENGTH_LONG).show()

            }

        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java);
            startActivity(intent)
        }

    }
    fun signInUser(email:String, password:String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener (this) { task ->

                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java);
                    startActivity(intent);
                } else {
                    Toast.makeText(
                        this,
                        "Authentication Error " + task.exception,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}