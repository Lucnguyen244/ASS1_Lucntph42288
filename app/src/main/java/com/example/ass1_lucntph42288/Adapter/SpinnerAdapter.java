package com.example.ass1_lucntph42288.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ass1_lucntph42288.DTO.StatusDTO;
import com.example.ass1_lucntph42288.R;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {

    ArrayList<StatusDTO> listStatus = new ArrayList<>();

    public SpinnerAdapter() {
        // Thêm các StatusItem vào danh sách
        listStatus.add(new StatusDTO(-1, "Thùng rác"));
        listStatus.add(new StatusDTO(0, "Mới tạo"));
        listStatus.add(new StatusDTO(1, "Đang làm"));
        listStatus.add(new StatusDTO(2, "Hoàn thành"));
    }



    @Override
    public int getCount() {
        return listStatus.size();
    }

    @Override
    public Object getItem(int position) {
        return listStatus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(parent.getContext(),R.layout.item_spinner, null);
        TextView txt_SpStatus = convertView.findViewById(R.id.txt_SpStatus);

       StatusDTO statusDTO = listStatus.get(position);

       String displayText = statusDTO.getStatus() + " - " + statusDTO.getStatusText();
       txt_SpStatus.setText(displayText);

        return convertView;
    }
}
