package com.example.socialnetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
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
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    CircleImageView ivDash;
    RecyclerView recyclerView;
    TextView nameDash, tvwins, tvFollowers, tvFollowing;
    Button btnProfile;
    ProgressDialog progressDialog;
    List<String> leftImage = new ArrayList<>();
    List<String> rightImage = new ArrayList<>();
    List<String> desc = new ArrayList<>();
    List<String> times = new ArrayList<>();
    List<ModelNotification> modelNotifications = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading");
        progressDialog.show();
        progressDialog.setCancelable(false);

        ivDash = findViewById(R.id.ivDash);
        nameDash = findViewById(R.id.nameDash);
        tvwins = findViewById(R.id.tvwins);
        tvFollowers = findViewById(R.id.tvFollowers);
        tvFollowing = findViewById(R.id.tvFollowing);
        btnProfile = findViewById(R.id.btnProfile);



        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.noti)));
        tabLayout.addTab(tabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.video)));
        tabLayout.addTab(tabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.gift)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });



        loginApi();

        btnProfile.setOnClickListener(view -> startActivity(new Intent(Dashboard.this, Profile.class)));
    }

    private void loginApi() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://35.181.159.221:3000/api/v1/user/sociallogin";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("socialid", "0015223423434222");
            jsonBody.put("signup_type", "facebook");
            jsonBody.put("name", "Arun Sharma");
            jsonBody.put("device_token", "d8tX6m_ZUUsUhjZq0NCB0r:APA91bEuEI-uobu7JCkhbVPQsZ2uQnKJmkVCsxUjzD-34t0uOstIZY02fyrcGBJq_KB6t8YLL6YnbGVKqWYMTbEPQT8u_7aVdos7nKw_yjwBXiVw-kiRVFmH1GSn9431DX_xrUD3CRV7");
            jsonBody.put("lang", "en");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("device_id","9e019479456998af");
            jsonObject.put("device_type","android");
            jsonObject.put("device_token","fI0tcmTTTnSdc5kDHCF5sy:APA91bGSAhh-GIUwwFXVs8QrRJ7L-T-0IIXzBKuTESKbVwXaI0JAbqQVoLPVB_Ud7YXfFb4VN9D9x8YWmzd2jCHpTsySsS0zafXkrKaB8lquGSwIiMFF9Ds_hB-CUVVqx7uRHl2yTp85");
            jsonObject.put("timeZone","America/New_York");
            jsonObject.put("app_version","1.0");
            jsonObject.put("device_name","Android SDK built for x86");
            jsonObject.put("device_version","29");
            jsonBody.put("device", jsonObject);
            final String mRequestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    progressDialog.dismiss();
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

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Dashboard.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("username", username);
                        editor.putString("name",name);
                        editor.putString("youtube_link", youtube_link);
                        editor.putString("instagm_link", instagm_link);
                        editor.putString("tiktok_link", tiktok_link);
                        editor.putString("lang", lang);

                        editor.apply();

                        try {
                            Picasso.get().load(avatar).error(R.drawable.profileicon).placeholder(R.drawable.profileicon).fit().into(ivDash);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                            nameDash.setText(name);
                            tvwins.setText(winning);
                            tvFollowers.setText(followers);
                            tvFollowing.setText(following);



                        Toast.makeText(Dashboard.this, ""+ name, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                Toast.makeText(Dashboard.this, "loading failed...", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
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

}

