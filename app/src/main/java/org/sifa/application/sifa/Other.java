package org.sifa.application.sifa;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */

public class Other extends Fragment {

    private String[] subjects = new String[]{"SIFA 2016", "Our Facebook Page"};

    //TODO need a material logo for SIFA app
    private int[] vectors = new int[] {R.drawable.app_logo, R.drawable.facebook_image};

    private Intent[] intents = new Intent[vectors.length];


    public Other() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        createIntents();

        ListView listView = (ListView) getView().findViewById(R.id.other_list_view);
        listView.setAdapter(new CustomViewAdapter(this.subjects, this.vectors));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(intents[position]);
            }
        });
    }

    private void createIntents() {
        intents[0] = new Intent(getActivity(), About.class);
        intents[1] = getOpenFacebookIntent(getActivity());
    }






    public class CustomViewAdapter
            extends ArrayAdapter<String>{

        int[] vectors = null;

        public CustomViewAdapter(String[] subjects, @DrawableRes int[] vectors) {
            super(getActivity(), R.layout.list_other_row, subjects);
            this.vectors = vectors;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater lf = LayoutInflater.from(getContext());
            String subject = getItem(position);

            View customView  = lf.inflate(R.layout.list_other_row, parent, false);
            ImageView vector = (ImageView) customView.findViewById(R.id.vector_pic);
            TextView subject_text = (TextView) customView.findViewById(R.id.subject_text);

            subject_text.setText(subject);
            vector.setImageResource(vectors[position]);

            return customView;
        }
    }

    private static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("org.sifa.application.sifa", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/196850076998755"));
        } catch (Exception ignored) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/carnaticmusicbayarea/?fref=ts"));
        }
    }

}
