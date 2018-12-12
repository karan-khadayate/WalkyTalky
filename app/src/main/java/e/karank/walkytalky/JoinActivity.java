package e.karank.walkytalky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class JoinActivity extends AppCompatActivity {
    Socket abc;
    DataOutputStream dos;
    DataInputStream dis;
    TextView jtextView;
    EditText jeditText;
    JDisplayConversation dc;
    String conversation,msgo,msgi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        jeditText=(EditText)findViewById(R.id.jeditText);
        jtextView=(TextView)findViewById(R.id.jtextView);
        jtextView.setMovementMethod(new ScrollingMovementMethod());
        dc=new JDisplayConversation();
        conversation="\n ";
        new JThread().start();
    }

    public void sendj(View v){
        if(abc!=null) {
            new JSendThread(jeditText.getText().toString()).start();
            jeditText.setText("");
        }
        else{
            Toast.makeText(this,"ERROR in connection.\nPlease read the FAQ.",Toast.LENGTH_SHORT).show();
        }
    }
    class JSendThread extends Thread{
        String msgo;
        public JSendThread(String msgo){
            this.msgo=msgo;
        }
        public void run(){
                try {
                    dos.writeUTF(msgo);
                    dos.flush();
                    conversation = conversation + "SENT: " + msgo+"\n ";
                    runOnUiThread(dc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
    class JRecieveThread extends Thread{
        public void run(){
            while(true) {
                try {
                    msgi=dis.readUTF();
                    conversation=conversation+"RECIEVED: "+msgi+"\n ";
                    runOnUiThread(dc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class JThread extends Thread{
        public void run(){
            while(abc==null) {
                try {
                    abc = new Socket("192.168.43.1", 2018);
                    dos = new DataOutputStream(abc.getOutputStream());
                    dis = new DataInputStream(abc.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            new JRecieveThread().start();
        }
    }
    class JDisplayConversation implements Runnable{
        public void run(){
            jtextView.setText(conversation);
        }
    }
}
