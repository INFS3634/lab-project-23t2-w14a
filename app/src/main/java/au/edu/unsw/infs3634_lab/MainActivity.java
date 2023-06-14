package au.edu.unsw.infs3634_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mLaunch;

    private final String TAG = "MainActivity";
    private final String MESSAGE = "Message from MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // findViewById(R.id.<id in XML file>)
        mLaunch = findViewById(R.id.btnLaunch);

        mLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLaunchDetailActivity();
            }
        });
    }

    protected void onLaunchDetailActivity() {
        Log.d(TAG, "onLaunchDetailActivity working");
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("msg", MESSAGE);
        startActivity(intent);
    }
}