package pl.drupalcampwroclaw.drupalcamp2015;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import pl.drupalcampwroclaw.drupalcamp2015.others.TouchImageView;


public class RoomsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TouchImageView img = new TouchImageView(this);
        img.setImageResource(R.drawable.orbis_rooms);
        img.setMaxZoom(4f);

        setContentView(img);
    }

}
