package com.sugengwin.multimatics.myshoppingmall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CategoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView lvCategory;
    private String[] categories = new String[] {
            "Sepatu", "Tas", "Dompet", "Sandal", "Kemeja", "Jeans"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().setTitle("Home");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(CategoryActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, categories);

        lvCategory = (ListView) findViewById(R.id.lv_category);
        lvCategory.setAdapter(categoryAdapter);
        lvCategory.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(CategoryActivity.this, "Item clicked "+categories[position], Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CategoryActivity.this, ProductActivity.class);
        intent.putExtra("category", categories[position]);
        if (intent != null) {
            startActivity(intent);
        }
    }
}
