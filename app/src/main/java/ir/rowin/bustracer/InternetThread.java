package ir.rowin.bustracer;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.Time;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * Created by Hamid-Lab on 12/20/2015.
 */
public class InternetThread extends Thread {
    public PicInfo pi;
    public MainActivity act;

    @Override
    public void run() {
        super.run();
        try {
            Bitmap bmp;
            synchronized (G.myobj) {
                pi.reading = true;
            }
            bmp = readimage(pi.x, pi.y, pi.z);
            synchronized (G.myobj) {
                pi.bmp = bmp;
            }
            if (bmp == null) {
                bmp = readimagex2(pi.x, pi.y, pi.z);
                synchronized (G.myobj) {
                    pi.bmp = bmp;
                }
                if (!pi.internet) {
                    synchronized (G.myobj) {

                        pi.internet = true;
                        pi.reading = false;
                    }

                    bmp = GetImageFromInternet();
                    synchronized (G.myobj) {
                        pi.bmp = bmp;
                        pi.internet = false;
                    }

                }

            }
            synchronized (G.myobj) {
                pi.reading = false;
            }
            if (!G.refresh_pic) {
                G.refresh_pic = true;
                Thread.sleep(150);
                if (bmp != null) {
                    MainActivity.handler2.post(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                act.paint_pic();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }
                G.refresh_pic = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private Bitmap readimage(int x, int y, int z) {
        try {
            String selectQuery = "SELECT id FROM tadr WHERE x = " + x + " AND y = " + y + " AND z = " + z + " AND t = 1";
            Cursor cursor = G.db.rawQuery(selectQuery, null);
            if (cursor.getCount() == 1 && cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                selectQuery = "SELECT bin_img FROM img WHERE id = " + id + " LIMIT 0,1";
                cursor = G.db.rawQuery(selectQuery, null);
                if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                    byte[] buf = cursor.getBlob(cursor.getColumnIndex("bin_img"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(buf, 0, buf.length);
                    return bmp;
                }
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }

        return null;
    }

    private Bitmap readimagex2(int x, int y, int z) {
        try {
            int count = 2;
            boolean isIxist = false;
            while (!isIxist && count < 9) {
                int x1 = (int) (x / count);
                int x2 = x - x1 * count;
                int y1 = (int) (y / count);
                int y2 = y - y1 * count;
                z--;
                String selectQuery = "SELECT id FROM tadr WHERE x = " + x1 + " AND y = " + y1 + " AND z = " + z + " AND t = 1";
                Cursor cursor = G.db.rawQuery(selectQuery, null);
                if (cursor.getCount() == 1 && cursor.moveToFirst()) {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    selectQuery = "SELECT bin_img FROM img WHERE id = " + id + " LIMIT 0,1";
                    cursor = G.db.rawQuery(selectQuery, null);
                    if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                        byte[] buf = cursor.getBlob(cursor.getColumnIndex("bin_img"));
                        Bitmap bmp = BitmapFactory.decodeByteArray(buf, 0, buf.length);
                        isIxist = true;
                        Bitmap bmp2 = Bitmap.createBitmap(bmp, x2 * (bmp.getWidth() / count), y2 * (bmp.getHeight() / count), bmp.getWidth() / count,
                                bmp.getHeight() / count);
                        Bitmap bmp3 = Bitmap.createScaledBitmap(bmp2, bmp.getWidth(), bmp.getHeight(), true);
                        return bmp3;
                    }
                }
                count *= 2;
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }

        return null;
    }

    public Bitmap GetImageFromInternet() {
        Bitmap bmp = null;
        try {
            Log.v("internetset", "read " + pi.x + " ," + pi.y + " ," + pi.z);
            String site = "";
            Random rand1 = new Random();
            int n1 = rand1.nextInt(2);
            int n2 = rand1.nextInt(9999999) + 249000000;// rand1.nextInt(9999999)
            // + 249000000;
            int n3 = rand1.nextInt(8);
            if (n1 == 0)
                site = "http://mts0.google.com/vt/lyrs=m@";
            else
                site = "http://mts1.google.com/vt/lyrs=m@";
            if (n3 > 0)
                site += n2 + "&hl=en&src=app&x=" + pi.x + "&y=" + pi.y + "&z=" + pi.z + "&s=" + "Galileon".substring(0, n3);
            else
                site += n2 + "&hl=en&src=app&x=" + pi.x + "&y=" + pi.y + "&z=" + pi.z + "&s=";
            URL url = new URL(site);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.connect();
            InputStream input = con.getInputStream();

            bmp = BitmapFactory.decodeStream(input);
            if (bmp != null) {
                Log.i("internet", "read " + pi.x + " ," + pi.y + " ," + pi.z);
                insert_img(pi.x, pi.y, pi.z, bmp);
            }
            return bmp;

        } catch (MalformedURLException e) {

            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void insert_img(int x, int y, int z, Bitmap bmp) {
        try {
            synchronized (G.myinsert) {
                G.db.beginTransaction();
                ContentValues fdata = new ContentValues();
                fdata.put("x", x);
                fdata.put("y", y);
                fdata.put("z", z);
                fdata.put("t", 1);
                Time now = new Time();
                now.setToNow();
                fdata.put("d", "" + now);
                long id = G.db.insert("tadr", null, fdata);

                ByteArrayOutputStream s = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 0, s);
                byte[] buf = s.toByteArray();

                fdata = new ContentValues();
                fdata.put("id", id);
                fdata.put("bin_img", buf);
                G.db.insert("img", null, fdata);
                G.db.setTransactionSuccessful();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            G.db.endTransaction();
        }
    }


}
