package com.example.newswaa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Homefragment extends Fragment {

    String api="ffb2a6f3643749dfaa67c52f4c2d1256";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String Country="in";
    private RecyclerView recyclerViewfh;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.homefragment,null);
        recyclerViewfh=v.findViewById(R.id.homefrag);
        modelClassArrayList=new ArrayList<>();
        recyclerViewfh.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new Adapter(getContext(),modelClassArrayList);
        recyclerViewfh.setAdapter(adapter);

        findNews();



        return v;
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getNews(Country,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {

                if (response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });


    }
}
