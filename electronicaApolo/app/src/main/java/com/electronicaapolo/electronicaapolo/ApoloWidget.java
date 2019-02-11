package com.electronicaapolo.electronicaapolo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class ApoloWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        CharSequence widgetText = "Matriz";
        CharSequence widgetText2="Sucursal Centro";

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.apolo_widget);
        views.setTextViewText(R.id.textViewMatriz, widgetText.toString());
        views.setTextViewText(R.id.textViewSucursal,widgetText2.toString());

        //direccion para abrir
        Intent intentMatriz = new Intent(Intent.ACTION_VIEW, Uri.parse("http://electronicaapolo.com/matriz/"));
        PendingIntent pendingIntentMatriz=PendingIntent.getActivity(context,0,intentMatriz,0);

        Intent intentSucursal = new Intent(Intent.ACTION_VIEW, Uri.parse("http://electronicaapolo.com/sucursal/"));
        PendingIntent pendingIntentSucursal=PendingIntent.getActivity(context,0,intentSucursal,0);

        views.setOnClickPendingIntent(R.id.textViewMatriz,pendingIntentMatriz);
        views.setOnClickPendingIntent(R.id.textViewSucursal,pendingIntentSucursal);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

