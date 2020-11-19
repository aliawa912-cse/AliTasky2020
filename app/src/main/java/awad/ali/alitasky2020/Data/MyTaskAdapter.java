package awad.ali.alitasky2020.Data;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import awad.ali.alitasky2020.R;

public class MyTaskAdapter extends ArrayAdapter<MyTask> {
    public MyTaskAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    /**\
     * bulding the single item view
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        //3.1
        View v= LayoutInflater.from(getContext()).inflate(R.layout.item_task_view,parent,false);
        //3.2
        TextView tvTitle=v.findViewById(R.id.itmTvTitle);
        TextView tvImportant=v.findViewById(R.id.itmTvImportant);
        TextView tvNecessary=v.findViewById(R.id.itmTvNecessary);
        TextView tvSupject=v.findViewById(R.id.itmTvSubject);
        ImageButton btnDelete=v.findViewById(R.id.itmbtnDelete);
        ImageButton btnCall=v.findViewById(R.id.itmbtnCall);
        ImageButton btnEdit=v.findViewById(R.id.itmbtnEdit);

        //3.3 get the soutable task project
        MyTask task = getItem(position);
        //3.4 view the dat to
        tvTitle.setText(task.getTitle());
        tvSupject.setText(task.getSub());
        tvNecessary.setText(task.getNec());
        tvImportant.setText(task.getImportant);

        switch (task.getImportant)
        {
            case 5:tvImportant.setBackgroundColor(Color.RED);break;
            case 4:tvImportant.setBackgroundColor(Color.YELLOW);break;
            case 3:tvImportant.setBackgroundColor(Color.CYAN);break;
            case 2:tvImportant.setBackgroundColor(Color.MAGENTA);break;
            case 1:tvImportant.setBackgroundColor(Color.rgb(0,200,0));break;
        }
        switch (task.getNec())
        {
            case 5:tvNecessary.setBackgroundColor(Color.RED);break;
            case 4:tvNecessary.setBackgroundColor(Color.YELLOW);break;
            case 3:tvNecessary.setBackgroundColor(Color.CYAN);break;
            case 2:tvNecessary.setBackgroundColor(Color.MAGENTA);break;
            case 1:tvNecessary.setBackgroundColor(Color.rgb(0,200,0));break;
        }
        return super.getView(position,convertView,parent);
    }
}
