package com.anand.appteamtask2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


public class MenuFragment extends Fragment {
    SearchView search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        search = v.findViewById(R.id.searchBar);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Bundle bundle = new Bundle();
                SearchMenuFragment searchMenuFragment = new SearchMenuFragment();
                bundle.putString("Query",s);
                searchMenuFragment.setArguments(bundle);
                getChildFragmentManager().beginTransaction().replace(R.id.frame,searchMenuFragment).commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.isEmpty()){
                    getChildFragmentManager().beginTransaction().replace(R.id.frame,new EmptyFragment()).commit();
                }
                return false;
            }
        });
        search.setOnCloseListener(() -> {
            getChildFragmentManager().beginTransaction().replace(R.id.frame,new EmptyFragment()).commit();
            return false;
        });

        getChildFragmentManager().beginTransaction().replace(R.id.frame,new EmptyFragment()).commit();
        return v;
    }

}