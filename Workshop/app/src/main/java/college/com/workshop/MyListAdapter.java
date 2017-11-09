package college.com.workshop;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Neel Patel on 10/9/2017.
 */

public class MyListAdapter extends BaseAdapter{

    List<Person> list;

    MyListAdapter(List<Person> list){
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return -1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//         
        return view;
    }
}
