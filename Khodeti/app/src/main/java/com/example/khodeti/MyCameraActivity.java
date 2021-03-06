package com.example.khodeti;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.khodeti.Networking.ReceiveImageResponse;
import com.example.khodeti.Networking.RetrofitClientInstance;
import com.example.khodeti.Networking.SendImageApi;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MyCameraActivity extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    String encodedImage;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout_activity);
        button = findViewById(R.id.kos);

        this.imageView = (ImageView)this.findViewById(R.id.imageView1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                koskon();
            }
        });
        Button photoButton = (Button) this.findViewById(R.id.button1);
        photoButton.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            System.out.println("kir to kazemi" + photo.);
            imageView.setImageBitmap(photo);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
             encodedImage = Base64.encodeToString(byteArray , Base64.DEFAULT);
//            photo.recycle();



        }
    }
    public void koskon(){
        Log.d("kos" , encodedImage);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://172.20.10.3:8000/photo/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("kir" , response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getCause().getMessage());
                Log.d("kiri" ,error.getCause().getLocalizedMessage() );

            }
        }){
            @Override
            protected Map<String , String> getParams(){
                Map<String , String> params = new HashMap<>();
                params.put("content" , (encodedImage));
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
