package com.example.ass1_lucntph42288;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ass1_lucntph42288.DAO.UserDAO;

public class DatLaiMatKhauActivity extends AppCompatActivity {

    EditText edt_NewPass;
    TextView edt_FgUsername;

    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datlaimatkhau);

        edt_FgUsername = findViewById(R.id.tv_username_taoMK);
        edt_NewPass = findViewById(R.id.edt_NewPass);
        Button btn_Submit = findViewById(R.id.btn_Submit);

        String tentaikhoan = getIntent().getStringExtra("username_key");
        edt_FgUsername.setText("Tên tài khoản: " + tentaikhoan);

        userDAO = new UserDAO(this);

        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpass = edt_NewPass.getText().toString();
                String username = getIntent().getStringExtra("username_key");
                String email = getIntent().getStringExtra("email_key");

                boolean passReset = userDAO.ResetPassword(username, email, newpass);
                if (passReset) {
                    Toast.makeText(DatLaiMatKhauActivity.this, "Mật khẩu đã được đặt lại", Toast.LENGTH_SHORT).show();
                    // Thực hiện chuyển hướng đến màn hình đăng nhập hoặc màn hình khác sau khi đặt lại mật khẩu thành công
                    Intent loginIntent = new Intent(DatLaiMatKhauActivity.this, DangNhapActivity.class);
                    startActivity(loginIntent);
//
//                     Kết thúc ResetPassActivity
                    finish();
                } else {
                    Toast.makeText(DatLaiMatKhauActivity.this, "Không thể đặt lại mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}