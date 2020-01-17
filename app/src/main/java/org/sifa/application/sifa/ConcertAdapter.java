package org.sifa.application.sifa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shashank on 6/6/16.
 * @author Shashank
 */

public class ConcertAdapter
        extends ArrayAdapter<Concert> {

    private final String TAG = "shank";

    public ConcertAdapter(Context context, ArrayList<Concert> concerts) {
        super(context, R.layout.fragment_concerts_list, concerts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lf = LayoutInflater.from(getContext());
        Concert c = getItem(position);

        View customView = null;

        switch (c.getPictureSource().size()) {
            case 1:
                customView = lf.inflate(R.layout.list_concert_row_1, parent, false);
                break;

            case 2:
                customView = lf.inflate(R.layout.list_concert_row_2, parent, false);
                break;

            case 3:
                customView = lf.inflate(R.layout.list_concert_row_3, parent, false);
                break;

            case 4:
                customView = lf.inflate(R.layout.list_concert_row_4, parent, false);
                break;
        }



        if (c.getPictureSource().size() >= 1) {
            ImageView picture1 = (ImageView) customView.findViewById(R.id.picture_1);
            new ImageLoadTask(c.getPictureSource().get(0), picture1).execute();
            if (c.getPictureSource().size() >= 2) {
                ImageView picture2 = (ImageView) customView.findViewById(R.id.picture_2);
                new ImageLoadTask(c.getPictureSource().get(1), picture2).execute();
                if (c.getPictureSource().size() >= 3) {
                    ImageView picture3 = (ImageView) customView.findViewById(R.id.picture_3);
                    new ImageLoadTask(c.getPictureSource().get(2), picture3).execute();
                    if (c.getPictureSource().size() == 4) {
                        ImageView picture4 = (ImageView) customView.findViewById(R.id.picture_4);
                        new ImageLoadTask(c.getPictureSource().get(3), picture4).execute();
                    }
                }
            }
        }


        TextView main_artist = (TextView) customView.findViewById(R.id.main_artist_1);
        TextView date = (TextView) customView.findViewById(R.id.date_1);

        String displayMainArtists = "";
        for (int i = 0 ; i < c.getMainArtists().length ; i++) {
            String text = c.getMainArtists()[i] + " - " + c.getMainArtistsInfo().get(c.getMainArtists()[i]);
            if (i == (c.getMainArtists().length - 1)) { // If it is the last element in the array
                displayMainArtists = displayMainArtists + text;
                main_artist.setText(displayMainArtists);
            } else { // if it is not the last element of the array
                displayMainArtists = displayMainArtists + text + "\n";
            }
        }

        date.setText(c.getDate());

        return customView;
    }

}
