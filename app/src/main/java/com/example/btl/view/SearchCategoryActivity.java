package com.example.btl.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.btl.adapter.StoreAdapter;
import com.example.btl.myinterface.CategoryInterface;
import com.example.btl.model.Category;
import com.example.btl.model.Store;
import com.example.btl.presenter.ListTypeStorePresenter;
import com.example.btl.R;
import java.util.ArrayList;
import java.util.List;

public class SearchCategoryActivity extends AppCompatActivity implements CategoryInterface {
    private Toolbar toolBar_listStore;
    private RecyclerView rec_category;
    private List<Store> mListStore;
    private SearchView searchView;
    private StoreAdapter storeAdapter;
    private ListTypeStorePresenter listTypeStorePresenter;
    private ImageView img_category_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_store);
        initUi();
        initStore();
    }

    private void initUi() {
        toolBar_listStore = findViewById(R.id.toolBar_listStore);
        rec_category = findViewById(R.id.rec_category);
        img_category_back = findViewById(R.id.img_category_back);
        img_category_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolBar_listStore);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initStore() {
        rec_category = findViewById(R.id.rec_category);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rec_category.setLayoutManager(linearLayoutManager);
        mListStore = new ArrayList<>();
        storeAdapter = new StoreAdapter(mListStore, this);
        rec_category.setAdapter(storeAdapter);
        listTypeStorePresenter = new ListTypeStorePresenter(getApplicationContext(), this);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Category category = (Category) bundle.get("category");
        listTypeStorePresenter.CheckItem(category);
        toolBar_listStore.setTitle(category.getType());
        listTypeStorePresenter.readStoreList(category, mListStore, storeAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
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