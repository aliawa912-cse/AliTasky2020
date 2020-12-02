package awad.ali.alitasky2020.MyUl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import awad.ali.alitasky2020.Data.MyTask;
import awad.ali.alitasky2020.Data.MyTaskAdapter;
import awad.ali.alitasky2020.R;

import static awad.ali.alitasky2020.R.*;

public class MainActivity extends AppCompatActivity {
    private ImageButton ibtnAdd;
//a.1 after bulding the array adabter
    ListView lstTasks;
    //a.2
    MyTaskAdapter taskAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        ibtnAdd = findViewById(id.ibtnAdd1);

        ibtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, activity_add_task.class);
                startActivity(i);
            }
        });
        //a.3
       lstTasks=findViewById(id.lstTasks);
        //a.4
        taskAdapter=new MyTaskAdapter(getBaseContext(), layout.item_task_view);
        //a.5
        lstTasks.setAdapter(taskAdapter);
        downloadFromFireBase();
    }
    public void downloadFromFireBase()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference();
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        reference.child("AllTasks").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                taskAdapter.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) ;
                MyTask task = child.getValue(MyTask.class);
                taskAdapter.add(task);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Doewnload Eroor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}