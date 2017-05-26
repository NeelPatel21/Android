package wtw.abc.customlistviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadioAndCheckActivity extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb,male,female;
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_and_check);
        rg = (RadioGroup) findViewById(R.id.rg);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        show = (Button) findViewById(R.id.show);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(male.isChecked()){
                    Toast.makeText(RadioAndCheckActivity.this, male.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RadioAndCheckActivity.this, female.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
