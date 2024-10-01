package com.jeneric.eventappfrontend.ui.home;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.view.View;

public class HomeClickHandlers {

    Context context;

    public HomeClickHandlers(Context context) {
        this.context = context;
    }

    public void onCalFABClicked(View view) {
        long millis = System.currentTimeMillis();

        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");
        ContentUris.appendId(builder, millis);

        Intent intent = new Intent(Intent.ACTION_VIEW)
                .setData(builder.build());
        context.startActivity(intent);

    }

}
