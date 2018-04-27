package com.example.madsstoltenborg.rejsedagbog;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Sonnich on 26/04/2018.
 */

public class RejseAdapter extends CursorAdapter {


    private Storage storage;
    private LayoutInflater cursorInflater;
    private View.OnClickListener listener;

    public RejseAdapter(Context context, Cursor cursor, int flags, View.OnClickListener listener){
        super(context, cursor, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listener = listener;
        storage = Storage.getInstance();

    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return cursorInflater.inflate(R.layout.rejse_row, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //Cursor rejseCursor = storage.getRejse();
        RejseCursorWrapper wrapper = (RejseCursorWrapper) cursor;
        Rejse rejse = wrapper.getRejse();

        TextView tvRejseNavn = (TextView) view.findViewById(R.id.rejseNavn);
        tvRejseNavn.setText(rejse.getRejseNavn());
        tvRejseNavn.setTag(rejse.getId());

        TextView tvRejseDato = (TextView) view.findViewById(R.id.rejseDato);
        Calendar start = rejse.getRejseStart();
        Calendar slut = rejse.getRejseSlut();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvRejseDato.setText(String.format(dateFormat.format(start.getTime()) + " - " + dateFormat.format(slut.getTime())));

        Button rejsekortBtn = (Button) view.findViewById(R.id.visRejseKortBtn);
        rejsekortBtn.setOnClickListener(listener);





    }
}
