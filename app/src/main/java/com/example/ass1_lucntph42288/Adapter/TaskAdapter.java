package com.example.ass1_lucntph42288.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass1_lucntph42288.DAO.CongViecDAO;
import com.example.ass1_lucntph42288.DTO.StatusDTO;
import com.example.ass1_lucntph42288.DTO.CongViecDTO;
import com.example.ass1_lucntph42288.NotificationUtils;
import com.example.ass1_lucntph42288.R;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    Context context;
    ArrayList<CongViecDTO> listTask;

    CongViecDAO congViecDAO;

    public TaskAdapter(Context context, ArrayList<CongViecDTO> listTask) {
        this.context = context;
        this.listTask = listTask;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_tasks, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CongViecDTO task = listTask.get(position);

        holder.txt_NameItemTask.setText("Name: " + task.getName());
        holder.txt_ContentItemTask.setText("Content: " + task.getContent());
        holder.txt_StartItemTask.setText("Start: " + task.getStart());
        holder.txt_EndItemTask.setText("End: " + task.getEnd());
        holder.txt_Id_UserItemTask.setText("ID User: " + task.getUser_id());

        int status = task.getStatus();
        String statusText = null;

        switch (status) {
            case 0:
                statusText = "Mới tạo";
                break;
            case 1:
                statusText = "Đang làm";
                break;
            case 2:
                statusText = "Hoàn thành";
                break;
            case -1:
                statusText = "Thùng rác";
                break;
        }
        holder.txt_StatusItemTask.setText("Status: " + statusText);

        holder.btn_XoaItemTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhân xoá");
                builder.setMessage("Bạn có muốn xoá không");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        congViecDAO = new CongViecDAO(context);
                        if (congViecDAO.delete(task) > 0) {
                            listTask.remove(task);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                            NotificationUtils.GuiThongBao(context, "Thông Báo","Xoá thành công");
                        }
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Không làm gì khi người dùng chọn "Không"
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

        holder.btn_SuaItemTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate(listTask.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listTask.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_NameItemTask, txt_ContentItemTask, txt_StartItemTask,
                txt_EndItemTask, txt_StatusItemTask, txt_Id_UserItemTask;
        Button btn_SuaItemTasks, btn_XoaItemTasks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_NameItemTask = itemView.findViewById(R.id.txt_NameItemTask);
            txt_ContentItemTask = itemView.findViewById(R.id.txt_ContentItemTask);
            txt_StartItemTask = itemView.findViewById(R.id.txt_StartItemTask);
            txt_EndItemTask = itemView.findViewById(R.id.txt_EndItemTask);
            txt_StatusItemTask = itemView.findViewById(R.id.txt_StatusItemTask);
            txt_Id_UserItemTask = itemView.findViewById(R.id.txt_Id_UserItemTask);
            btn_SuaItemTasks = itemView.findViewById(R.id.btn_SuaItemTasks);
            btn_XoaItemTasks = itemView.findViewById(R.id.btn_XoaItemTasks);

        }
    }

    public void showDialogUpdate(CongViecDTO congViecDTO) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_task, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();

        congViecDAO = new CongViecDAO(context);


        EditText edt_DialogUpdate_Name = view.findViewById(R.id.edt_DialogUpdate_Name);
        EditText edt_DialogUpdate_Content = view.findViewById(R.id.edt_DialogUpdate_Content);
        EditText edt_DialogUpdate_Start = view.findViewById(R.id.edt_DialogUpdate_Start);
        EditText edt_DialogUpdate_End = view.findViewById(R.id.edt_DialogUpdate_End);
        EditText edt_DialogUpdate_UserID = view.findViewById(R.id.edt_DialogUpdate_UserID);
        Button btn_DialogUpdate_Add = view.findViewById(R.id.btn_DialogUpdate_Add);

        Spinner SP_DialogUpdate_Status = view.findViewById(R.id.SP_DialogUpdate_Status);

        // hiển thị dữ liệu lên spinner
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter();
        SP_DialogUpdate_Status.setAdapter(spinnerAdapter);



        edt_DialogUpdate_Name.setText(congViecDTO.getName());
        edt_DialogUpdate_Content.setText(congViecDTO.getContent());
        edt_DialogUpdate_Start.setText(congViecDTO.getStart());
        edt_DialogUpdate_End.setText(congViecDTO.getEnd());
        edt_DialogUpdate_UserID.setText(String.valueOf(congViecDTO.getUser_id()));


        btn_DialogUpdate_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StatusDTO statusDTO = (StatusDTO) SP_DialogUpdate_Status.getSelectedItem();
                int status = statusDTO.getStatus();

                String name = edt_DialogUpdate_Name.getText().toString();
                String content = edt_DialogUpdate_Content.getText().toString();
                String start = edt_DialogUpdate_Start.getText().toString();
                String end = edt_DialogUpdate_End.getText().toString();
                String user_id = edt_DialogUpdate_UserID.getText().toString();

                congViecDTO.setContent(content);
                congViecDTO.setName(name);
                congViecDTO.setEnd(end);
                congViecDTO.setStart(start);
                congViecDTO.setUser_id(Integer.parseInt(user_id));
                congViecDTO.setStatus(status);

                if (name.isEmpty() || content.isEmpty()|| start.isEmpty()|| end.isEmpty()) {
                    Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    int id_moi = congViecDAO.update(congViecDTO);
                    if (id_moi > 0) {
                        listTask.clear();
                        listTask = congViecDAO.getList();
                        notifyDataSetChanged();
                        dialog.dismiss();
                        NotificationUtils.GuiThongBao(context, "Thông Báo", "Bạn đã cập nhật thành công");
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
