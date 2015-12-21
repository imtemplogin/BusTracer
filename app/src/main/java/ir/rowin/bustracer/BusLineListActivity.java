package ir.rowin.bustracer;


import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BusLineListActivity extends AppCompatActivity implements Runnable {

    private ProgressDialog pDialog;
    private ListView lv_buslinelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("لیست خطوط");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.buslinelist_title_layout);
        setContentView(R.layout.activity_bus_line_list);


        lv_buslinelist = (ListView) findViewById(R.id.lv_linelist);
        lv_buslinelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        pDialog = new ProgressDialog(BusLineListActivity.this);
        pDialog.setTitle("در حال دریافت اطلاعات ...");
        pDialog.setMessage("لطفا صبر کنید");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        Thread thread = new Thread(BusLineListActivity.this);
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

                    LineInfoItem lines[] = new LineInfoItem[1];
                    String strStartTerminal ="ترمینال شروع : " + "قدس";
                    String strEndTerminal ="ترمینال پایان : " + "طیب";
                    String strLineLbl ="خط " + "اصفهان";
                    String strStInLines = "تعداد ایستگاه :" + "";
                    String strLinePriceWithoutCard = "کرایه با کارت :" + "" + "تومان";
                    String strLinePriceWithCard = "کرایه بدون کارت :"+ "" + "تومان";
                    LineInfoItem t = new LineInfoItem(
                            1, 52,
                            1000,
                            550, 52, 11, 0,
                            strStartTerminal,
                            strEndTerminal,
                            12
                    );
                    lines[0] = t;


                    LinesAdapter adapter = new LinesAdapter(BusLineListActivity.this,
                            R.layout.buslines_item_layout, lines);
                    lv_buslinelist.setAdapter(adapter);
                }
            }, 500);
        }
    };
}



