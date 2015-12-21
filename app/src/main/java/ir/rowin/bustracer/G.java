package ir.rowin.bustracer;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Hamid-Lab on 12/20/2015.
 */
class PicInfo {
    public int x;
    public int y;
    public int z;
    public Bitmap bmp;
    public boolean reading;
    public boolean internet;
}

class points_2_2 {
    float x1;
    float y1;
    float x2;
    float y2;
}

class PointLatLon
{
    public double Lon;
    public double Lat;
    public long Time;
    public int Accuracy;
    public int Altitude;
}

class PointD
{
    public int start_x;
    public int start_y;
    public float setpos_x;
    public float setpos_y;
}

class Station{
    public String name;
    public double lat;
    public double lng;
    public int x;
    public int y;
    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public int z;
}


public class G extends Application {
    public static SQLiteDatabase db;
    public static Object myobj = new Object();
    public static Object myinsert = new Object();
    public static ArrayList<PicInfo> picInfo = new ArrayList<PicInfo>();
    public static ArrayList<Station> stationList = new ArrayList<Station>();
    public static boolean refresh_pic = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Station s1 = new Station();
        s1.lat = 32.691573;
        s1.lng = 51.536024999999995;
        s1.name = "درسیبه";
        stationList.add(s1);

        s1 = new Station();
        s1.lat = 32.701919680720742;
        s1.lng = 51.525340571758306;
        s1.name = "ایران خودرو";
        stationList.add(s1);

        s1 = new Station();
        s1.lat = 32.701804570624518;
        s1.lng = 51.525431766864813;
        s1.name = "ایران خودرو";
        stationList.add(s1);

        s1 = new Station();
        s1.lat = 32.700678056727085;
        s1.lng = 51.528481644180147;
        s1.name = "سه راه معلم";
        stationList.add(s1);

        s1 = new Station();
        s1.lat = 32.700641943269254;
        s1.lng = 51.528419953372804;
        s1.name = "سه راه معلم";
        stationList.add(s1);

        s1 = new Station();
        s1.lat = 32.697335522422769;
        s1.lng = 51.517399935195954;
        s1.name = "دخانیات";
        stationList.add(s1);

        s1 = new Station();
        s1.lat = 32.696857000000016;
        s1.lng = 51.515930084655793;
        s1.name = "دخانیات";
        stationList.add(s1);

        s1 = new Station();
        s1.lat = 32.695007334707711;
        s1.lng = 51.513000101852413;
        s1.name = "میدان نماز";
        stationList.add(s1);

        s1 = new Station();
        s1.lat = 32.695041028933311;
        s1.lng = 51.509641618385331;
        s1.name = "میدان نماز";

        /*s1 = new Station();
        s1.lat = 32.691573;
        s1.lng = 51.536024999999995;
        s1.name = "درسیبه";
        stationList.add(s1);

        s1 = new Station();
        s1.lat = 32.691573;
        s1.lng = 51.536024999999995;
        s1.name = "درسیبه";
        stationList.add(s1);

        s1 = new Station();
        s1.lat = 32.691573;
        s1.lng = 51.536024999999995;
        s1.name = "درسیبه";*/

        stationList.add(s1);
        String sdc = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        sdc += "/bus_data/";
        File fp = new File(sdc);
        if (!fp.exists()) {
            fp.mkdir();
        }
        db = SQLiteDatabase.openOrCreateDatabase(sdc + "busdb.sqlite", null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tadr (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE ,"
                + " x INTEGER NOT NULL ,"
                + " y INTEGER NOT NULL ,"
                + " z INTEGER NOT NULL ,"
                + " t INTEGER NOT NULL ,"
                + " d DATETIME NOT NULL )");

        db.execSQL("CREATE TABLE IF NOT EXISTS img (id INTEGER NOT NULL  UNIQUE , bin_img BLOB NOT NULL )");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        db.close();
    }


    static public PointLatLon TileToWorldPos(double tile_x, double tile_y, int zoom)
    {
        PointLatLon p = new PointLatLon();
        double n = Math.PI - ((2.0 * Math.PI * tile_y) / Math.pow(2.0, zoom));

        p.Lon = (tile_x / Math.pow(2.0, zoom) * 360.0) - 180.0;
        p.Lat = (180.0 / Math.PI * Math.atan(Math.sinh(n)));

        return p;
    }

    static public Point WorldToTilePos(double lat, double lon, int zoom)
    {
        Point p = new Point();
        double X = ((lon + 180.0) / 360.0 * (1 << zoom));
        double Y = ((1.0 - Math.log(Math.tan(lat * Math.PI / 180.0) +
                1.0 / Math.cos(lat * Math.PI / 180.0)) / Math.PI) / 2.0 * (1 << zoom));
        //Log.v("internetset", "WorldToTilePos " + X + " ," + Y );
        p.x = (int)(X * 256);
        p.y = (int)(Y * 256);
        return p;
    }

}
