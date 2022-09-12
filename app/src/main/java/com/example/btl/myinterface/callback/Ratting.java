package com.example.btl.myinterface.callback;

import com.example.btl.model.Rank;

public interface Ratting {
    void checkId_store(Integer id, Rank rank);
    void checkId_user(Integer id_user);
}
