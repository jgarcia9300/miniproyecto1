package com.univalle.miniproyecto1.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.univalle.miniproyecto1.R

/**
 * Implementation of App Widget functionality.
 */
class InventoryWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, R.layout.inventory_widget)

    // Actualizamos los textos
    views.setTextViewText(R.id.widget_inventory_title, "Inventory")
    views.setTextViewText(R.id.widget_question_text, "¿Cuánto tengo de inventario?")
    views.setTextViewText(R.id.widget_inventory_balance, "$ ****")

    // Colocamos el logo
    views.setImageViewResource(R.id.widget_logo, R.drawable.inventory)

    // Ícono del ojo (por defecto abierto)
    views.setImageViewResource(R.id.widget_eye_icon, R.drawable.eye_image)

    // Enviar los cambios al widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}