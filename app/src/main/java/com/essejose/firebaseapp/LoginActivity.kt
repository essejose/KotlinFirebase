package com.essejose.firebaseapp

    import android.support.v7.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Toast
    import com.essejose.firebaseapp.extensions.getText
    import com.google.firebase.auth.FirebaseAuth
    import kotlinx.android.synthetic.main.activity_login.*
    import com.google.android.gms.tasks.OnCompleteListener



class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance();

        btnCriar.setOnClickListener {

            mAuth.createUserWithEmailAndPassword(inpuEmail.getText(),inputPassword.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Authentication Success.",
                                    Toast.LENGTH_SHORT).show()
                            val user = mAuth.currentUser

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
        }

        btnLogin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(inpuEmail.getText(), inputPassword.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(this, "Authentication Success.",
                                    Toast.LENGTH_SHORT).show()

                            // Sign in success, update UI with the signed-in user's information
                             val user = mAuth.currentUser

                        } else {
                            // If sign in fails, display a message to the user.
                              Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()

                        }

                        // ...
                    }
        }

        logout.setOnClickListener {

            mAuth.signOut();
            Toast.makeText(this, "signOut",
                    Toast.LENGTH_SHORT).show()

        }

        btnEnviar.setOnClickListener {
            val user = mAuth.currentUser

            user?.sendEmailVerification()   
                    ?.addOnCompleteListener(this, OnCompleteListener { task ->

                        if(task.isSuccessful){
                            Toast.makeText(this, "sendEmailVerification Success",
                                    Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(this, "sendEmailVerification failed",
                                    Toast.LENGTH_SHORT).show()
                        }

                    })


        }

    }
}
