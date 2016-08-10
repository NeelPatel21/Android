package mydomain.ghostactivity;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Random;


public class GhostActivity extends ActionBarActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    Button add,ai,reset;
    TextView text,label;
    EditText et;
    private Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            dictionary = new SimpleDictionary(new BufferedInputStream(getAssets().open("words.txt")));
        }catch(Exception ex){System.exit(0);}
        setContentView(R.layout.activity_ghost);
        // onStart(null);

        reset=(Button)findViewById(R.id.reset);
        add=(Button)findViewById(R.id.add);
        ai=(Button)findViewById(R.id.ai);
        reset=(Button)findViewById(R.id.reset);
        text= (TextView) findViewById(R.id.ghostText);
        text.setText("");
        et=(EditText)findViewById(R.id.editT);
        label = (TextView) findViewById(R.id.gameStatus);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=new String();
                s = et.getText().toString();
                if(s.length()>0){
                    s=text.getText().toString() + s.toLowerCase().charAt(0);
                    et.setText(null);
                    if(dictionary.isWord(s)) {
                        text.setText(s);
                        win();
                    }else if(!dictionary.getAnyWordStartingWith(s).isEmpty()) {
                        text.setText(s);
                        computerTurn();
                    }
                }
            }
        });
        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s;
                s = text.getText().toString();
                ArrayList ar=dictionary.getPossibleChar(s);
                char c = (char) (ar.size()>1?ar.get(random.nextInt(ar.size()-1)):ar.get(0));
                s=text.getText().toString() +c;
                text.setText(s);
                if(dictionary.isWord(s))win();
                else userTurnf();
                //label.setText(String.valueOf(ar.size()));
            }
        });
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fset();
            }

        });
        fset();
    }
    public void fset(){
        userTurn = random.nextBoolean();
        text.setText("");
        if (userTurn) {
            userTurnf();
        } else {
            computerTurn();
        }
    }
    public void win(){
        String s;
        s=text.getText().toString();
        if(dictionary.isWord(s)){
            if(userTurn)
                label.setText("User Win");
            else
                label.setText("A.I. Win");
            add.setEnabled(false);
            ai.setEnabled(false);
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
    private void userTurnf() {
        userTurn = true;
        ai.setEnabled(!userTurn);
        add.setEnabled(userTurn);
        et.setEnabled(userTurn);
        TextView label = (TextView) findViewById(R.id.gameStatus);
        label.setText(USER_TURN);
    }
    private void computerTurn() {
        userTurn = false;
        add.setEnabled(userTurn);
        ai.setEnabled(!userTurn);
        et.setEnabled(userTurn);
        TextView label = (TextView) findViewById(R.id.gameStatus);
        label.setText(COMPUTER_TURN);
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

