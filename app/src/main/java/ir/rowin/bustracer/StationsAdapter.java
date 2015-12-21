package ir.rowin.bustracer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StationsAdapter extends ArrayAdapter<StationInfoItem> {

    Context context;
    int layoutResourceId;
    StationInfoItem stations[] = null;

    public StationsAdapter(Context context, int resource, StationInfoItem[] objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.stations = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        StationItemHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new StationItemHolder();
            /*holder.tv_lineNumber = (TextView) row.findViewById(R.id.tv_linenumber);
            holder.tv_stationsInLine = (TextView) row.findViewById(R.id.tv_stationsInLine);
            holder.tv_linePriceWithCard = (TextView) row.findViewById(R.id.tv_withCardPrice);
            holder.tv_linePriceWithoutCard = (TextView) row.findViewById(R.id.tv_withoutCardPrice);
            holder.tv_startTerminal = (TextView) row.findViewById(R.id.tv_startTerminal);
            holder.tv_endTerminal = (TextView) row.findViewById(R.id.tv_endTerminal);
            holder.tv_busesBetweenTime = (TextView) row.findViewById(R.id.tv_busesBetweenTime);
            holder.btn_lineMoreInfo = (Button) row.findViewById(R.id.btn_lineMoreInfo);*/

            row.setTag(holder);
        } else {
            holder = (StationItemHolder) row.getTag();
        }

        StationInfoItem stationItem = stations[position];
        /*holder.tv_lineNumber.setText("خط " + stationItem._lineNumber);
        holder.tv_stationsInLine.setText("تعداد ایستگاه : " + stationItem._allStaionInLine);
        holder.tv_linePriceWithCard.setText("کرایه با کارت : " + stationItem._linePriceWithCard + " تومان");
        holder.tv_linePriceWithoutCard.setText("کرایه بدون کارت : " + stationItem._linePriceWithoutCard + " تومان");
        holder.tv_startTerminal.setText(stationItem._startTerminal);
        holder.tv_endTerminal.setText(stationItem._endTerminal);
        holder.tv_busesBetweenTime.setText("فاصله مابین اتوبوس ها : " + stationItem._busesBetweenTime + " دقیقه");
        holder.btn_lineMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.valueOf("Mohammad"), Toast.LENGTH_SHORT).show();
            }
        });*/
        return row;
    }
}

class StationItemHolder {

}
