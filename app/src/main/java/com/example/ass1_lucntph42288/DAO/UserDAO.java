package com.example.ass1_lucntph42288.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ass1_lucntph42288.DTO.UserDTO;
import com.example.ass1_lucntph42288.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class UserDAO {

    MyDbHelper dbHelper;
    SQLiteDatabase db;

    public UserDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // check login
    public boolean checkLogin(String username, String password) {
        Cursor c = db.rawQuery("Select * from User where username = ? AND password = ?", new String[]{username, password});
        Log.d("zzzzzzzzzzz", "checkLogin: "+c.getCount());
        return c.getCount() > 0;
    }
    // check register
    public boolean Register(String username, String password, String email, String fullname) {
       ContentValues values = new ContentValues();
       values.put("username", username);
       values.put("password", password);
       values.put("email", email);
       values.put("fullname", fullname);
       long check = db.insert("User", null, values);
      return check != -1;
    }

    public boolean ResetPass (String username, String newPass) {
        ContentValues values = new ContentValues();
        values.put("password", newPass);
        long check = db.update("User", values, "username = ?", new String[]{username});
        return check > 0;
    }

    public boolean ResetPassword(String username, String email, String newPass) {
        // Kiểm tra xem thông tin username và email có trùng khớp không
        String checkPass = ForgotPassword(username, email);
        if (!checkPass.equals("")) {
            return ResetPass(username, newPass);
        } else {
            return false;
        }
    }

 // forgot
    public String ForgotPassword(String username, String email) {
        Cursor c = db.rawQuery("Select password From User where username = ? AND email = ?", new String[]{username, email});
        if (c.getCount() > 0) {
            c.moveToFirst();
            return c.getString(0);
        } else {
            return "";
        }
    }


    public ArrayList<UserDTO> getList() {
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        Cursor c = db.rawQuery("Select * from User", null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                userDTOS.add(
                        new UserDTO(
                                c.getInt(0),
                                c.getString(1),
                                c.getString(2),
                                c.getString(3),
                                c.getString(4)
                        ));
            } while (c.moveToNext());
        }
        return userDTOS;
    }
    public int addrow(UserDTO userDTO) {
        ContentValues values = new ContentValues();
        values.put("username", userDTO.getUsername());
        values.put("email", userDTO.getEmail());
        values.put("password", userDTO.getPassword());
        values.put("fullname", userDTO.getFullname());
        return (int) db.insert("User", null, values);
    }

    public int update(UserDTO userDTO) {
        ContentValues values = new ContentValues();
        String[] id = new String[]{String.valueOf(userDTO.getId())};
        values.put("username", userDTO.getUsername());
        values.put("email", userDTO.getEmail());
        values.put("password", userDTO.getPassword());
        values.put("fullname", userDTO.getFullname());
        return db.update("User", values, "id=?", id);
    }

    public int delete(UserDTO userDTO) {
     String[] id = new String[]{String.valueOf(userDTO.getId())};
     Cursor c = db.rawQuery("Select * from User", null);
     if (c.getCount() > 0) {
         db.delete("User", "id=?", id);
     }
     return db.delete("User", "id=?", id);
    }

}
