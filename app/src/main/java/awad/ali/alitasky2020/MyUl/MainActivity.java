package awad.ali.alitasky2020.MyUl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import awad.ali.alitasky2020.R;

import static awad.ali.alitasky2020.R.*;

public class MainActivity extends AppCompatActivity {
    private ImageButton ibtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        ibtnAdd = findViewById(R.id.ibtnAdd);

        ibtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, activity_add_task.class);
                startActivity(i);
            }
        });
    }
}