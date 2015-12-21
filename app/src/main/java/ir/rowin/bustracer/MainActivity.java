package ir.rowin.bustracer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Runnable {

    //Button btnName, btnName2, btnName3;

    int finalHeight, finalWidth;

    private ImageView imageView;
    //private TextView txt1, txt2, txt3, txt4;
    private float oldXvalue, oldYvalue;
    private Bitmap result;// , image;
    private Bitmap image;// , image;
    private Canvas canvas;
    float Canvas_w = 0;
    float Canvas_h = 0;
    public static Handler handler2 = new Handler();
    int start_x, start_y;
    float setpos_x = 0, setpos_y = 0;
    int x_image = 256;
    int y_image = 256;
    int w;
    int h;
    int numRow;
    int numCol;
    int RowxCol;
    int Z = 13;
    int x_start;
    int y_start;
    boolean startApp = true;
    boolean change = true;
    float pos_mouse_x;
    float pos_mouse_y;
    boolean twoFingerShift = false;
    boolean twoFinger = false;
    float firest;
    float scaleV = 1.5f;
    float scaleZ = 1;
    float oldscaleZ = 1;
    float scale = 1;
    boolean strat1 = false;
    String st1 = "";
    points_2_2 oldpos;
    float teta = 0;
    boolean changeZoom = false;
    boolean changeZoomcanvas = false;
    public Hashtable<String, Bitmap> pictemp = new Hashtable<String, Bitmap>();
    boolean signPress = false;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Begin(0, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        /*setSupportActionBar(toolbar);
        Webservice t1 = new Webservice();
        t1.site = "http://31.25.133.54:8888/Account/Login?ReturnUrl=%2F";
        t1.act = this;
        t1.start();*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "موقعیت یابی غیر فعال میباشد!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*btnName = (Button) findViewById(R.id.button);
        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                // dialog.setTitle("لطفا منتظر بمانید");
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.loading_proc_dialog);


                // set the custom dialog components - text, image and button
                *//*TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Android custom dialog example!");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(R.drawable.ic_menu_send);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });*//*

                dialog.show();
            }
        });*/

        /*btnName2 = (Button) findViewById(R.id.button2);
        btnName2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setTitle("در حال دریافت اطلاعات ...");
                pDialog.setMessage("لطفا صبر کنید");
                pDialog.setCanceledOnTouchOutside(false);
                pDialog.show();
                Thread thread = new Thread(MainActivity.this);
                thread.start();
            }
        });

        btnName3 = (Button) findViewById(R.id.button3);
        btnName3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Mohammad", Toast.LENGTH_LONG).show();
            }
        });*/
        //start_x = 1324;
        //start_y = 849;
        /*PointD pd = G.WorldToTilePos(32.6724783333333, 51.5343433333333,Z);
        start_x = pd.start_x;
        start_y = pd.start_y;
        setpos_x = pd.setpos_x - Canvas_w;
        setpos_y = pd.setpos_y - Canvas_h;
        Log.v("internetset", "WorldToTilePos 2" + Canvas_w + " ," + Canvas_h);
        while (setpos_x <= -x_image) {
            setpos_x += x_image;
            start_x++;
            change = true;
        }
        while (setpos_y <= -y_image) {
            setpos_y += y_image;
            start_y++;
            change = true;
        }*/

        imageView = (ImageView) findViewById(R.id.imageView3);
        initTask();
        image = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_station);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v1, MotionEvent me) {
                try {
                    if (me.getPointerCount() == 2) {
                        signPress = false;
                        //txt2.setText("x1:" + me.getX(0) + "  x2:" + me.getX(1) + "   y1:" + me.getY(0) + "  y2:" + me.getY(1));
                        if (me.getAction() == MotionEvent.ACTION_MOVE) {
                            if (twoFingerShift) {
                                scale = spacing(me) / (firest);

                                if (scale > 1.02f || scale < 0.98f) {
                                    // PointF p1 = degreexy(me.getX() /
                                    // (scaleV * scaleZ), me.getY() /
                                    // (scaleV * scaleZ));
                                    // PointF p2 = degreexy( scale *
                                    // oldpos.x1, scale * oldpos.y1);
                                    float xd = me.getX() / (scaleV * scaleZ) - scale * oldpos.x1;
                                    float yd = me.getY() / (scaleV * scaleZ) - scale * oldpos.y1;
                                    scaleZ *= scale;
                                    oldpos = new points_2_2();
                                    oldpos.x1 = me.getX(0) / (scaleV * scaleZ);
                                    oldpos.y1 = me.getY(0) / (scaleV * scaleZ);
                                    oldpos.x2 = me.getX(1) / (scaleV * scaleZ);
                                    oldpos.y2 = me.getY(1) / (scaleV * scaleZ);
                                    Begin(xd, yd);
                                } else {
                                    if (oldpos.x1 != me.getX(0) / (scaleV * scaleZ) || oldpos.y1 != me.getY(0) / (scaleV * scaleZ)) {
                                        float xd = me.getX() / (scaleV * scaleZ) - oldpos.x1;
                                        float yd = me.getY() / (scaleV * scaleZ) - oldpos.y1;
                                        setPos(xd, yd, true);
                                        oldpos = new points_2_2();
                                        oldpos.x1 = me.getX(0) / (scaleV * scaleZ);
                                        oldpos.y1 = me.getY(0) / (scaleV * scaleZ);
                                        oldpos.x2 = me.getX(1) / (scaleV * scaleZ);
                                        oldpos.y2 = me.getY(1) / (scaleV * scaleZ);
                                    }
                                }


                            } else {
                                oldpos = new points_2_2();
                                oldpos.x1 = me.getX(0) / (scaleV * scaleZ);
                                oldpos.y1 = me.getY(0) / (scaleV * scaleZ);
                                oldpos.x2 = me.getX(1) / (scaleV * scaleZ);
                                oldpos.y2 = me.getY(1) / (scaleV * scaleZ);
                                firest = spacing(me);
                            }
                            twoFingerShift = true;
                            twoFinger = true;
                        }else  if (me.getAction() == MotionEvent.ACTION_MOVE) {
                            twoFinger = false;
                            setPos(0,0,true);
                        }else{
                            twoFinger = true;
                        }
                        twoFinger = true;
                    } else if (me.getPointerCount() == 1) {
                        //txt2.setText("x1:" + me.getX(0) + "   y1:" + me.getY(0));

                        if (me.getAction() == MotionEvent.ACTION_DOWN) {
                            signPress = true;
                            oldXvalue = me.getX() / (scaleV * scaleZ);
                            oldYvalue = me.getY() / (scaleV * scaleZ);
                            changeZoom = false;
                        } else if (me.getAction() == MotionEvent.ACTION_MOVE) {
                            if (changeZoom) {
                                oldXvalue = me.getX() / (scaleV * scaleZ);
                                oldYvalue = me.getY() / (scaleV * scaleZ);
                                changeZoom = false;
                            } else {
                                changeZoom = false;
                                if (oldXvalue != me.getX() / (scaleV * scaleZ) || oldYvalue != me.getY() / (scaleV * scaleZ)) {
                                    boolean b1 = false;
                                    if(signPress)
                                        if ((oldXvalue > me.getX() / (scaleV * scaleZ) + 3) || (oldYvalue  > me.getY() / (scaleV * scaleZ) + 3)
                                                || (oldXvalue < me.getX() / (scaleV * scaleZ) - 3) || (oldYvalue  < me.getY() / (scaleV * scaleZ) - 3)
                                        ) {
                                            b1 = true;
                                            Log.v("internetset", "move " + oldXvalue + " , " + (me.getX() / (scaleV * scaleZ)) + " , " + oldYvalue + " , " + (me.getY() / (scaleV * scaleZ)));
                                            Log.v("internetset", "move "
                                                            + (oldXvalue > me.getX() / (scaleV * scaleZ) + 5) + " , "
                                                            + (oldYvalue  > me.getY() / (scaleV * scaleZ) + 5)
                                                            + " , " + (oldXvalue < me.getX() / (scaleV * scaleZ) - 5)
                                                            + " , " + (oldYvalue  < me.getY() / (scaleV * scaleZ) - 5)
                                            );
                                        }
                                        else {
                                            b1 = false;
                                        }
                                    else
                                        b1 = true;
                                    if(b1) {
                                        if (!twoFinger) {
                                            signPress = false;
                                            setPos(me.getX() / (scaleV * scaleZ) - oldXvalue, me.getY() / (scaleV * scaleZ) - oldYvalue, true);
                                        }
                                        oldXvalue = me.getX() / (scaleV * scaleZ);
                                        oldYvalue = me.getY() / (scaleV * scaleZ);
                                    }

                                }
                            }
                        }else if (me.getAction() == MotionEvent.ACTION_UP) {
                            if(signPress && Z > 14){
                                boolean b2 = true;
                                for(int i = G.stationList.size() - 1; i >= 0 && b2 ; i--) {
                                    Station st = G.stationList.get(i);
                                    /*Log.v("internetset", "press " + me.getX() + " " + me.getY()+ " ; "
                                                    + (st.x1 * (scaleV * scaleZ))+ " " +(st.x2 * (scaleV * scaleZ))+ " ; "
                                                    + ( st.y1 * (scaleV * scaleZ)) +  " " +(st.y2 * (scaleV * scaleZ))+ " ; "
                                                    + setpos_x + " " + setpos_y
                                    );*/
                                    if(me.getX() > st.x1 * (scaleV * scaleZ) && me.getX() < st.x2 * (scaleV * scaleZ)
                                            && me.getY() > st.y1 * (scaleV * scaleZ)  && me.getY() < st.y2 * (scaleV * scaleZ) )
                                    {
                                        final Dialog dialog = new Dialog(MainActivity.this);
                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                        // dialog.setTitle("لطفا منتظر بمانید");
                                        dialog.setCanceledOnTouchOutside(false);
                                        dialog.setContentView(R.layout.loading_proc_dialog);


                                        // set the custom dialog components - text, image and button
                                        TextView text = (TextView) dialog.findViewById(R.id.text);
                                        text.setText("ایستگاه " + st.name);
                                        //ImageView image = (ImageView) dialog.findViewById(R.id.image);
                                        //image.setImageResource(R.drawable.ic_menu_send);

                                        /*Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                                        // if button is clicked, close the custom dialog
                                        dialogButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                            }
                                        });*/

                                        dialog.show();
                                        b2 = false;
                                    }
                                }
                                if(!b2)
                                    Log.v("internetset", "station is press ");
                                else
                                    Log.v("internetset", "station is not press " + me.getX() + " , " + me.getY());
                            }
                            setPos(0,0,true);
                        }
                        if (twoFinger) {
                            zoom(false);
                        }
                        twoFingerShift = false;
                        twoFinger = false;

                    } else {
                        if (twoFinger) {
                            zoom(false);
                        }
                        changeZoom = false;
                        twoFingerShift = false;
                        twoFinger = false;
                        setPos(0,0,true);
                    }

                } catch (Exception ex) {

                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Snackbar.make(this.imageView, "تنطیمات غیر فعال میباشد!", Snackbar.LENGTH_LONG)
                    //.setAction("Action", null).show();
            Intent mainIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(mainIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_linelist) {
            Intent mainIntent = new Intent(MainActivity.this, BusLineListActivity.class);
            startActivity(mainIntent);
        } else if (id == R.id.nav_comment) {
            Intent mainIntent = new Intent(MainActivity.this, CommentActivity.class);
            startActivity(mainIntent);
        } else if (id == R.id.nav_manage) {
            Intent mainIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(mainIntent);
        } else if (id == R.id.nav_share) {

            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            //set the type
            shareIntent.setType("text/plain");
            //add a subject
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "نرم افزار موبایل اتوبوسرانی خمینی شهر");
            //build the body of the message to be shared
            String shareMessage = "نرم افزار موبایل اتوبوسرانی خمینی شهر";
            shareMessage += "\r\n";
            shareMessage += "http://rowin.ir/apps/bustracer";
            //add the message
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareMessage);

            try {
                //start the chooser for sharing
                startActivity(Intent.createChooser(shareIntent, "معرفی نرم افزار به دوستان"));
            } catch (android.content.ActivityNotFoundException ex) {
                // (handle error)
            }
        } else if (id == R.id.nav_aboutus) {
            Intent mainIntent = new Intent(MainActivity.this, AboutusActivity.class);
            startActivity(mainIntent);
        }
        //hello
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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



    private void initTask() {
        DisplayMetrics dm = new DisplayMetrics();
        Display display = getWindowManager().getDefaultDisplay();
        display.getMetrics(dm);
        // image = BitmapFactory.decodeResource(getResources(),
        // R.drawable.tile);

    }

    private void Begin(float x, float y) {
        //txt3.setText("" + setpos_x + "  :" + setpos_y);
        //txt4.setText("" + Z + "  :" + scaleZ);
        if (oldscaleZ != scaleZ)
            twoFingerShift = false;
        edit_canvas();
        try {
            setPos(x, y, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void edit_canvas() {
        oldscaleZ = scaleZ;
        result = Bitmap.createBitmap((int) (imageView.getWidth() / (scaleV * scaleZ)), (int) (imageView.getHeight() / (scaleV * scaleZ)),
                Bitmap.Config.ARGB_8888);
        Canvas_w = imageView.getWidth() / (scaleV * scaleZ);
        Canvas_h = imageView.getHeight() / (scaleV * scaleZ);
        if(startApp){
            startApp = false;
            start_x = 5270;
            start_y = 3310;
            setpos_x = -150 + Canvas_w /2;
            setpos_y = Canvas_h /2;
            while (setpos_x > 0) {
                setpos_x -= x_image;
                start_x--;
                change = true;
            }
            while (setpos_x <= -x_image) {
                setpos_x += x_image;
                start_x++;
                change = true;
            }
            while (setpos_y > 0) {
                setpos_y -= y_image;
                start_y--;
                change = true;
            }
            while (setpos_y <= -y_image) {
                setpos_y += y_image;
                start_y++;
                change = true;
            }
        }
        Log.v("internetset", "WorldToTilePos " + Canvas_w  + " ," + Canvas_h);
        canvas = new Canvas(result);
        if (teta != 0)
            canvas.rotate(teta, imageView.getWidth() / (scaleV * scaleZ * 2), imageView.getHeight() / (scaleV * scaleZ * 2));

    }

    private void setPos(float x, float y, boolean bmove) {
        if (bmove && teta != 0) {
            PointF p1 = degreexy(x, y);
            float x1 = p1.x;
            float y1 = p1.y;
            setpos_x += x1;
            setpos_y += y1;
        } else {
            setpos_x += x;
            setpos_y += y;
        }

        while (setpos_x > 0) {
            setpos_x -= x_image;
            start_x--;
            change = true;
        }
        while (setpos_x <= -x_image) {
            setpos_x += x_image;
            start_x++;
            change = true;
        }
        while (setpos_y > 0) {
            setpos_y -= y_image;
            start_y--;
            change = true;
        }
        while (setpos_y <= -y_image) {
            setpos_y += y_image;
            start_y++;
            change = true;
        }
        paint_pic();

    }

    boolean trueimage = true;

    public void paint_pic() {
        synchronized (G.myobj) {
            //txt1.setText("" + G.picInfo.size());
        }
        if (change) {
            //txt3.setText("" + setpos_x + "  :" + setpos_y);
            Bitmap bmp = GetImage(start_x, start_y, Z);

            if (bmp != null) {
                x_image = bmp.getWidth();
                y_image = bmp.getHeight();
            }
            w = canvas.getWidth();
            h = canvas.getHeight();
            numRow = w / x_image + 4;
            numCol = h / y_image + 4;
            if (!strat1) {
                strat1 = true;
                start_x -= numRow / 2;
                start_y -= numCol / 2;
            }
            RowxCol = numRow * numCol;
            change = false;
            synchronized (G.myobj) {

                ArrayList<PicInfo> picInfo = new ArrayList<PicInfo>();
                for (PicInfo pi : G.picInfo)
                    if (pi.x > start_x - 1 && pi.x < start_x + numRow && pi.y > start_y - 1 && pi.y < start_y + numCol && pi.z == Z)
                        picInfo.add(pi);
                G.picInfo = picInfo;

            }
        }

        //if (!changeZoomcanvas && !trueimage)
            canvas.drawColor(Color.rgb(233,229,220));
        int counti = -1;
        for (int i = start_x; i < start_x + numRow; i++) {
            int countj = -1;
            for (int j = start_y; j < start_y + numCol; j++) {
                if (trueimage) {
                    Bitmap bmp = GetImage(i, j, Z);
                    if (bmp != null)
                        canvas.drawBitmap(bmp, setpos_x + x_image * counti, setpos_y + y_image * countj, null);
                } else {
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawText("" + (counti + 2) + " , " + (countj + 2) + "\r\n" + i + " , " + j, setpos_x + x_image * counti, setpos_y
                            + y_image * countj, paint);
                }
                countj++;
            }
            counti++;
        }
        //image.
        Paint paint = new Paint();
        //VoiceInteractor.PickOptionRequest.Option op = new VoiceInteractor.PickOptionRequest.Option();

        if(!twoFinger && Z > 14) {
            for(int i = 0;i<G.stationList.size();i++) {
                Station st = (Station)G.stationList.get(i);
                if(st.z != Z){
                    st.z = Z;
                    Point p = G.WorldToTilePos(st.lat, st.lng, Z);
                    st.x = p.x;
                    st.y = p.y;
                }
                st.x1 = st.x - (start_x +1) * x_image + (int)setpos_x - (int) (image.getWidth() / (scaleV * scaleZ * 2));
                st.x2 = st.x1 + (int) (image.getWidth() / (scaleV * scaleZ));
                st.y1 = st.y - (start_y +1) * y_image + (int)setpos_y - (int) (image.getHeight() / (scaleV * scaleZ * 2));
                st.y2 = st.y1 + (int) (image.getHeight() / (scaleV * scaleZ));
                //Log.v("internetset", "station " + x1 + " ," + x2 + " ," + y1 + " ," + y2);
                Rect rectangle = new Rect(st.x1, st.y1, st.x2, st.y2);
                canvas.drawBitmap(image, null, rectangle, null);
            }
        }
        imageView.setImageBitmap(result);
    }

    private void zoom(boolean act) {
        if (scaleZ >= 2f) {
            scaleZ *= 0.5f;
            zoomin();
            zoom(true);
        } else if (scaleZ <= 1f) {
            scaleZ *= 2f;
            zoomout();
            zoom(true);
        } else {
            if (act) {
                changeZoom = true;
                edit_canvas();
                zoomApliy();

            }
        }
    }

    private void zoomApliy() {
        changeZoomcanvas = true;
        paint_pic();
        changeZoomcanvas = false;
    }

    private void zoomin() {

        float x2 = (x_image * start_x - setpos_x + x_image) * 2;
        float y2 = (y_image * start_y - setpos_y + y_image) * 2;
        start_x = (int) ((x2 - x_image) / x_image);
        start_y = (int) ((y2 - y_image) / y_image);
        setpos_x = (int) (-(x2 - x_image) + start_x * x_image);
        setpos_y = (int) (-(y2 - y_image) + start_y * y_image);

        Z++;
        //txt4.setText("" + Z + "  :" + scaleZ);
        change = true;
        synchronized (G.myobj) {
            G.picInfo.clear();
        }

    }

    private void zoomout() {

        float x2 = (x_image * start_x - setpos_x + x_image) / 2;
        float y2 = (y_image * start_y - setpos_y + y_image) / 2;
        start_x = (int) ((x2 - x_image) / x_image);
        start_y = (int) ((y2 - y_image) / y_image);
        setpos_x = (int) (-(x2 - x_image) + start_x * x_image);
        setpos_y = (int) (-(y2 - y_image) + start_y * y_image);

        Z--;
        //txt4.setText("" + Z + "  :" + scaleZ);
        change = true;
        synchronized (G.myobj) {
            G.picInfo.clear();
        }
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) / (scaleV * scaleZ) - event.getX(1) / (scaleV * scaleZ);
        float y = event.getY(0) / (scaleV * scaleZ) - event.getY(1) / (scaleV * scaleZ);
        return (float)Math.sqrt(x * x + y * y);
    }

    private PointF degreexy(float x, float y) {
        double degree = Math.toRadians(-teta);

        PointF p1 = new PointF();
        p1.x = (float) (x * Math.cos(degree) - y * Math.sin(degree));
        p1.y = (float) (x * Math.sin(degree) + y * Math.cos(degree));
        return p1;

    }

    public PicInfo Contain(int x, int y, int z) {

        for (PicInfo pi : G.picInfo) {
            if (pi.x == x && pi.y == y && pi.z == z)
                return pi;
        }
        return null;
    }

    public Bitmap GetImage(int x, int y, int z) {
        PicInfo PI = Contain(x, y, z);
        if (PI != null) {
            Bitmap bmp = PI.bmp;
            if (bmp != null) {
                return bmp;
            } else {
                if (!PI.internet) {
                    if (!PI.reading) {
                        PI.reading = true;
                        InternetThread t1 = new InternetThread();
                        t1.pi = PI;
                        t1.act = this;
                        t1.start();
                    }
                }
            }
        } else {
            PicInfo pi = new PicInfo();
            pi.x = x;
            pi.y = y;
            pi.z = z;
            pi.reading = true;
            pi.internet = false;
            synchronized (G.myobj) {
                G.picInfo.add(pi);
            }
            InternetThread t1 = new InternetThread();
            t1.pi = pi;
            t1.act = this;
            t1.start();
        }

        return null;
    }

}
