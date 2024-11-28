package com.example.intentbt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;

public class ImageActivity extends Activity {

    TableLayout myTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_image);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myTable=(TableLayout) findViewById(R.id.tableLayoutImage);

        int soDong= 5;
        int soCot=3;
        //Tron mang
        Collections.shuffle(MainActivity.arrayName);

        //Tao dong va cot
        for(int i=1;i<=soDong;i++){
            TableRow tableRow= new TableRow(this);

            //Tạo cột-> ImageView
            for(int j=1;j<=soCot;j++){
                ImageView imageView= new ImageView(this);
                TableRow.LayoutParams layoutParams= new TableRow.LayoutParams(360,360);
                imageView.setLayoutParams(layoutParams);

                int vitri= soCot*(i-1)+ j -1;

                int idHinh= getResources().getIdentifier(MainActivity.arrayName.get(vitri),"drawable",getPackageName());
                imageView.setImageResource(idHinh);
                //add imageView vào TableRow
                tableRow.addView(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent();
                        intent.putExtra("tenhinhchon", MainActivity.arrayName.get(vitri));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
            //add tablerow vao table
            myTable.addView(tableRow);
        }
    }
}