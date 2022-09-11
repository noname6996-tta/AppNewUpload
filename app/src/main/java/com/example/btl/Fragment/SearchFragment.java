package com.example.btl.Fragment;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.Adapter.StoreAdapter;
import com.example.btl.Interface.CallBack.SearchRragmentCallback;
import com.example.btl.Interface.SearchFragmentInterface;
import com.example.btl.Model.Items_Suggest;
import com.example.btl.Model.Store;
import com.example.btl.Presenter.SearchFragmentPresenter;
import com.example.btl.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchFragmentInterface, SearchRragmentCallback {
    private View view;
    private List<Store> mListStore;
    private List<Items_Suggest> items_suggestList;
    private RecyclerView rec_search, suggestStoreRev;
    private SearchFragmentPresenter searchFragmentPresenter;
    private StoreAdapter storeAdapter;
    private EditText edt_search_store;
    private TextView tv_suggest_items1, tv_suggest_items2, tv_suggest_items3, tv_suggest_items4, tv_suggest_items5, tv_suggest_items6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);
        initUI();
        initSuggestItems();
        return view;
    }

    private void initSuggestItems() {
        tv_suggest_items1 = view.findViewById(R.id.tv_suggest_items1);
        tv_suggest_items2 = view.findViewById(R.id.tv_suggest_items2);
        tv_suggest_items3 = view.findViewById(R.id.tv_suggest_items3);
        tv_suggest_items4 = view.findViewById(R.id.tv_suggest_items4);
        tv_suggest_items5 = view.findViewById(R.id.tv_suggest_items5);
        tv_suggest_items6 = view.findViewById(R.id.tv_suggest_items6);

        tv_suggest_items1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_search_store.setText(tv_suggest_items1.getText().toString().trim());
            }
        });
        tv_suggest_items2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_search_store.setText(tv_suggest_items2.getText().toString().trim());
            }
        });
        tv_suggest_items3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_search_store.setText(tv_suggest_items3.getText().toString().trim());
            }
        });
        tv_suggest_items4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_search_store.setText(tv_suggest_items4.getText().toString().trim());
            }
        });
        tv_suggest_items5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_search_store.setText(tv_suggest_items6.getText().toString().trim());
            }
        });
        tv_suggest_items6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_search_store.setText(tv_suggest_items6.getText().toString().trim());
            }
        });
    }

    private void initUI() {
        searchFragmentPresenter = new SearchFragmentPresenter(getContext(), this, this);
        Toolbar toolbar = view.findViewById(R.id.toolBar_search);
        toolbar.setTitle("Tìm kiếm");
        rec_search = view.findViewById(R.id.rec_search);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rec_search.setLayoutManager(linearLayoutManager);
        mListStore = new ArrayList<>();
        searchFragmentPresenter.addToListSearch();
        storeAdapter = new StoreAdapter(mListStore, getContext());
        rec_search.setAdapter(storeAdapter);
        edt_search_store = view.findViewById(R.id.edt_search_store);
        edt_search_store.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String storeSearch = edt_search_store.getText().toString().trim();
                storeAdapter.getFilter().filter(storeSearch);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onSuccess() {
    }

    @Override
    public void onFail() {
        Toast.makeText(getContext(), "Looix ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void callBack(Store store) {
        mListStore.add(store);
        storeAdapter.notifyDataSetChanged();
    }
}
