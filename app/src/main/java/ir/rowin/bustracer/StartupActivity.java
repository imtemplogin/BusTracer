package ir.rowin.bustracer;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        //Webservice.readUrl("http://91.240.61.118:8234/api/stationlist");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(StartupActivity.this, MainActivity.class);
                startActivity(mainIntent);
                StartupActivity.this.finish();
            }
        }, 1000);

    }
}
