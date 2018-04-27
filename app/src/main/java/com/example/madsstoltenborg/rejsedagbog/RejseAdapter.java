package com.example.madsstoltenborg.rejsedagbog;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

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
        Cursor rejseCursor = storage.getRejse();





    }
}
