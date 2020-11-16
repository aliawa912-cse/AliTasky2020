package awad.ali.alitasky2020.Data;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class MyTaskAdapter extends ArrayAdapter<MyTask> {
    public MyTaskAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
