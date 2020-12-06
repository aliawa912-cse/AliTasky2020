package awad.ali.alitasky2020.MyUl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import awad.ali.alitasky2020.MyUtils.MyValidations;
import awad.ali.alitasky2020.R;

public class SigninActivity extends AppCompatActivity {
    private EditText etEmail, etPassWord;
    private Button btnLogIn, btnSignUp;
    private Calendar FirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //CHEKE IF I SIGNED IN BEFORE
        Calendar auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null)
        {
            Intent i=new Intent(getBaseContext(),MainActivity.class);
            finish();
            startActivity(i);
        }

        etEmail = findViewById(R.id.edEmail);
        etPassWord = findViewById(R.id.etPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        //4
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //5
                validateForm();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SigninActivity.this, SigninActivity.class);
                startActivity(i);

            }
        });
    }

    private void validateForm() {
        String stEmail = etEmail.getText().toString();
        String stPassw = etPassWord.getText().toString();
        boolean isOk = true;
        if(stEmail.length()<5||stEmail.indexOf('@')==0||stEmail.indexOf('@')>stEmail.length()-2||stEmail.indexOf('.')==0||stEmail.indexOf('.')>stEmail.length()-1||stEmail.lastIndexOf(".")<stEmail.

                indexOf('@') )

        {
            isOk = false;
            etEmail.setError("Wrong Email syntax");
        }

        MyValidations myValidations = new MyValidations();
        if(myValidations.validatePasword(stPassw)==false)

        {
            isOk = false;
            etPassWord.setError("Invalid Password");
        }
        if(isOk)

        {
            Signin(stEmail, stPassw);
        }

    }

    private void Signin(String stEmail, String stPassw) {
        FirebaseDatabase auth=FirebaseDatabase.getInstance();
        auth.signInWithEmailAndPassword(stEmail,stPassw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Intent i=new Intent(SigninActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(SigninActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    etEmail.setError(task.getException().getMessage());
                }
            }
        });

    }

}


















