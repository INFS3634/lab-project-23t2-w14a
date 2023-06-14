package au.edu.unsw.infs3634_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView mText;
    private Button mButton;
    private final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // setTitle() changes the title/heading of the header
        setTitle("Detail Activity");

        // Retrieve intent from MainActivity
        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");
        Log.d(TAG, msg);

        // Link to TextView component in XML and set text to msg
        mText = findViewById(R.id.tvText);
        mText.setText(msg);

        mButton = findViewById(R.id.btnDetail);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetailButton();
            }
        });
    }

    protected void launchDetailButton() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        startActivity(intent);
    }
}