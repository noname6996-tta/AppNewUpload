package com.example.btl.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.Adapter.StoreAdapter;
import com.example.btl.Interface.ListTypeStoreInterface;
import com.example.btl.Model.Category;
import com.example.btl.Model.Store;
import com.example.btl.Presenter.ListTypeStorePresenter;
import com.example.btl.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListTypeStore extends AppCompatActivity implements ListTypeStoreInterface {
    private EditText edt_search_store;
    private ImageButton btn_search_store;
    private Toolbar toolBar_listStore;
    private RecyclerView rec_list_type_store;
    private List<Store> mListStore;
    private androidx.appcompat.widget.SearchView searchView;
    private StoreAdapter storeAdapter;
    private ListTypeStorePresenter listTypeStorePresenter;
    private ImageView img_LystTypeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_type_store);

        initUi();
        initStore();
        listTypeStorePresenter = new ListTypeStorePresenter(getApplicationContext(),this);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Category category = (Category) bundle.get("category");
        listTypeStorePresenter.CheckItem(category);
        toolBar_listStore.setTitle(category.getType());
        //readStoreList(category);
        listTypeStorePresenter.readStoreList(category,mListStore,storeAdapter);

    }

    private void initUi() {
        toolBar_listStore = findViewById(R.id.toolBar_listStore);
        rec_list_type_store = findViewById(R.id.rec_list_type_store);
        img_LystTypeBack = findViewById(R.id.img_LystTypeBack);
        img_LystTypeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolBar_listStore);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    private void initStore(){
        rec_list_type_store = findViewById(R.id.rec_list_type_store);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rec_list_type_store.setLayoutManager(linearLayoutManager);
        mListStore = new ArrayList<>();
        storeAdapter = new StoreAdapter(mListStore,this);
        rec_list_type_store.setAdapter(storeAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search_action).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                storeAdapter.getFilter().filter(query);
                    return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                storeAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onNullItems() {
        Toast.makeText(this, "Dữ liệu rỗng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failRespon() {
        Toast.makeText(this, "Vui lòng khởi động lại ứng dụng", Toast.LENGTH_SHORT).show();
    }
}