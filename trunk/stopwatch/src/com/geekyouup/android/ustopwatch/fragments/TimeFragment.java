package com.geekyouup.android.ustopwatch.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.geekyouup.android.ustopwatch.R;
import com.geekyouup.android.ustopwatch.TimeUtils;

public class TimeFragment extends Fragment implements OnClickListener{

	private TextView mCounter;
	private LapTimeRecorder mLapTimer;
	private TextView mSaveText;
	private double mCurrentTimeMillis=0;
	private int mMode = StopwatchFragment.MODE_STOPWATCH;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View timeView = inflater.inflate(R.layout.time_fragment, container, false);
		mCounter = (TextView) timeView.findViewById(R.id.counter_digits);
		if(mCounter!=null)mCounter.setOnClickListener(this);
		
		mSaveText = (TextView) timeView.findViewById(R.id.timefrag_savetext);
		
		mLapTimer = LapTimeRecorder.getInstance();
		
		timeView.setOnClickListener(this);
		
		return timeView;
	}

	public void setTime(double time) {
		mCurrentTimeMillis = time;
		if(mCounter!=null) mCounter.setText(TimeUtils.createTimeString(time));
	}
	
	public void resetTime()
	{
		if(mCounter!=null)mCounter.setText(TimeUtils.createTimeString(0));
	}

	@Override
	public void onClick(View v) {
		if(mMode == StopwatchFragment.MODE_STOPWATCH && mLapTimer!=null)
		{
			mLapTimer.recordLapTime(mCurrentTimeMillis, getActivity());
		}
	}
	
	public void setMode(int mode)
	{
		mMode = mode;
		mSaveText.setVisibility((mode == StopwatchFragment.MODE_STOPWATCH)?View.VISIBLE:View.INVISIBLE);
	}
}
