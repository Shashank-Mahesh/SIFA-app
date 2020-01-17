package org.sifa.application.sifa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.sifa.application.sifa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Feedback extends Fragment {

    EditText subject = null, email = null, comments = null;
    Button button;


    public Feedback() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        subject = (EditText) getView().findViewById(R.id.subject_feedback_editText);
        email = (EditText) getView().findViewById(R.id.email_feedback_editText);
        comments = (EditText) getView().findViewById(R.id.comment_feedback_editText);
        button = (Button) getView().findViewById(R.id.submit_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Thank you for your feedback!", Toast.LENGTH_LONG).show();
                subject.setText("");
                email.setText("");
                comments.setText("");

                //ToDo - set action after submitted
            }
        });
    }

}
