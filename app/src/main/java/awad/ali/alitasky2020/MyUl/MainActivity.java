package awad.ali.alitasky2020.MyUl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import awad.ali.alitasky2020.Data.MyTaskAdapter;
import awad.ali.alitasky2020.R;

import static awad.ali.alitasky2020.R.*;

public class MainActivity<stTasks> extends AppCompatActivity {
    private ImageButton ibtnAdd;
//a.1 after bulding the array adabter
    ImageButton  1stTasks;
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
        1stTasks=findViewById(id.1stTasks);
        //a.4
        taskAdapter=new MyTaskAdapter(getBaseContext(), layout.item_task_view);
        //a.5
        1stTasks.(taskAdapter);
    }
}