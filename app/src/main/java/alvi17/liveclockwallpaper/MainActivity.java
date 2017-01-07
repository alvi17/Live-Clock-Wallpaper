package alvi17.liveclockwallpaper;

import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    FrameLayout fm;
    AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm=(FrameLayout)findViewById(R.id.activity_main);
        adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-6508526601344465/2803073232");
        adView.setAdSize(AdSize.BANNER);
        LinearLayout layout = new LinearLayout(this);
        layout.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        layout.addView(adView);
        fm.addView(layout);

    }

    public void setClockLw(View v) {

        int id=v.getId();

        switch (id)
        {
            case R.id.clock1:
                Globals.seleted_image=1;
                break;
            case R.id.clock2:
                Globals.seleted_image=2;
                break;
            case R.id.clock3:
                Globals.seleted_image=3;
                break;
            case R.id.clock4:
                Globals.seleted_image=4;
                break;
            case R.id.clock5:
                Globals.seleted_image=5;
                break;
            case R.id.clock6:
                Globals.seleted_image=6;
                break;
            case R.id.clock7:
                Globals.seleted_image=7;
                break;
            case R.id.clock8:
                Globals.seleted_image=8;
                break;
        }
        Log.e("MainActivity","Id: "+id+" "+Globals.seleted_image);


        Intent intent = new Intent(
                WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                new ComponentName(this, ClockWallpaperService.class));
        startActivity(intent);
    }



}
