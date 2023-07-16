package com.example.myapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class oldbookAnswer extends Fragment {

    TextView textView;

    public oldbookAnswer() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_oldbook_answer, container, false);

        textView = view.findViewById(R.id.answertxt);
        String answertext = getActivity().getIntent().getStringExtra("answer");
        textView.setText(answertext);

        return view;
    }
}

