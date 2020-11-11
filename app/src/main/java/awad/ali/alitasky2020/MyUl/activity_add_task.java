package awad.ali.alitasky2020.MyUl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import awad.ali.alitasky2020.Data.MyTask;
import awad.ali.alitasky2020.R;

    public class MainActivity extends AppCompatActivity {
        private EditText etTitle,etSupject;
        private SeekBar skbrlmportant,skbrNeccesary;
        private Button btnSaveTask;
        private ImageButton imgbtn;
        private Button btnUplaod;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_task);
            etTitle=findViewById(R.id.etTitle);
            etSupject=findViewById(R.id.etSupject);
            skbrlmportant=findViewById(R.id.skbrlmportant);
            skbrNeccesary=findViewById(R.id.skbrNeccesary);
            btnSaveTask=findViewById(R.id.btnSaveTask);
            imgbtn=findViewById(R.id.imgbtn);
            btnUplaod=findViewById(R.id.btnUplaod);

            btnSaveTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
        public void  validateForm()
        {
            String title=etTitle.getText().toString();
            String text=etSupject.getText().toString();
            int imp=skbrlmportant.getProgress();
            int nec=skbrNeccesary.getProgress();
            boolean isok=true;
            if (title .length()==0)
            {
                isok=false;
                etTitle.setError("at Least one char");
            }
            if(isok)
            {
                MyTask myTask=new MyTask();
                myTask.setTitle(title);
                myTask.setSub(text);
                myTask.setNec(nec);
                myTask.setSkr(imp);
                saveTask(myTask);
            }
        }
        private void saveTask(MyTask myTask) {
            //1
            FirebaseDatabase database=FirebaseDatabase.getInstance();
            //2
            DatabaseReference reference=database.getReference();
            //user id
            FirebaseAuth auth=FirebaseAuth.getInstance();
            String uid=auth.getCurrentUser().getUid();
            //my opject key
            String key=reference.child("AllTasks").push().getKey();
            myTask.setOwner(uid);
            myTask.setKey(key);
            //actual stroring
            reference.child("AllTasks").child(uid).child(key).setValue(myTask).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "add successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "add failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        task.getException().printStackTrace();
                    }
                }
            });





        }
    }
