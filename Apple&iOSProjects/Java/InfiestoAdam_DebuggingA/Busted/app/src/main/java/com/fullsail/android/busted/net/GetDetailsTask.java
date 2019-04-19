package com.fullsail.android.busted.net;
//Adam S Infiesto
//Java 1
//GetDetailTask
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.fullsail.android.busted.MainActivity;

public class GetDetailsTask extends AsyncTask<Integer, Void, HashMap<String, String>> {
	
	private static final String API_URL = "https://www.govtrack.us/api/v2/person/";
	private static final String TAG = "member is hitting";
	private static final String NAME = "name";
	private static final String BIRTHDAY = "birthday";
	private static final String GENDER = "gender";
	private static final String TWITTER_ID = "twitter_id";
	private static final String YOUTUBE_ID = "youtubeid";
	private static final String CSPAN_ID = "cspanid";
	
	private final MainActivity mActivity;

	private ProgressDialog proGress;

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		if(NetworkUtils.isConnected(mActivity))
		{
			Log.e(TAG, "onPreExecute: pre hit " );

			proGress = new ProgressDialog(mActivity);
			proGress.setTitle("Downloading");
			proGress.setIndeterminate(true);
			proGress.show();
		}
	}
	
	public GetDetailsTask(MainActivity _activity) {
		mActivity = _activity;
	}
	
	@Override
	protected HashMap<String, String> doInBackground(Integer... _params) {
		
		// Add member ID to the end of the URL
		String data = NetworkUtils.getNetworkData(API_URL + _params[0]);

		HashMap<String, String> retValues = new HashMap<>();
		
		try {

			JSONObject response = new JSONObject(data);

			String name = response.getString("firstname");
			retValues.put(NAME, name);

			String birthday = response.getString("birthday");
			retValues.put(BIRTHDAY, birthday);

			String youtubeID = response.getString("youtubeid");
			retValues.put(YOUTUBE_ID, "" + youtubeID);


			String gender = response.getString("gender_label");
			retValues.put(GENDER, gender);

			String cspanID = response.getString("cspanid");
			retValues.put(CSPAN_ID, "" + cspanID);

			String twitterId = response.getString("twitterid");
			retValues.put(TWITTER_ID, twitterId);
			
			
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		
		return retValues;
	}



	@Override
	protected void onPostExecute(HashMap<String, String> _result)
	{
		super.onPostExecute(_result);
		String name = _result.get(NAME);
		String birthday = _result.get(BIRTHDAY);
		String gender = _result.get(GENDER);
		String twitterId = _result.get(TWITTER_ID);
		String youtubeID = _result.get(YOUTUBE_ID);
		String cspanID = _result.get(CSPAN_ID);

		proGress.dismiss();
		mActivity.populateMemberDetailsScreen(name, birthday, gender, twitterId, youtubeID, cspanID);
	}
}