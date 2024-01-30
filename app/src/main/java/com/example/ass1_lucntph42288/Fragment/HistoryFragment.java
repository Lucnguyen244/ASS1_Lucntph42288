package com.example.ass1_lucntph42288.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass1_lucntph42288.Adapter.HistoryAdapter;
import com.example.ass1_lucntph42288.R;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private ArrayList<String> historyList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        // Khởi tạo RecyclerView và Adapter
        recyclerView = v.findViewById(R.id.recycler_view_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        historyList = getHistoryData(); // Lấy dữ liệu lịch sử từ cơ sở dữ liệu hoặc nguồn dữ liệu khác
//        historyAdapter = new HistoryAdapter(historyList, getContext());
        recyclerView.setAdapter(historyAdapter);
        return v;
    }

//    private ArrayList<String> getHistoryData() {
//
//    }
}
