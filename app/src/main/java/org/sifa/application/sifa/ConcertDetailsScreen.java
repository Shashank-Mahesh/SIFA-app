package org.sifa.application.sifa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ConcertDetailsScreen extends AppCompatActivity {

    private String TAG = "shank";

    private Concert c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_details);
        toolbar.setTitle("Concert Details");
        setSupportActionBar(toolbar);


        if (getIntent().getExtras().isEmpty()) {
            return;
        }

        c = (Concert) getIntent().getExtras().get("concert");

        TextView main_artists = (TextView) findViewById(R.id.details_main_artists);
        String displayMainArtists = "";
        for (int i = 0 ; i < c.getMainArtists().length ; i++) {
            String text = c.getMainArtists()[i] + " - " + c.getMainArtistsInfo().get(c.getMainArtists()[i]);
            if (i == (c.getMainArtists().length - 1)) { // If it is the last element in the array
                displayMainArtists = displayMainArtists + text;
                main_artists.setText(displayMainArtists);
            } else { // if it is not the last element of the array
                displayMainArtists = displayMainArtists + text + "\n";
            }
        }

        TextView details_accompanists = (TextView) findViewById(R.id.details_accompanists);
        String displayAccompanists = "";
        for (int i = 0 ; i < c.getAccompanists().length ; i++) {
            String text = c.getAccompanists()[i] + " - " + c.getAccompanistsInfo().get(c.getAccompanists()[i]);
            if (i == (c.getAccompanists().length - 1)) { // If it is the last element in the array
                displayAccompanists = displayAccompanists + text;
                details_accompanists.setText(displayAccompanists);
            } else { // if it is not the last element of the array
                displayAccompanists = displayAccompanists + text + "\n";
            }
        }
        if (c.getPictureSource().size() >= 1) {
            ImageView details_picture = (ImageView) findViewById(R.id.details_picture_1);
            new ImageLoadTask(c.getPictureSource().get(0), details_picture).execute();
            if (c.getPictureSource().size() >= 2) {
                ImageView details_picture2 = (ImageView) findViewById(R.id.details_picture_2);
                new ImageLoadTask(c.getPictureSource().get(1), details_picture2).execute();
                if (c.getPictureSource().size() >= 3) {
                    ImageView details_picture3 = (ImageView) findViewById(R.id.details_picture_3);
                    new ImageLoadTask(c.getPictureSource().get(2), details_picture3).execute();
                    if (c.getPictureSource().size() >= 4) {
                        ImageView details_picture4 = (ImageView) findViewById(R.id.details_picture_4);
                        new ImageLoadTask(c.getPictureSource().get(3), details_picture4).execute();
                    }
                }
            }
        }

        TextView details_date = (TextView) findViewById(R.id.details_date);
        details_date.setText(c.getDate());

        TextView details_time = (TextView) findViewById(R.id.details_time);
        details_time.setText(c.getTime());

        TextView details_venue = (TextView) findViewById(R.id.details_venue);
        details_venue.setText(c.getVenue());

        TextView details_description = (TextView) findViewById(R.id.details_description);
        details_description.setText(c.getDescription());

    }

    public void processClick(View view) {

        Intent intent = null;

        if (view.getId() == R.id.google_maps_button) {
            intent = new Intent(Intent.ACTION_VIEW);
            String encoded = Uri.encode(c.getVenue());
            Log.i(TAG, encoded);
            intent.setData(Uri.parse("geo:0,0?q=" + encoded));
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        } else if (view.getId() == R.id.tickets_button) {
            startActivity(new Intent(this, PurchaseTickets.class).putExtra("link", c.getTicketLink()));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.toolbar_details) {
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

}
