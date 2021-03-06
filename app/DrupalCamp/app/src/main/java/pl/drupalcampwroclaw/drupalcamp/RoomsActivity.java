package pl.drupalcampwroclaw.drupalcamp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import pl.drupalcampwroclaw.drupalcamp.others.TouchImageView;


public class RoomsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TouchImageView img = new TouchImageView(this);
        img.setImageResource(R.drawable.orbis_rooms);
        img.setMaxZoom(4f);

        setContentView(img);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Back button.
                this.finish();
                return (true);
        }

        return super.onOptionsItemSelected(item);
    }

}
