package com.example.btl.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btl.Adapter.NewsAdapter;
import com.example.btl.Model.News;
import com.example.btl.Model.XMLDOMParser;
import com.example.btl.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {
    NewsAdapter adapter;
    List<News> list;
    ListView listView;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news,container,false);
        myInit();
        adapter = new NewsAdapter(list,getContext(),R.layout.dong_news);
        listView.setAdapter(adapter);
        String linkRSS = "http://www.doivi.net/rss/tin/am-thuc-cuoc-song/";
        new ReadContentAsyncTask().execute(linkRSS);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getContext(),WebActivity.class);
                intent.putExtra("url",list.get(i).getLink());
                startActivity(intent);

            }
        });
        return view;
        
    }

    private void myInit() {
        listView = view.findViewById(R.id.lvNews);
        list = new ArrayList<>();
//        list.add(new News("aaaaa","https://i1-vnexpress.vnecdn.net/2022/01/20/ukraineafpedited-1642657314-1553-1642657581.jpg?w=220&h=132&q=100&dpr=1&fit=crop&s=_qI-xxFOu8Yy6Hx_fFTRiQ","mo ta",""));
//        list.add(new News("aaaaa","https://i1-vnexpress.vnecdn.net/2022/01/20/ukraineafpedited-1642657314-1553-1642657581.jpg?w=220&h=132&q=100&dpr=1&fit=crop&s=_qI-xxFOu8Yy6Hx_fFTRiQ","mo ta",""));
//        list.add(new News("aaaaa","https://i1-vnexpress.vnecdn.net/2022/01/20/ukraineafpedited-1642657314-1553-1642657581.jpg?w=220&h=132&q=100&dpr=1&fit=crop&s=_qI-xxFOu8Yy6Hx_fFTRiQ","mo ta",""));
//        list.add(new News("aaaaa","https://i1-vnexpress.vnecdn.net/2022/01/20/ukraineafpedited-1642657314-1553-1642657581.jpg?w=220&h=132&q=100&dpr=1&fit=crop&s=_qI-xxFOu8Yy6Hx_fFTRiQ","mo ta",""));

    }
    class ReadContentAsyncTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line +"\n");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
//            Log.d("-------",s);
            XMLDOMParser parser = new XMLDOMParser();
            Document doc =parser.getDocument(s);
            NodeList nodeList = doc.getElementsByTagName("item");
            String title = "";
            String link = "";
            String desc = "";
            String imageLink = "";
            String htmlDesc = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element,"title");
                link = parser.getValue(element,"link");
                htmlDesc = parser.getValueDesc(element,"description");
                imageLink = parser.getImageLink(htmlDesc);
                desc = parser.getDescContent(htmlDesc);
                //Toast.makeText(MainActivity.this, ""+imageLink, Toast.LENGTH_SHORT).show();
                News news = new News(title,imageLink,desc,link);
                list.add(news);
                //Log.d("AAA",title);

            }
            adapter.notifyDataSetChanged();

        }


    }
}
