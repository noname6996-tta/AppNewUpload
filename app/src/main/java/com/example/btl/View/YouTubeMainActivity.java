package com.example.btl.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Adapter.VideoYoutubeAdapter;
import com.example.btl.Model.VideoYoutube;
import com.example.btl.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YouTubeMainActivity extends AppCompatActivity {
    public static String API_KEY =  "AIzaSyCrlQWGfiphh9oO-Kmra-jMOLmm7DXwCqU";
    String ID_PlayList = "PLpfjxvspXEVEcX1eC0_k0FFTUeUoFo2Og";
    String url_GetJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ ID_PlayList +"&key="+ API_KEY +"&maxResults=50";

    ListView lvVideo;
    ArrayList<VideoYoutube> arrayVideo;
    VideoYoutubeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_activity_main);

        lvVideo = findViewById(R.id.lvVideo);
        arrayVideo = new ArrayList<>();
        adapter = new VideoYoutubeAdapter(this, R.layout.row_video_youtube,arrayVideo);
        lvVideo.setAdapter(adapter);

        GetJsonYoutube(url_GetJson);

        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =  new Intent(YouTubeMainActivity.this, PlayVideoActivity.class);
                intent.putExtra("idVideoYoutube", arrayVideo.get(i).getIdVideo());
                startActivity(intent);
            }
        });
    }
    private void GetJsonYoutube(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");
                            String title = "";
                            String url = "";
                            String idVideo = "";
                            for (int i = 0; i < jsonItems.length(); i++) {
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                title = jsonSnippet.getString("title");
                                JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                                url = jsonMedium.getString("url");
                                JSONObject jsonResourceID = jsonSnippet.getJSONObject("resourceId");
                                idVideo = jsonResourceID.getString("videoId");

                                arrayVideo.add(new VideoYoutube(title,url,idVideo));

                            }
                            adapter.notifyDataSetChanged();
                        }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(YouTubeMainActivity.this,"Loi !!",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}