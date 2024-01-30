package com.example.ass1_lucntph42288;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ass1_lucntph42288.DAO.UserDAO;

public class DangKyActivity extends AppCompatActivity {

    EditText edt_RgUsername, edt_RgPassword, edt_RgEmail, edt_RgFullname, edt_RgRePassword;
    Button btnRegister;
    ImageButton ib_back;

    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        edt_RgUsername = findViewById(R.id.edt_RgUsername);
        edt_RgPassword = findViewById(R.id.edt_RgPassword);
        edt_RgEmail = findViewById(R.id.edt_RgEmail);
        edt_RgFullname = findViewById(R.id.edt_RgFullname);
        //edt_RgRePassword = findViewById(R.id.edt_RgRePassword);
        ib_back = findViewById(R.id.ib_back);
        btnRegister = findViewById(R.id.btnRegister);


        //thoát: mũi tên
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(DangKyActivity.this, "Thoát", Toast.LENGTH_SHORT).show();
            }
        });
        userDAO = new UserDAO(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_RgUsername.getText().toString();
                String pass = edt_RgPassword.getText().toString();
                //String repass = edt_RgRePassword.getText().toString();
                String email = edt_RgEmail.getText().toString();
                String fullname = edt_RgFullname.getText().toString();
                if(user.isEmpty() || pass.isEmpty() || email.isEmpty() || fullname.isEmpty()){
                    Toast.makeText(DangKyActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    boolean check = userDAO.Register(user, pass, email, fullname);
                    if (check) {
                        Toast.makeText(DangKyActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(DangKyActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}