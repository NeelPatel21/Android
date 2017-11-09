package college.com.workshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class IntentTest extends AppCompatActivity {

    @InjectView(R.id.email)
    Button email;

    @InjectView(R.id.spin)
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.inject(this);

        Toast.makeText(this,getIntent().getExtras().getCharSequence("message","no message"),Toast.LENGTH_LONG).show();
        ArrayAdapter ar= new ArrayAdapter(this,android.R.layout.simple_spinner_item,new String[]{"saca","Sca","SCacacac","AScacc"});
//        ar.insert("sasc",0);
//        ar.insert("sascscac",1);
//        ar.insert("cascca",2);
//        ar.insert("uykyky",3);
        ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ar);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(IntentTest.this,"item "+i,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.email)
    public void sendMail() {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.vogella.com"));
        i = Intent.createChooser(i, "select something");
        startActivity(i);
    }

}
