package college.com.workshop;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static college.com.workshop.DataBaseHelper.col_id;

public class DataBase extends AppCompatActivity {

    @InjectView(R.id.text_id)
    EditText tId;

    @InjectView(R.id.text_name)
    EditText tName;

    @InjectView(R.id.text_enroll)
    EditText tEnroll;

    @InjectView(R.id.spin_field)
    Spinner sfield;

    @InjectView(R.id.insert)
    Button b_insert;

    @InjectView(R.id.update)
    Button b_update;

    @InjectView(R.id.get)
    Button b_get;

    @InjectView(R.id.get_all)
    Button b_getAll;

    @InjectView(R.id.list)
    ListView vlist;

    ArrayAdapter<String> adapter;

    DataBaseHelper db= new DataBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        ButterKnife.inject(this);

        List<String> fields = new ArrayList<>();
        fields.add("C.E.");
        fields.add("E.E.");
        fields.add("I.T.");
        fields.add("M.E.");
        fields.add("Auto");
        fields.add("E.C.");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,fields);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sfield.setAdapter(adapter);
        sfield.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(DataBase.this,"item "+i,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.insert)
    void insert(){
        Person p = new Person();
        p.setId(Integer.parseInt(tId.getText().toString()));
        p.setName(tName.getText().toString());
        p.setEnroll(tEnroll.getText().toString());
        p.setField(sfield.getSelectedItem().toString());

        if(db.insert(p)>=0)
            Toast.makeText(this,"done",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.update)
    void update(){
        Person p = new Person();
        p.setId(Integer.parseInt(tId.getText().toString()));
        p.setName(tName.getText().toString());
        p.setEnroll(tEnroll.getText().toString());
        p.setField(sfield.getSelectedItem().toString());
        if(db.update(p)>=0)
            Toast.makeText(this,"done",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.get)
    void get(){
        int id = Integer.parseInt(tId.getText().toString());
        Person p = db.getPerson(id);

        if(p!=null){
            tName.setText(p.getName());
            tId.setText(p.getId());
            tEnroll.setText(p.getEnroll());
            sfield.setSelection(adapter.getPosition(p.getField()));
            Toast.makeText(this,"done",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
            tName.setText("");
        }
    }

    @OnClick(R.id.get_all)
    void getAll(){
        Cursor c = db.getAll();
        String ids="";
        while(c.moveToNext()){
            ids+=c.getInt(c.getColumnIndex(col_id))+",";
        }
        Toast.makeText(this,"ids :- "+ids,Toast.LENGTH_SHORT).show();
    }
}
