package wtw.abc.sqlitedatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText rollNo,name,mark;
    Button insert,show,delete;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = openOrCreateDatabase("Student",MODE_PRIVATE,null);
        String table = "create table if not exists record(roll varchar(10),name varchar(20),mark varchar(20))";
        db.execSQL(table);
        rollNo = (EditText) findViewById(R.id.rollNo);
        name = (EditText) findViewById(R.id.name);
        mark = (EditText) findViewById(R.id.mark);
        insert = (Button) findViewById(R.id.insert);
        show = (Button) findViewById(R.id.show);
        delete = (Button) findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleteQuery = "delete from record where roll ='"+rollNo.getText().toString()+"'";
                db.execSQL(deleteQuery);
                Toast.makeText(MainActivity.this, "Record Delete Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String show = "select * from record";
                Cursor cursor = db.rawQuery(show,null);
                if(cursor.moveToFirst()){
                    StringBuilder sb = new StringBuilder();
                    do {
                        sb.append("Roll No :"+cursor.getString(0)+"\t");
                        sb.append("Name :"+cursor.getString(1)+"\t");
                        sb.append("Mark :"+cursor.getString(2)+"\n");
                    }
                    while (cursor.moveToNext());
                    Toast.makeText(MainActivity.this, sb, Toast.LENGTH_SHORT).show();
                }

            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rollNo.getText().toString().equalsIgnoreCase("")){
                    rollNo.setError("Roll No Required");
                    Snackbar.make(v,"Roll No Required",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                else if(name.getText().toString().equalsIgnoreCase("")){
                    name.setError("name Required");
                    Snackbar.make(v,"Name Required",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                else if(mark.getText().toString().equalsIgnoreCase("")){
                    mark.setError("Mark Required");
                    Snackbar.make(v,"Mark Required",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                else{
                    String insertQuery = "insert into record values('"+rollNo.getText().toString()+"','"+name.getText().toString()+"','"+mark.getText().toString()+"')";
                    db.execSQL(insertQuery);
                    Toast.makeText(MainActivity.this, "Insert Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
