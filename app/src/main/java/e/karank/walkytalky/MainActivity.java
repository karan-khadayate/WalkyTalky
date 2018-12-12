package e.karank.walkytalky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      }
    public void create(View v){
        startActivity(new Intent(MainActivity.this,CreateActivity.class));
    }
    public void join(View v){
        startActivity(new Intent(MainActivity.this,JoinActivity.class));
    }
    public void help(View v){
        startActivity(new Intent(MainActivity.this,HelpActivity.class));
    }
}
