package com.example.ass1_lucntph42288.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ass1_lucntph42288.DTO.CongViecDTO;
import com.example.ass1_lucntph42288.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class CongViecDAO {
    MyDbHelper dbHelper;

    SQLiteDatabase db;

    public CongViecDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }



    public ArrayList<CongViecDTO> getList() {
        ArrayList<CongViecDTO> congViecDTOS = new ArrayList<>();
        Cursor c = db.rawQuery("Select * from Tasks", null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                congViecDTOS.add(
                        new CongViecDTO(
                                c.getInt(0),
                                c.getString(1),
                                c.getString(2),
                                c.getInt(3),
                                c.getString(4),
                                c.getString(5),
                                c.getInt(6)));
            } while (c.moveToNext());
        }
        return congViecDTOS;
    }

    public int addRow(CongViecDTO congViecDTO) {
        ContentValues values = new ContentValues();
        values.put("name", congViecDTO.getName());
        values.put("content", congViecDTO.getContent());
        values.put("status", congViecDTO.getStatus());
        values.put("start_task", congViecDTO.getStart());
        values.put("end_task", congViecDTO.getEnd());
        values.put("user_id", congViecDTO.getUser_id());
        return (int) db.insert("Tasks", null, values);
    }

    public int update(CongViecDTO congViecDTO) {
        ContentValues values = new ContentValues();
        values.put("name", congViecDTO.getName());
        values.put("content", congViecDTO.getContent());
        values.put("status", congViecDTO.getStatus());
        values.put("start_task", congViecDTO.getStart());
        values.put("end_task", congViecDTO.getEnd());
        values.put("user_id", congViecDTO.getUser_id());
        String[] id = new String[]{String.valueOf(congViecDTO.getId())};
        return db.update("Tasks", values, "id=?", id);
    }

    public int delete(CongViecDTO congViecDTO) {
        String[] id = new String[]{String.valueOf(congViecDTO.getId())};
        return db.delete("Tasks", "id=?", id);
    }

}
