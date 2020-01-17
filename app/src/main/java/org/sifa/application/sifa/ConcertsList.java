package org.sifa.application.sifa;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;


public class ConcertsList extends Fragment {

    private final String TAG = "shank";

    private static ConcertAdapter adapter;

    private SwipeRefreshLayout refreshLayout;

    public ConcertsList() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Firebase.setAndroidContext(getActivity());
        return inflater.inflate(R.layout.fragment_concerts_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView concertView = (ListView) getView().findViewById(R.id.concert_list_view);
        refreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);
        populateListView(concertView);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Fetcher.instance().refresh(adapter);
                refreshLayout.setRefreshing(false);
            }
        });
    }


    void populateListView(ListView concertView) {
        ArrayList<Concert> concertList = Fetcher.instance().fetchConcerts(adapter);

        adapter = new ConcertAdapter(getActivity(), concertList);
        concertView.setAdapter(adapter);

        concertView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(getActivity(), ConcertDetailsScreen.class);

                        Concert c = (Concert) parent.getItemAtPosition(position);
                        i.putExtra("concert", c);
                        startActivity(i);
                    }
                }
        );
    }




    public static class Fetcher {

        private static Fetcher singleton = new Fetcher();

        public static Fetcher instance() {
            return singleton;
        }

        private Fetcher() {
        }


        private Firebase fRootRef = new Firebase("https://sifadb.firebaseio.com");

        private final String TAG = "shank";

        private static ArrayList<Concert> itemList = new ArrayList<>();


        private void initializeFetch(final ArrayAdapter<Concert> a) {
            //TODO
            Firebase.goOnline();

            itemList = new ArrayList<>();


            fRootRef.addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Concert c = new Concert();
                    for (DataSnapshot d : dataSnapshot.getChildren()) {

                        switch (d.getKey().toLowerCase().trim()) {

                            case "main artists":
                            case "main artist":
                            case "main artists:":
                            case "main artist:":
                                Iterable<DataSnapshot> ma = d.getChildren();
                                for (DataSnapshot snap : ma) {
                                    c.addMainArtist(snap.getKey(), (String) snap.getValue());
                                }
                                break;

                            case "accompanists":
                            case "accompaniment":
                            case "accompanist":
                            case "acompanists":
                                Iterable<DataSnapshot> ac = d.getChildren();
                                for (DataSnapshot snap : ac) {
                                    c.addAccompanist(snap.getKey(), (String) snap.getValue());
                                }
                                break;

                            case "date":
                            case "day":
                                c.setDate(d.getValue().toString());
                                break;

                            case "time":
                                c.setTime(d.getValue().toString());
                                break;

                            case "venue":
                            case "address":
                            case "place":
                            case "theater":
                                c.setVenue(d.getValue().toString());
                                break;

                            case "picture":
                            case "image":
                            case "pic":
                                Iterable<DataSnapshot> pic = d.getChildren();
                                for (DataSnapshot snap : pic) {
                                    c.addPictureSource((String) snap.getValue());
                                }
                                break;

                            case "description":
                            case "desription":
                                c.setDescription(d.getValue().toString());
                                break;

                            case "tickets":
                            case "ticket":
                            case "tickets link":
                            case "ticket link":
                                Iterable<DataSnapshot> tickets = d.getChildren();
                                for (DataSnapshot snap : tickets) {
                                    c.setTicketLink((String) snap.getValue());
                                    c.setTicketHeader(snap.getKey());
                                }
                                break;

                        }
                        Log.i("shank", d.getKey() + " and " + d.getValue());
                    }

                    itemList.add(c);
                    if (a != null) {
                        a.notifyDataSetChanged();
                    } else {
                        Log.i(TAG, "a is null");
                    }
                }







                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }

        public ArrayList<Concert> fetchConcerts(ArrayAdapter<Concert> a) {
            initializeFetch(a);
            return itemList;
        }

        void refresh (ArrayAdapter<Concert> a) {
            adapter.notifyDataSetChanged();
            Firebase.goOffline();
            Firebase.goOnline();
            initializeFetch(a);
        }


    }



}