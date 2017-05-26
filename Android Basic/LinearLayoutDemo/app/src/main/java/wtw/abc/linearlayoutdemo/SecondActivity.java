package wtw.abc.linearlayoutdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView textView;
    GridView lv;
    ArrayAdapter adapter;
    String[] technology = {"Android","Php","Java","IOS","Android","Php","Java","IOS","Android","Php","Java","IOS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = (TextView) findViewById(R.id.second_email);
        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString("Email","");
        textView.setText(s);
        lv = (GridView) findViewById(R.id.second_lv);
        adapter  =new ArrayAdapter(SecondActivity.this,android.R.layout.simple_list_item_1,technology);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String string =  parent.getItemAtPosition(position)+"";
                Toast.makeText(SecondActivity.this,string,Toast.LENGTH_LONG).show();
            }
        });
    }
}
