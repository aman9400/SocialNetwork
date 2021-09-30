package com.example.socialnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.method.KeyListener;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    EditText usernamePro, yournamePro, instaPro, tiktokPro, youtubePro, enPro;
    TextView back, editPro;
    ImageView camera_click;
    CircleImageView ivPro;
    Button submitPro;
    Bitmap bitmap;
    public static final int PICK_IMAGE = 1;
    String encodedImage="a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernamePro = findViewById(R.id.usernamePro);
        yournamePro = findViewById(R.id.yournamePro);
        instaPro = findViewById(R.id.instaPro);
        tiktokPro = findViewById(R.id.tiktokPro);
        youtubePro = findViewById(R.id.youtubePro);
        enPro = findViewById(R.id.enPro);
        back = findViewById(R.id.back);
        editPro = findViewById(R.id.editPro);
        camera_click = findViewById(R.id.camera_click);
        ivPro = findViewById(R.id.ivPro);
        submitPro = findViewById(R.id.submitPro);

        disabledField1();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Profile.this);

        usernamePro.setText( preferences.getString("username", ""));
        yournamePro.setText( preferences.getString("name", ""));
        youtubePro.setText( preferences.getString("youtube_link", ""));
        instaPro.setText( preferences.getString("instagm_link", ""));
        tiktokPro.setText( preferences.getString("tiktok_link", ""));
        enPro.setText( preferences.getString("lang", ""));

        editPro.setOnClickListener(view->{

            yournamePro.setEnabled(true);
            yournamePro.setKeyListener(mKeyListener);
            youtubePro.setEnabled(true);
            youtubePro.setKeyListener(mKeyListener);
            instaPro.setEnabled(true);
            instaPro.setKeyListener(mKeyListener);
            tiktokPro.setEnabled(true);
            tiktokPro.setKeyListener(mKeyListener);
            enPro.setEnabled(true);
            enPro.setKeyListener(mKeyListener);

        });

        camera_click.setOnClickListener(v -> imagefromStorage());

        submitPro.setOnClickListener(v -> updateProfileApi());

        back.setOnClickListener(v -> {
            startActivity(new Intent(Profile.this, Dashboard.class));
            finish();
        });

    }
    private void imagefromStorage() {
        Intent intent = new Intent();
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.putExtra("return-data", true);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_picture)), PICK_IMAGE);
    }

    /*---------  Result for Selected Image -----*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && data != null) {

            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);


                encodedImage = imageToString(bitmap);

                Log.e("a", encodedImage);

                byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                // card_profile.setImageBitmap(decodedByte);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    String imageToString(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }


    private void updateProfileApi() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://35.181.159.221:3000/api/v1/user/update_profile";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("username", "Arun1989");
            jsonBody.put("name", "aa");
            jsonBody.put("tiktok_link", tiktokPro.getText().toString());
            jsonBody.put("profile_picture", encodedImage);
            jsonBody.put("youtube_link", youtubePro.getText().toString());
            jsonBody.put("instagm_link", instaPro.getText().toString());
            jsonBody.put("lang", "en");

            final String mRequestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
//                    progressDialog.dismiss();
                    if(jsonObject1.getInt("status")==1){
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                        String avatar = jsonObject2.getString("avatar");
                        String jwt_token = jsonObject2.getString("jwt_token");
                        String signupType = jsonObject2.getString("signup_type");
                        String lang = jsonObject2.getString("lang");
                        String youtube_link = jsonObject2.getString("youtube_link");
                        String tiktok_link = jsonObject2.getString("tiktok_link");
                        String instagm_link = jsonObject2.getString("instagm_link");
                        String _id = jsonObject2.getString("_id");
                        String socialId = jsonObject2.getString("socialId");
                        String name = jsonObject2.getString("name");
                        String username = jsonObject2.getString("username");
                        String following = jsonObject2.getString("following");
                        String winning = jsonObject2.getString("winning");
                        String followers = jsonObject2.getString("followers");

                        usernamePro.setText( username);
                        yournamePro.setText( name);
                        youtubePro.setText( youtube_link);
                        instaPro.setText( instagm_link);
                        tiktokPro.setText( tiktok_link);
                        enPro.setText( lang);


                        try {
                            Picasso.get().load(avatar).error(R.drawable.profileicon).placeholder(R.drawable.profileicon).fit().into(ivPro);
                        }catch (Exception e){
                            e.printStackTrace();
                        }




                        Toast.makeText(Profile.this, ""+ name, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                Toast.makeText(Profile.this, "loading failed...", Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    return mRequestBody == null ? null : mRequestBody.getBytes(StandardCharsets.UTF_8);
                }

//                @Override
//                protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                    String responseString = response;
//                    if (response != null) {
//
//                        responseString = String.valueOf(response.statusCode);
//
//                    }
//                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*-------- Disable Profile Component --------*/

    KeyListener mKeyListener;

    public void disabledField1() {
        mKeyListener = usernamePro.getKeyListener();


        youtubePro.setEnabled(false);
        youtubePro.setKeyListener(null);
        instaPro.setEnabled(false);
        instaPro.setKeyListener(null);
        enPro.setEnabled(false);
        enPro.setKeyListener(null);
        tiktokPro.setEnabled(false);
        tiktokPro.setKeyListener(null);
        yournamePro.setEnabled(false);
        yournamePro.setKeyListener(null);

        // card_profile.setKeyListener(null);

    }
}