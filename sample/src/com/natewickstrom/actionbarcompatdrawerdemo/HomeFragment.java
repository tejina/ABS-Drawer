package com.natewickstrom.actionbarcompatdrawerdemo;

import com.natewickstrom.actionbarcompatdrawerdemo.listener.MyListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeFragment extends Fragment {
	
	public static final String NUM = "my_number"; 

	private int mInt = 0;
	private TextView mTextView;
	public HomeFragment() { }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();

		if (bundle != null) 
			mInt = bundle.getInt(NUM);
		else if (savedInstanceState != null) 
			mInt = savedInstanceState.getInt(NUM);		
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		RelativeLayout v = (RelativeLayout) inflater.inflate(R.layout.fragment_home, container, false);
		mTextView = (TextView) v.findViewById(R.id.number);
		mTextView.setText("Number = " + mInt);
		Button button = (Button) v.findViewById(R.id.button_plus);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				++mInt;
				mTextView.setText("Number = " + mInt);
			}
			
		});
		
		return v;
	}
	
	@Override
	public void onStart(){
		super.onStart();
		String text = "Number = " + ((MyListener) getActivity()).getSomeRandomInfoInIntegerForm();		
		mTextView.setText(text);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(NUM, mInt);
	}
	
	public int getInt(){
		return mInt;
	}

}
