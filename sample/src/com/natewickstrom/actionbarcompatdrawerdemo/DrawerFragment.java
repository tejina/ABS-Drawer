package com.natewickstrom.actionbarcompatdrawerdemo;

import com.natewickstrom.actionbarcompatdrawerdemo.listener.MyListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DrawerFragment extends Fragment {
	
	public static final String TEXT = "my_text"; 

	private String mText = "Empty";
	private TextView mTextView;
	public DrawerFragment() { }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();

		if (bundle != null) 
			mText = bundle.getString(TEXT);

		if (savedInstanceState != null)
			mText = savedInstanceState.getString(TEXT);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		RelativeLayout v = (RelativeLayout) inflater.inflate(R.layout.fragment_text, container, false);
		mTextView = (TextView) v.findViewById(R.id.fragment_text);
		return v;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(TEXT, mText);
	}
	
	public void refresh(){
		String text = "Number = " + ((MyListener) getActivity()).getSomeRandomInfoInIntegerForm();		
		mTextView.setText(text);
	}


}
