package com.example.manan.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manan.inventoryapp.data.InventoryContract.InventoryEntry;

import java.io.FileNotFoundException;


/**
 * Created by Manan on 06-03-2017.
 */

public class InventoryCursorAdapter extends CursorAdapter {
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {

        TextView product_name = (TextView) view.findViewById(R.id.item_name);
        TextView product_price = (TextView) view.findViewById(R.id.item_price);
        final TextView product_quantity = (TextView) view.findViewById(R.id.item_quantity);
        ImageView pr_image = (ImageView) view.findViewById(R.id.item_image);
        Button SaleButton = (Button) view.findViewById(R.id.sale);

        String p_name = cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME));
        final int p_price = cursor.getInt(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_PRICE));
        final int p_qauntity = cursor.getInt(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_QUANTITY));
        final int productId = cursor.getInt(cursor.getColumnIndexOrThrow(InventoryEntry._ID));


        String imageUriString = cursor.getString(cursor.getColumnIndexOrThrow(InventoryEntry.COLUMN_PRODUCT_IMAGE));

        if (imageUriString.startsWith("content://com.android.providers.media.documents/document/image")) {
            pr_image.setImageURI(Uri.parse(imageUriString));
        }
        else {
            pr_image.setImageResource(R.drawable.add_image);
        }

        SaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = p_qauntity;
                if (quantity <= 0) {
                    Toast.makeText(context, "Buy products first", Toast.LENGTH_SHORT).show();
                } else {
                    quantity = p_qauntity - 1;
                    ContentValues values = new ContentValues();
                    values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, quantity);
                    Uri currentUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, productId);
                    context.getContentResolver().update(currentUri, values, null, null);
                }
            }
        });

        product_name.setText("Product : " + p_name);
        product_price.setText("Price : " + p_price);
        product_quantity.setText("Quantity : " + p_qauntity);
    }
}
