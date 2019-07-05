package com.wangliangjun.androidtraining133.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wangliangjun.androidtraining133.R;
import com.wangliangjun.androidtraining133.adapter.PythonAdapter;

public class PythonActivity extends BaseActivity {
    private RecyclerView pythonRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);
        pythonRecyclerView = findViewById(R.id.pythonRecyclerView);
        PythonAdapter pythonAdapter = new PythonAdapter(R.layout.item_python);
        pythonRecyclerView.setAdapter(pythonAdapter);


    }

}
