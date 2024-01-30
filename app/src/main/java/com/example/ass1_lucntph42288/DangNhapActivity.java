package com.example.ass1_lucntph42288;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ass1_lucntph42288.DAO.UserDAO;

public class DangNhapActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView txt_forgot, txt_signup;
    ImageView iv_mat;
    UserDAO userDAO;
    boolean isRegisterDialogShow = false;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txt_forgot = findViewById(R.id.txt_forgot);
        txt_signup = findViewById(R.id.txt_Signup);
        iv_mat = findViewById(R.id.iv_mat);
        userDAO = new UserDAO(this);

        final boolean[] isPassword = {false};
        iv_mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPassword[0]){
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_mat.setImageResource(R.drawable.mat_eye_24);
                    isPassword[0] = false;
                }else{
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    iv_mat.setImageResource(R.drawable.mat_eye_24);
                    isPassword[0] = true;
                }
            }
        });

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUsername.getText().toString();
                String pass = edtPassword.getText().toString();

                boolean check = userDAO.checkLogin(user, pass);
                if(user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(DangNhapActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else if (check) {
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DangNhapActivity.this, TasksActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    NotificationUtils.GuiThongBao(DangNhapActivity.this, "Thông Báo", "Bạn đã đăng nhập thành công");
                } else {
                    Toast.makeText(DangNhapActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }

            }
        });

       txt_forgot.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                showDialogForgot();
           }
       });


    }



    private void showDialogForgot() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_forgot, null);
//        View v = getLayoutInflater().inflate(R.layout.dialog_forgot, null);

        builder.setView(v);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edt_FgEmail = v.findViewById(R.id.edt_FgEmail);
        EditText edt_FgUsername = v.findViewById(R.id.edt_FgUsername);
        Button btnSend = v.findViewById(R.id.btn_FgSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_FgEmail.getText().toString();
                String username = edt_FgUsername.getText().toString();
                String matkhau = userDAO.ForgotPassword(username, email);
                if (matkhau.equals("")) {
                    Toast.makeText(DangNhapActivity.this, "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DangNhapActivity.this, DatLaiMatKhauActivity.class);
                    intent.putExtra("username_key", username);
                    intent.putExtra("email_key", email);
                    startActivity(intent);
                }
            }
        });
    }
}