package com.example.btl.View;

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
import com.example.btl.Adapter.StoreAdapter;
import com.example.btl.Interface.ListTypeStoreInterface;
import com.example.btl.Model.Category;
import com.example.btl.Model.Store;
import com.example.btl.Presenter.ListTypeStorePresenter;
import com.example.btl.R;
import java.util.ArrayList;
import java.util.List;

public class CategoryStore extends AppCompatActivity implements ListTypeStoreInterface {
    private Toolbar toolBar_listStore;
    private RecyclerView rec_list_type_store;
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

    private void initUi() {
        toolBar_listStore = findViewById(R.id.toolBar_listStore);
        rec_list_type_store = findViewById(R.id.rec_list_type_store);
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
        rec_list_type_store = findViewById(R.id.rec_list_type_store);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rec_list_type_store.setLayoutManager(linearLayoutManager);
        mListStore = new ArrayList<>();
        storeAdapter = new StoreAdapter(mListStore, this);
        rec_list_type_store.setAdapter(storeAdapter);
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