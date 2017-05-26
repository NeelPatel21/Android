package wtw.abc.customlistviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by WTW on 5/25/2017.
 */
public class MainAdapter extends BaseAdapter{
    Context context;
    ArrayList<MainList> mainLists;
    LayoutInflater inflater;

    public MainAdapter(MainActivity mainActivity, ArrayList<MainList> mainLists) {
        this.context = mainActivity;
        this.mainLists = mainLists;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mainLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mainLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.custom_main,null);
        ImageView iv = (ImageView) view.findViewById(R.id.custom_main_iv);
        TextView txt = (TextView) view.findViewById(R.id.custom_main_txt);
        txt.setText(mainLists.get(position).getName());
        iv.setImageResource(mainLists.get(position).getImage());
        return view;
    }
}
