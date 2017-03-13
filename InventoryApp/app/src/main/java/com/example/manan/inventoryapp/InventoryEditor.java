package com.example.manan.inventoryapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.manan.inventoryapp.data.InventoryContract;
import com.example.manan.inventoryapp.data.InventoryContract.InventoryEntry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class InventoryEditor extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    static final int TAKE_IMAGE = 10;
    Uri imageUri;
    Uri uri;
    private EditText mPrName;
    private EditText mPrPrice;
    private EditText mPrQuantity;
    private EditText mSuppName;
    private EditText mSuppId;
    private Uri currentProductUri;
    private ImageView mPrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_editor);

        mPrName = (EditText) findViewById(R.id.edit_item_name);
        mPrPrice = (EditText) findViewById(R.id.edit_item_price);
        mPrQuantity = (EditText) findViewById(R.id.edit_item_quantity);
        mSuppName = (EditText) findViewById(R.id.edit_supplier_name);
        mSuppId = (EditText) findViewById(R.id.edit_supplier_id);
        mPrImage = (ImageView) findViewById(R.id.product_image);

        final Intent intent = getIntent();
        currentProductUri = intent.getData();

        if (currentProductUri == null) {
            setTitle("Add a Product");
        } else {
            setTitle("Edit a product");
            getLoaderManager().initLoader(0, null, this);
        }

        Button addButton = (Button) findViewById(R.id.increase_item_quantity);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity = Integer.parseInt(mPrQuantity.getText().toString());
                quantity += 1;
                mPrQuantity.setText(String.valueOf(quantity));
            }
        });
        Button minusButton = (Button) findViewById(R.id.decrease_item_quantity);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(mPrQuantity.getText().toString());
                if (quantity <= 0) {
                    Toast.makeText(InventoryEditor.this, "Quantity cannot be less than 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                quantity -= 1;
                mPrQuantity.setText(String.valueOf(quantity));
            }
        });
        mPrImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, TAKE_IMAGE);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    private void saveProduct() {
        String pr_name = mPrName.getText().toString().trim();
        String pr_price = mPrPrice.getText().toString().trim();
        String pr_quantity = mPrQuantity.getText().toString().trim();
        String sup_id = mSuppId.getText().toString().trim();
        String sup_name = mSuppName.getText().toString().trim();
        String imageUriString = "";
        if (imageUri != null) {
            imageUriString = imageUri.toString();
        }

        if (currentProductUri == null &&
                TextUtils.isEmpty(pr_name) && TextUtils.isEmpty(pr_price) &&
                TextUtils.isEmpty(pr_quantity)) {
            return;
        }

        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, pr_name);
        values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, pr_quantity);
        values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, pr_price);
        values.put(InventoryEntry.COLUMN_SUPPLIER_ID, sup_id);
        values.put(InventoryEntry.COLUMN_SUPPLIER_NAME, sup_name);
        values.put(InventoryEntry.COLUMN_PRODUCT_IMAGE, imageUriString);
        if (currentProductUri == null) {
            Uri insert = getContentResolver().insert(InventoryEntry.CONTENT_URI, values);
            if (insert != null) {
                Toast.makeText(this, "New Product saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error with saving product", Toast.LENGTH_SHORT).show();
            }
        } else {
            int insert = getContentResolver().update(currentProductUri, values, null, null);
            if (insert != -1) {
                Toast.makeText(this, "Product Updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error with updating product", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            mPrImage.setImageURI(Uri.parse(String.valueOf(imageUri)));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (currentProductUri == null) {
            MenuItem menuItem = menu.findItem(R.id.order);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                if (checkFields()) {
                    saveProduct();
                    finish();
                }
                return true;
            case R.id.order:
                mPrName = (EditText) findViewById(R.id.edit_item_name);
                mPrQuantity = (EditText) findViewById(R.id.edit_item_quantity);
                mSuppId = (EditText) findViewById(R.id.edit_supplier_id);
                String productName = mPrName.getText().toString();
                String productQuan = mPrQuantity.getText().toString();
                String supp_id = mSuppId.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + supp_id));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Order for the following product");
                intent.putExtra(Intent.EXTRA_TEXT, "Product :" + productName + "\n" + " Quantity :" + productQuan);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return true;

            case R.id.action_delete_entry:
                showDeleteConfirmationDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to delete ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteProduct();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteProduct() {
        if (currentProductUri != null) {
            getContentResolver().delete(currentProductUri, null, null);
            finish();
        }
    }

    private boolean checkFields() {
        String pr_name = mPrName.getText().toString().trim();
        String pr_price = mPrPrice.getText().toString().trim();
        String pr_quantity = mPrQuantity.getText().toString().trim();
        if (TextUtils.isEmpty(pr_name) || TextUtils.isEmpty(pr_price) || TextUtils.isEmpty(pr_quantity)) {
            mPrName.setError("Enter name");
            mPrPrice.setError("Enter price");
            mPrQuantity.setError("Enter quantity");
            return false;
        }
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {InventoryContract.InventoryEntry._ID,
                InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryEntry.COLUMN_PRODUCT_PRICE,
                InventoryEntry.COLUMN_PRODUCT_QUANTITY,
                InventoryEntry.COLUMN_SUPPLIER_ID,
                InventoryEntry.COLUMN_PRODUCT_IMAGE,
                InventoryEntry.COLUMN_SUPPLIER_NAME};

        return new CursorLoader(this, currentProductUri, projection, null, null, null);
    }

    @Override
    public void onBackPressed() {
        Log.i("Back button pressed ", "Back button pressed");
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Discard your changes and quit editing?");
        builder.setPositiveButton("DIscard", discardButtonClickListener);
        builder.setNegativeButton("Keep Editing", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor.moveToFirst()) {
            int pNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
            int pPriceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_PRICE);
            int pQuantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_QUANTITY);
            int pSnameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_SUPPLIER_NAME);
            int pSidColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_SUPPLIER_ID);

            String pName = cursor.getString(pNameColumnIndex);
            String sName = cursor.getString(pSnameColumnIndex);
            String sId = cursor.getString(pSidColumnIndex);
            int price = cursor.getInt(pPriceColumnIndex);
            int quantity = cursor.getInt(pQuantityColumnIndex);

            String imageUriString = cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_IMAGE));
            if (imageUriString.startsWith("content://com.android.providers.media.documents/document/image")) {
                mPrImage.setImageURI(Uri.parse(imageUriString));
            } else {
                mPrImage.setImageResource(R.drawable.add_image);
            }

            mPrName.setText(pName);
            mSuppName.setText(sName);
            mSuppId.setText(sId);
            mPrPrice.setText(Integer.toString(price));
            mPrQuantity.setText(Integer.toString(quantity));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mPrImage.setImageURI(null);
    }
}
