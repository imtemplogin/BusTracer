package ir.rowin.bustracer;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class StationListOfLineActivity extends AppCompatActivity implements Runnable {
    private ListView lv_stationsOfline;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.stationlist_title_layout);
        setContentView(R.layout.activity_station_list_of_line);

        lv_stationsOfline = (ListView) findViewById(R.id.lv_stations);
        pDialog = new ProgressDialog(StationListOfLineActivity.this);
        pDialog.setTitle("در حال دریافت اطلاعات ...");
        pDialog.setMessage("لطفا صبر کنید");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        Thread thread = new Thread(StationListOfLineActivity.this);
        thread.start();
    }

    @Override
    public void run() {
        handler.sendEmptyMessage(0);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            // handle the result here
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pDialog.dismiss();
                    StationInfoItem stations[] = new StationInfoItem[10];
                    for (int x = 0; x < 10; x++) {
                        stations[x] = new StationInfoItem();
                    }

                    StationsAdapter adapter = new StationsAdapter(StationListOfLineActivity.this,
                            R.layout.station_item_layout, stations);
                    lv_stationsOfline.setAdapter(adapter);
                }
            }, 500);
        }
    };
}
