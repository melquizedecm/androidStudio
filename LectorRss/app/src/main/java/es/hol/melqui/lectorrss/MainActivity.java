package es.hol.melqui.lectorrss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RssDescargar.getRSSfromURL("https://sports.yahoo.com/nba/rss.xml");

    }
}
