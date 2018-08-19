package com.jaisel.flames;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText mName1, mName2;
    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Flames");
                intent.putExtra(Intent.EXTRA_TEXT, "Check this out\n" +
                        "http://fundo.ezyro.com/flames/" + mName1.getText() + "/" + mName2.getText());
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });

        findViewById(R.id.fab_web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://fundo.ezyro.com/flames/" + mName1.getText() + "/" + mName2.getText()));
                startActivity(intent);
            }
        });

        mResult = findViewById(R.id.text);
        mName1 = findViewById(R.id.edit1);
        mName2 = findViewById(R.id.edit2);
        Button ok = findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String name1 = mName1.getText().toString().toLowerCase();
                String name2 = mName2.getText().toString().toLowerCase();

                if (name1.equals("") && name2.equals("") ||
                        name1.equals("") && name2.equals("")) {
                    mResult.setText("");
                    return;
                }

                String res = "FLAMES";
                switch (flames(name1, name2)) {
                    case "f":
                        res = "FRIENDS";
                        break;
                    case "l":
                        res = "LOVE";
                        break;
                    case "a":
                        res = "AFFECTION";
                        break;
                    case "m":
                        res = "MARRIAGE";
                        break;
                    case "e":
                        res = "ENEMY";
                        break;
                    case "s":
                        res = "SIBLING";
                        break;
                }
                mResult.setText(res);
            }
        });
        Button mClear = findViewById(R.id.clear);
        mClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View p1) {
                mName1.setText("");
                mName2.setText("");
                mResult.setText("");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent i = new Intent(getApplicationContext(), AboutActivity.class);
                startActivityForResult(i, 0);
                return true;
            case R.id.exit:
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private String flames(String name1, String name2) {
        int i, j, n = 0, len1, len2, tot;

        char[] array = name1.toCharArray();
        Arrays.sort(array);
        name1 = new String(array);
        name1 = name1.trim();
        Log.d("Flames ", name1);

        array = name2.toCharArray();
        Arrays.sort(array);
        name2 = new String(array);
        name2 = name2.trim();
        Log.d("Flames ", name2);

        if (name1.length() < name2.length()) {
            String t = name2;
            name2 = name1;
            name1 = t;
        }

        len1 = name1.length();
        len2 = name2.length();
        tot = name1.length() + name2.length();
        for (i = 0; i < len1; i++) {
            for (j = n; j < len2; j++) {
                if (name1.charAt(i) == name2.charAt(j)) {
                    tot -= 2;
                    n = ++j;
                    break;
                }
            }
        }
        Log.d("Flames ", "" + tot);
        if (tot <= 0) return "";
        return rem(tot);
    }

    private String rem(int n) {
        String result = "flames";
        String t;
        int n2;
        while (result.length() > 1) {
            n2 = n % result.length();
            n2 = (n2 == 0) ? result.length() : n2;
            t = result.substring(n2);
            result = result.substring(0, n2 - 1);
            result = t + result;
        }
        return result;
    }
}
