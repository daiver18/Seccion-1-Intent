package com.example.daiverandresdoria.app_01;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Set;

public class thirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imageButtonPhone;
    private ImageButton imageButtonWeb;
    private ImageButton imageButtonCamera;
    private Button btn;
    private Button btnContac;
    private EditText editTextMail;
    private ImageButton btnMail;

    private final int CALL_CODE = 100;
    private final int CAM_CODE = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        //flecha para regresar a inicio
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextWeb = findViewById(R.id.editTextWeb);
        imageButtonPhone = findViewById(R.id.imageButtonPhone);
        imageButtonWeb = findViewById(R.id.imageButtonWeb);
        imageButtonCamera = findViewById(R.id.imageButtonCamera);
        btn = findViewById(R.id.DefaultButton);
        btnContac = findViewById(R.id.buttonContact);
        btnMail = findViewById(R.id.imageButtonMail);
        editTextMail = findViewById(R.id.editTextMail);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package:" + getPackageName()));
                /*i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);*/
                startActivity(i);
            }
        });

        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PhoneNumber = editTextPhone.getText().toString();
                if (PhoneNumber != null) {
                    //Comprobar a version de android
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkPermission(Manifest.permission.CALL_PHONE)) {
                            Intent i_CALL = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+PhoneNumber));
                            startActivity(i_CALL);
                        }else{
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_CODE);
                        }
                    } else {
                        OldVer(PhoneNumber);
                    }
                }
            }

            private void OldVer(String PhoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + PhoneNumber));
                if (checkPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                    } else {
                    Toast.makeText(thirdActivity.this, "you declined de access", Toast.LENGTH_LONG).show();
                    }
            }
        }
        );

        //boton para el navegador
        imageButtonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editTextWeb.getText().toString();
                if (url != null && !url.isEmpty()){
                    Intent intentweb = new Intent();
                    intentweb.setAction(Intent.ACTION_VIEW);
                    intentweb.setData(Uri.parse("http://"+url));
                    startActivity(intentweb);
                }else{
                    Toast.makeText(thirdActivity.this, "input a url valid", Toast.LENGTH_LONG).show();
                }
            }
        });

        //button Contacts
        btnContac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContact = new Intent();
                intentContact.setAction(Intent.ACTION_VIEW);
                intentContact.setData(Uri.parse("content://contacts/people"));


                //Phone mode 2
                Intent intentPhone2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 5666344"));

                startActivity(intentContact);
            }
        });

        //button for email
        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mail = editTextMail.getText().toString();
                String mail = "daiverandres";
                if (Mail!=null && !Mail.isEmpty()) {
                    Intent intentMail = new Intent(Intent.ACTION_SEND, Uri.parse(Mail));
                    intentMail.setType("text/plain");
                    intentMail.putExtra(Intent.EXTRA_SUBJECT, "Mail title");
                    intentMail.putExtra(Intent.EXTRA_TEXT, "Hi there...");
                    intentMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"daiverandres99@gmail.com", "doriamass99@gmail.com"});
                    startActivity(Intent.createChooser(intentMail, "Open with: "));
                }else{
                    Toast.makeText(thirdActivity.this, "input a valid email", Toast.LENGTH_LONG).show();
                }
            }
        });

        imageButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCam = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intentCam, CALL_CODE );
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CALL_CODE:
                if (resultCode == Activity.RESULT_OK){
                    String Result = data.toUri(0);
                    Toast.makeText(this,"Result:"+Result,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"there was a error with the picture, try again",Toast.LENGTH_LONG).show();

                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    //comprobar si concedo permisos
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //concedio permisos
                        String PhoneNumber = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + PhoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {return;}
                        startActivity(intentCall);
                    }else{
                        //no consesido permisos
                        Toast.makeText(thirdActivity.this,"you declined the access",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private boolean checkPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
