package ghost.engedu.google.com.ghostactivity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.util.Random;


public class GhostActivity extends ActionBarActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    Button add,ai,reset;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            dictionary = new SimpleDictionary(new BufferedInputStream(getAssets().open("words.txt")));
        }catch(Exception ex){}
        setContentView(R.layout.activity_ghost);
       // onStart(null);
        userTurn = random.nextBoolean();
        reset=(Button)findViewById(R.id.reset);
        add=(Button)findViewById(R.id.add);
        ai=(Button)findViewById(R.id.ai);
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et=(EditText)findViewById(R.id.editT);
                String s=new String();
                s = et.getText().toString();
                if(s.length()>0){
                    TextView text = (TextView) findViewById(R.id.ghostText);
                    s=text.getText().toString() + s.charAt(0);
                    et.setText(null);
                    text.setText(s);
                    userTurn=false;
                    add.setEnabled(false);
                    ai.setEnabled(true);

                }
            }
        });
        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et=(EditText)findViewById(R.id.editT);
                TextView text = (TextView) findViewById(R.id.ghostText);
                String s=new String();
                s = et.getText().toString();
                    char c= (char) (random.nextInt(25)+65);
                    s=text.getText().toString() +c;
                    text.setText(s);
                    userTurn=false;
                    ai.setEnabled(false);
                    add.setEnabled(true);
            }
        });
        if (userTurn) {
            label.setText(USER_TURN);
            ai.setEnabled(false);
            add.setEnabled(true);
        } else {
            label.setText(COMPUTER_TURN);
            add.setEnabled(false);
            ai.setEnabled(true);
            computerTurn();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);

        userTurn = true;
        label.setText(USER_TURN);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {

        return true;
    }
}
