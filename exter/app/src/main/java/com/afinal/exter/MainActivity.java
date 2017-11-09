package com.afinal.exter;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText eNumber, eMessage;
    Button bSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eNumber = (EditText) findViewById(R.id.num);
        eMessage = (EditText) findViewById(R.id.textView);
        bSend = (Button) findViewById(R.id.button);
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("Custom Notification");
        mBuilder.setSmallIcon(R.drawable.mail512);
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sendMessage(eNumber.getText().toString(),eMessage.getText().toString())){
                    Toast.makeText(MainActivity.this,"message send",Toast.LENGTH_LONG).show();
                    mBuilder.setContentText("Message send");
                }else{
                    Toast.makeText(MainActivity.this,"error!!!",Toast.LENGTH_LONG).show();
                    mBuilder.setContentText("error in send");
                }
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(5, mBuilder.build());
            }

        });
    }

    private boolean sendMessage(String num, String message){
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(num, null
                    ,message, null, null);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
