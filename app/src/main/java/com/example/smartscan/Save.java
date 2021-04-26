package com.example.smartscan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Save extends AppCompatActivity {
    private Bitmap imageBitmap ;
    private String errorM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        imageBitmap= (Bitmap)getIntent().getExtras().get("photo");
        ImageView imageView = findViewById(R.id.ivBackgroundSave);
        imageView.setImageBitmap(imageBitmap);
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, 1);
    }

    public void returnPreview(View view) {
        Intent i= new Intent(this,Previews.class);
        startActivity(i);

    }
    public void donePdf(View view) {
        TextInputEditText nameFichier=findViewById(R.id.edittext);
        String nameFichierbis =nameFichier.getText().toString();
        Toast.makeText(this,nameFichierbis,Toast.LENGTH_LONG).show();

        // create a new document
        PdfDocument document = new PdfDocument();

        //       // create a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(792, 1120, 1).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);
        Paint paint = new Paint();
        page.getCanvas().drawBitmap(imageBitmap,56,40,paint);

        //     page.getCanvas().drawBitmap(imageBitmap,100,100,null);

        // finish the page
        document.finishPage(page);

        // add more pages
        File myFilePath = new File(Environment.getExternalStorageDirectory()+"/"+nameFichierbis+".pdf");

        // File myFilePath = new File("/Download"+"/"+nameFichierbis+".pdf");
        // write the document content
        Log.println(Log.DEBUG,"test","myFilePath");
        try {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                Log.println(Log.DEBUG,"test","myFilePath2");
                FileOutputStream fos=new FileOutputStream(myFilePath);
                document.writeTo(fos);
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            errorM=e.toString();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Intent i= new Intent(this,Share.class);
        i.putExtra("error",errorM);
        startActivity(i);
    }

}