package ir.rowin.bustracer;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CommentActivity extends AppCompatActivity implements Runnable {

    private Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        btn_send=(Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(CommentActivity.this);
                pDialog.setTitle("در حال دریافت اطلاعات ...");
                pDialog.setMessage("لطفا صبر کنید");
                pDialog.setCanceledOnTouchOutside(false);
                pDialog.show();
                Thread thread = new Thread(CommentActivity.this);
                thread.start();
            }
        });
    }

    private ProgressDialog pDialog;

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
                }
            }, 2000);
        }
    };
}
