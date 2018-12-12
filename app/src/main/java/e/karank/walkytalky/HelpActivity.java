package e.karank.walkytalky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        String helpmsg="\n*HELP*\n\n#1 (CREATE)\n Turn on your wifi hotspot before choosing this option. Ask your friend to connect to your hotspot before choosing the 'JOIN' option.\n\n"+
                "#2 (JOIN)\n Connect to your friend's hotspot before choosing this option.\n";
        TextView htv=(TextView)findViewById(R.id.htextView);
        htv.setText(helpmsg);
        String aboutmsg="\n*ABOUT*\n\nv 1.0\nDeveloped by Karan Khadayate.\n";
        TextView hatv=(TextView)findViewById(R.id.h2textView);
        hatv.setText(aboutmsg);
    }
}
