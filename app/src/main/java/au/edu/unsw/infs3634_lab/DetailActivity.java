package au.edu.unsw.infs3634_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import au.edu.unsw.infs3634_lab.api.Crypto;

public class DetailActivity extends AppCompatActivity {

    private TextView mName, mSymbol, mRank, mValue, mChangeHr, mChangeDay, mChangeWeek, mMarket, mVolume;
    private ImageView mSearch;
    private final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String symbol = intent.getStringExtra("symbol");
        Log.d(TAG, "Symbol is " + symbol);

        mName = findViewById(R.id.tvName);
        mSymbol = findViewById(R.id.tvSymbol);
        mRank = findViewById(R.id.tvRank);
        mValue = findViewById(R.id.tvValue);
        mChangeHr = findViewById(R.id.tvChangeHour);
        mChangeDay = findViewById(R.id.tvChangeDay);
        mChangeWeek = findViewById(R.id.tvChangeWeek);
        mMarket = findViewById(R.id.tvMarketCap);
        mVolume = findViewById(R.id.tvVolume);
        mSearch = findViewById(R.id.ivSearch);

        if (intent.hasExtra("symbol")) {
            Crypto crypto = Crypto.findCrypto(symbol);
            mName.setText(crypto.getName());
            mSymbol.setText(crypto.getSymbol());
            mRank.setText(String.valueOf(crypto.getRank()));
            mValue.setText(crypto.getPriceUsd());
            mChangeHr.setText(crypto.getPercentChange1h());
            mChangeDay.setText(crypto.getPercentChange24h());
            mChangeWeek.setText(crypto.getPercentChange7d());
            mMarket.setText(crypto.getMarketCapUsd());
            mVolume.setText(String.valueOf(crypto.getVolume24()));

            mSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchCryptoSearch(symbol);
                }
            });
        }
    }

    protected void launchCryptoSearch(String symbol) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + symbol));
        startActivity(intent);
    }
}