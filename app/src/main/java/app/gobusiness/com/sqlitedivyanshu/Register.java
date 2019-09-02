package app.gobusiness.com.sqlitedivyanshu;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class Register extends AppCompatActivity {

    private static final int MY_SORAGE = 1 ;
    private static final int MY_STORAGE_READ =1 ;
    EditText name,email,number,password;
    Button register;
    TextView login;
    ImageView imageView;
    Calendar calendar;
    public int  MY_REQUEST_CAMERA = 1888;
    DB_HELPER db_helper;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        imageView = findViewById(R.id.ImagePost);

        login = findViewById(R.id.login);

        name =findViewById(R.id.name);
        email =findViewById(R.id.email);
        number =findViewById(R.id.number);
        password =findViewById(R.id.password);

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reegisterFunction();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginIntent();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                if (checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},MY_REQUEST_CAMERA);
                }
                else if (checkSelfPermission((Manifest.permission.WRITE_EXTERNAL_STORAGE))!= PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_SORAGE);

                } else if (checkSelfPermission((Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED)
                {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_STORAGE_READ);
                }
                else {
                    cameraIntent();
                }


            }
        });



    }

    private void cameraIntent()
    {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,MY_REQUEST_CAMERA);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
    //    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==MY_REQUEST_CAMERA)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,MY_REQUEST_CAMERA);
            }
            else
            {
                Toast.makeText(this, "Permision Denied", Toast.LENGTH_SHORT).show();
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == MY_REQUEST_CAMERA  && resultCode== Activity.RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);

//            ByteArrayOutputStream byteArrayOutputStream  = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//            byte[] byteArray = byteArrayOutputStream.toByteArray();
//
//            encodedImage = Base64.encodeToString(byteArray,Base64.DEFAULT);

                Log.e("#","image save start" );
                String path = Environment.getExternalStorageDirectory().getPath();

            Log.e("path",path);

                File myDir = new File(path+ "/saved_image");

                Log.e("dir",""+myDir);
                myDir.mkdirs();

            Log.e("mkdir",""+myDir.mkdirs());

                Random random = new Random();

                int n = 10000;
                n = random.nextInt(n);

                String file_name = "Image-"+n+".jpg";

            Log.e("file name",""+file_name);

               file  =new File(myDir,file_name);

               Log.e("File",""+file);

                if (file.exists())
                {
                    Log.e("fle",""+file );
                }
                else
                {
                    Log.e("fle",""+file );
                }

                try
                {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,90,fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();

                    Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show();
                    Log.e("##","file saved" );


                   // sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+Environment.getExternalStorageDirectory())));


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        final Uri contentUri = Uri.fromFile(file);
                        scanIntent.setData(contentUri);
                        sendBroadcast(scanIntent);
                    } else {
                        final Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()));
                        sendBroadcast(intent);
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();

                }
        }
    }

    private void loginIntent() {
        Intent xyz = new Intent(getApplicationContext(),Login.class);
        startActivity(xyz);
    }

    private void reegisterFunction() {
        String name1, email1, number1, password1, file1;







        name1 = name.getText().toString();
        email1 = email.getText().toString();
        number1 = number.getText().toString();
        password1 = password.getText().toString();
//        file1 = file.toString();


        if (file==null)
        {
            Toast.makeText(this, "select image", Toast.LENGTH_SHORT).show();
            return;
        }else if (name1.isEmpty()) {
            name.setError("enter your name");
            return;
        } else if (email1.isEmpty()) {
            email.setError("enter email");
            return;
        } else if (number1.isEmpty()){
            number.setError("enter number");
            return;
        }

        file1 = file.toString();


        db_helper = new DB_HELPER(getApplicationContext());

        db_helper.addUsers(new UserModel(name1,email1,number1,password1,file1));

        Log.e("store",db_helper.toString());


        Intent register = new Intent(getApplicationContext(),Login.class);
        startActivity(register);


    }
}
