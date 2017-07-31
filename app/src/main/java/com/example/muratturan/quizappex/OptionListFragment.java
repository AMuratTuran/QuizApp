package com.example.muratturan.quizappex;


import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class OptionListFragment extends ListFragment {

    static interface OptionListListener {
        void itemClicked(int position);
    }

    private OptionListListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String[] options = {"Multiplication", "Sum", "Capitals"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext()
                , android.R.layout.simple_list_item_1, options);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (OptionListListener) activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            listener.itemClicked(position);
        }
    }
}
