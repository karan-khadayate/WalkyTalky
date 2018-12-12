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
import java.net.ServerSocket;
import java.net.Socket;

public class CreateActivity extends AppCompatActivity {
    ServerSocket pqr;
    Socket xyz;
    DataOutputStream dos;
    DataInputStream dis;
    TextView ctextView;
    EditText ceditText;
    CDisplayConversation dc;
    String conversation,msgo,msgi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ceditText=(EditText)findViewById(R.id.ceditText);
        ctextView=(TextView)findViewById(R.id.ctextView);
        ctextView.setMovementMethod(new ScrollingMovementMethod());
        dc=new CDisplayConversation();
        conversation="\n ";
        new CThread().start();
    }
    public void sendc(View v){
        if(xyz!=null) {
            new CSendThread(ceditText.getText().toString()).start();
            ceditText.setText("");
        }
        else{
            Toast.makeText(this,"ERROR in connection.\nPlease read the FAQ.",Toast.LENGTH_SHORT).show();
        }
    }
    class CSendThread extends Thread{
        String msgo;
        public CSendThread(String msgo){
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
    class CRecieveThread extends Thread{
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
    class CThread extends Thread{
        public void run(){
            try{
                pqr=new ServerSocket(2018);
                while(true) {
                    xyz = pqr.accept();
                    conversation="\n ";
                    dos = new DataOutputStream(xyz.getOutputStream());
                    dis = new DataInputStream(xyz.getInputStream());
                    new CRecieveThread().start();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    class CDisplayConversation implements Runnable{
        public void run(){
            ctextView.setText(conversation);
        }
    }
}
