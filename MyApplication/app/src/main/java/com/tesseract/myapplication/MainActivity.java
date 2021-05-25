package com.tesseract.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tesseract.mylibrary.MyLib;
import com.tesseract.mylibrary.MyListData;

public class MainActivity extends AppCompatActivity implements MainListAdapter.MainListAdapterListener{
    private List<MyListData> listOfApp;
    private List<MyListData> tempListHolder = new ArrayList();
    List<MyListData> tempList = new ArrayList();
    private MainListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        listOfApp = MyLib.getInstance().getListOfInstalledApps(this);
        adapter = new MainListAdapter(listOfApp,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    listOfApp.clear();
                    listOfApp.addAll(tempListHolder);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    void filter(String text) {
        for (MyListData myListData : listOfApp) {
            if (myListData.getName().toLowerCase().contains(text.toLowerCase())) {
                tempList.add(myListData);
            }
        }
        if (tempListHolder.size() == 0) {
            tempListHolder.addAll(listOfApp);
        }
        listOfApp.clear();
        listOfApp.addAll(tempList);
        tempList.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClickItem(MyListData myListData) {
        PackageManager pm = getPackageManager();
        Intent launchIntent = pm.getLaunchIntentForPackage(myListData.getPackages());
        startActivity(launchIntent);
    }
}