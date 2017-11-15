package com.example.auser.myapp14111302_n;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.auser.myapp14111302_n.data.StudentDAOFileImpl;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {
    public static StudentDAOFileImpl t;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter<MyAdapter.ViewHolder> mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    GestureDetector mGD;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = new StudentDAOFileImpl(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // t.add(new Student("Bob", "123", "123"));
        // t.add(new Student("Mary", "123", "123"));
        mGD = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        }
        );
        mRecyclerView.addOnItemTouchListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter = new MyAdapter(MainActivity.this, t.getData());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("ADD");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it = new Intent(MainActivity.this, AddActivity.class);
        startActivity(it);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View v = rv.findChildViewUnder(e.getX(), e.getY());
        Log.d("Touch", "onInterceptTouchEvent");
        if (mGD.onTouchEvent(e))
        {
            Log.d("Touch", "Single Tap up");
            int position = rv.getChildLayoutPosition(v);
            // Toast.makeText(MainActivity.this, "posi:" + position, Toast.LENGTH_SHORT).show();
            if (position >= 0)
            {
                Intent it = new Intent(MainActivity.this, DetailActivity.class);
                it.putExtra("id", t.getData()[position].id);
                startActivity(it);
            }
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
