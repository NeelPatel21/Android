package wtw.abc.spinnerandtab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter adapter,auto_adapter;
    String[] technology = {"Select Technology", "Android", "Java", "Php", "Ios"};
    String[] search = {"Android","Aa","AAA","B","BB","BBB","C","CC","CCC"};
    AutoCompleteTextView autoTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.main_sp);
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, technology);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                if (position == 0) {

                } else {
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        autoTxt = (AutoCompleteTextView) findViewById(R.id.main_autoTxt);
        auto_adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,search);
        autoTxt.setThreshold(1);
        autoTxt.setAdapter(auto_adapter);

    }
}
