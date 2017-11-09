package college.com.workshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AddActivity extends AppCompatActivity {

    @InjectView(R.id.text1)
    EditText text1;

    @InjectView(R.id.text2)
    EditText text2;

    @InjectView(R.id.add)
    Button b_add;

//    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        ButterKnife.inject(this);
        text1.setText("");
        text2.setText("");
        text1.setHint("number");
        text2.setHint("number");
        String x="x";
//        Stream.generate(()->x).count();
    }

    @OnClick(R.id.add)
    void event(View view){
        if(text1.getText().toString().isEmpty()||text2.getText().toString().isEmpty()){
            text1.setError("required");
            text2.setError("required");
            return;
        }
        try{
            int a=Integer.parseInt(text1.getText().toString());
            int b=Integer.parseInt(text2.getText().toString());
            Intent i=new Intent(getApplicationContext(),IntentTest.class);
            i.putExtra("message","sum="+(a+b));
            startActivity(i);
        }catch(Exception e){
            text1.setError("numbers only");
            text2.setError("numbers only");
            return;
        }
    }
}
