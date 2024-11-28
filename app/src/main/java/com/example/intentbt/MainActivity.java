package com.example.intentbt;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> arrayName;
    ImageView imgGoc, imgNhan;
    int REQUEST_CODE_IMAGE= 123;
    String tenHinhGoc="";
    TextView txtDiem;
    int total=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgGoc=(ImageView) findViewById(R.id.imageViewGoc);
        imgNhan=(ImageView) findViewById(R.id.imageViewNhan);
        txtDiem=(TextView) findViewById(R.id.textViewDiem) ;
        txtDiem.setText(total+"");

        String[] mangTen= getResources().getStringArray(R.array.list_name);
        arrayName= new ArrayList<>(Arrays.asList(mangTen));

        //trộn mảng
        Collections.shuffle(arrayName);
        tenHinhGoc=arrayName.get(5);

        int idHinh= getResources().getIdentifier(arrayName.get(5),"drawable",getPackageName());
        imgGoc.setImageResource(idHinh);

        imgNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,ImageActivity.class),REQUEST_CODE_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode ==REQUEST_CODE_IMAGE && resultCode==RESULT_OK && data != null){
            String tenHinhNhan= data.getStringExtra("tenhinhchon");
            int idHinhNhan= getResources().getIdentifier(tenHinhNhan,"drawable",getPackageName());
            imgNhan.setImageResource(idHinhNhan);
            //so sánh theo tên hình
            if(tenHinhGoc.equals(tenHinhNhan)) {
                Toast.makeText(this, "Chính xác! \nBạn được cộng 10 đỉm", Toast.LENGTH_SHORT).show();
                //COng diem
                total+=10;
                //đổi hình gốc
                new CountDownTimer(2000, 100) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        Collections.shuffle(arrayName);
                        tenHinhGoc=arrayName.get(5);

                        int idHinh= getResources().getIdentifier(arrayName.get(5),"drawable",getPackageName());
                        imgGoc.setImageResource(idHinh);

                    }
                }.start();

            }else{
                Toast.makeText(this, "Sai rồi :( \n Bạn bị trừ 5 đỉm", Toast.LENGTH_SHORT).show();
                total-=5;
            }
            txtDiem.setText(total+"");
        }
        //Kiem tra man hinh thu 2 khong chon hinh
        if(requestCode== REQUEST_CODE_IMAGE && resultCode== RESULT_CANCELED){
            Toast.makeText(this, "Bạn chưa chọn hình? Trừ 15 đỉm:))", Toast.LENGTH_SHORT).show();
            total-=15;
        }
        txtDiem.setText(total+"");

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.menuReload){
            Collections.shuffle(arrayName);
            tenHinhGoc=arrayName.get(5);

            int idHinh= getResources().getIdentifier(arrayName.get(5),"drawable",getPackageName());
            imgGoc.setImageResource(idHinh);
        }
        return super.onOptionsItemSelected(item);
    }
}