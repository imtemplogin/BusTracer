package ir.rowin.bustracer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Wineo on 12/14/2015.
 */
public class LinesAdapter extends ArrayAdapter<LineInfoItem> {

    Context context;
    int layoutResourceId;
    LineInfoItem lines[] = null;

    public LinesAdapter(Context context, int resource, LineInfoItem[] objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.lines = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LineItemHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new LineItemHolder();
            holder.tv_lineNumber = (TextView) row.findViewById(R.id.tv_linenumber);
            holder.tv_stationsInLine = (TextView) row.findViewById(R.id.tv_stationsInLine);
            holder.tv_linePriceWithCard = (TextView) row.findViewById(R.id.tv_withCardPrice);
            holder.tv_linePriceWithoutCard = (TextView) row.findViewById(R.id.tv_withoutCardPrice);
            holder.tv_startTerminal = (TextView) row.findViewById(R.id.tv_startTerminal);
            holder.tv_endTerminal = (TextView) row.findViewById(R.id.tv_endTerminal);
            holder.tv_busesBetweenTime = (TextView) row.findViewById(R.id.tv_busesBetweenTime);
            holder.btn_lineMoreInfo = (Button)row.findViewById(R.id.btn_lineMoreInfo);

            row.setTag(holder);
        } else {
            holder = (LineItemHolder) row.getTag();
        }

        LineInfoItem lineItem = lines[position];
        holder.tv_lineNumber.setText("خط " + lineItem._lineNumber);
        holder.tv_stationsInLine.setText("تعداد ایستگاه : " + lineItem._allStaionInLine);
        holder.tv_linePriceWithCard.setText("کرایه با کارت : " + lineItem._linePriceWithCard + " تومان");
        holder.tv_linePriceWithoutCard.setText("کرایه بدون کارت : " + lineItem._linePriceWithoutCard + " تومان");
        holder.tv_startTerminal.setText(lineItem._startTerminal);
        holder.tv_endTerminal.setText(lineItem._endTerminal);
        holder.tv_busesBetweenTime.setText("فاصله مابین اتوبوس ها : "    + lineItem._busesBetweenTime + " دقیقه");
        holder.btn_lineMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(context, StationListOfLineActivity.class);
                context.startActivity(mainIntent);
            }
        });

        if (position % 2 == 1) {
            row.setBackgroundColor(Color.parseColor("#efefef"));
        } else {
            row.setBackgroundColor(Color.WHITE);
        }

        return row;
    }
}

class LineItemHolder {
    TextView tv_lineNumber;
    TextView tv_stationsInLine;
    TextView tv_linePriceWithCard;
    TextView tv_linePriceWithoutCard;
    TextView tv_startTerminal;
    TextView tv_endTerminal;
    TextView tv_busesBetweenTime;
    Button btn_lineMoreInfo;
}
