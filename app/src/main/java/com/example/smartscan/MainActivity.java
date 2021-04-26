package com.example.smartscan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

  public void start(View view) {
      //pour démarrer une autre activité
      Intent i = new Intent(this, Camera.class);
      startActivity(i);
  }
}


/*initialisation de l'activity
    private void initActivity() {
        // récupération des objets graphiques
        btnPrendrePhoto = (Button)findViewById(R.id.btnPicture);
        imgAffichePhoto = (ImageView)findViewById(R.id.imgPhoto);
        createOnclicBtnPrendrePhoto();
        //méthode pour gérer les événements
    }
    //événement clic sur bouton btnPrendrePhoto
    private void createOnclicBtnPrendrePhoto(){
    btnPrendrePhoto.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});

    }
 // Méthode pour l'accés à l'appareil photo et mémorise dans un fichier temporaire
    private void takePicture(){

        //crée un intent pour ouvrir une fenetre pour prendre la photo
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    //test pour contrôler que l'intent peut être géré
    if(intent.resolveActivity(getPackageManager()) !=null) {

    }
    }

    // Retour de l'appel de l'appareil photo
    // @param requestCode
    // @param resultCOde
    // @param data

    @Override
    protected void onActivityResult(int requestcode, int resultCode, Intent data) {
    super.onActivityResult(requestcode, resultCode, data);

    //vérifie le bon code de retour et l'état du retour ok
        if(requestcode == RETOUR_PRENDRE_PHOTO && resultCode==RESULT_OK) {
    //récupérer l'image
            Bitmap image = BitmapFactory.decodeFile(photoPath);

            //afficher l'image
            imgAffichePhoto.setImageBitmap(image);

            //créer un nom de fichier unique
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile = File.createTempFile("photo"+time,".jpg", photoDir);

                //Enregistrer le chemin complet
                photoPath = photoFile.getAbsolutePath();
                //créer l'URI
                Uri photoUri = FileProvider.getUriForFile(MainActivity.this,
                        MainActivity.this.getApplicationContext().getPackageName()+".provider",
                        photoFile);

                /*transfert URI vers l'intent pour enregistrement photo dans fichier temporaire
                //intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                //ouvrir l'activity par rapport à l'intent
                startActivityForResult(intent, RETOUR_PRENDRE_PHOTO);

            } catch (IOException e) {
                e.printStackTrace();
            }


*/