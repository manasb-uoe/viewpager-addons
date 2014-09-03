package com.manas.viewpageraddons.sample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Manas on 8/31/2014.
 */
public class TestFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        TextView colorTextView = (TextView) view.findViewById(R.id.colorTextView);
        int color = getArguments().getInt("color");
        colorTextView.setText("Color: #" + Integer.toHexString(color));
        colorTextView.setTextColor(color);

        TextView pageTextView = (TextView) view.findViewById(R.id.pageTextView);
        pageTextView.setText("Page: " + getArguments().getInt("num"));

        return view;
    }

    public static TestFragment newInstance(int num, int color) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("num", num);
        bundle.putInt("color", color);
        fragment.setArguments(bundle);
        return fragment;
    }
}
