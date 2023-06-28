package au.edu.unsw.infs3634_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import au.edu.unsw.infs3634_lab.api.Crypto;
import au.edu.unsw.infs3634_lab.recycler_view.CryptoAdapter;
import au.edu.unsw.infs3634_lab.recycler_view.RecyclerViewClickListener;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CryptoAdapter adapter;

    private final String TAG = "MainActivity";
    private final String MESSAGE = "Message from MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvList);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CryptoAdapter(Crypto.getCryptoCurrencies(), this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickRow(String symbol) {
        Log.d(TAG, "launchDetailActivity working");
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("symbol", symbol);
        startActivity(intent);
    }
}