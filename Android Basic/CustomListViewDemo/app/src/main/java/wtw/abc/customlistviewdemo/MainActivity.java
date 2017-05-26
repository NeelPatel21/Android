package wtw.abc.customlistviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MainAdapter mainAdapter;
    String[] name = {"Android", "Java", "Php", "IOS"};
    int[] image = {R.drawable.blood, R.drawable.changepass, R.drawable.gmail, R.drawable.kidslogo};
    ArrayList<MainList> mainLists;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.main_lv);
        mainLists = new ArrayList<>();
        for(int i=0;i<name.length;i++){
            MainList list = new MainList();
            list.setName(name[i]);
            list.setImage(image[i]);
            mainLists.add(list);
        }
        mainAdapter = new MainAdapter(MainActivity.this,mainLists);
        listView.setAdapter(mainAdapter);
    }
}
