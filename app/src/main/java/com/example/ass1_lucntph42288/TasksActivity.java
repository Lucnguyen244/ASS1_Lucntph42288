package com.example.ass1_lucntph42288;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ass1_lucntph42288.DAO.CongViecDAO;
import com.example.ass1_lucntph42288.Fragment.GioiThieu;
import com.example.ass1_lucntph42288.Fragment.MyWeb;
import com.example.ass1_lucntph42288.Fragment.Home;
import com.example.ass1_lucntph42288.Fragment.caidat;
import com.google.android.material.navigation.NavigationView;

public class TasksActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fm;
    Home tasksFrag;
    GioiThieu aboutFrag;
    CongViecDAO congViecDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        congViecDAO = new CongViecDAO(getApplicationContext());
        // ánh xạ
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigationView);

        // set up toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setTitle("Danh Sách Công Việc");

        // nhấn item navigation
        tasksFrag = new Home();
        aboutFrag = new GioiThieu();
        fm = getSupportFragmentManager();
        // set fragment mặc định khi chạy lên
        fm.beginTransaction()
                .replace(R.id.frag_container, tasksFrag)
                .commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.dangxuat) {
                    Intent intent = new Intent(TasksActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                    finish(); // Kết thúc TasksActivity sau khi chuyển sang LoginActivity
                } else {

                    if (item.getItemId() == R.id.danhsachcongviec) {
                        fragment = tasksFrag;
                    } else if (item.getItemId() == R.id.gioithieu) {
                        fragment = new GioiThieu();
                    } else if (item.getItemId() == R.id.docbao) {
                        fragment = new MyWeb();
                    } else {
                        fragment = new caidat();
                    }

                    fm.beginTransaction()
                            .replace(R.id.frag_container, fragment)
                            .commit();

                    getSupportActionBar().setTitle(item.getTitle());
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


}