//Adam S Infiesto
//Java 1
//GetMemberTask

package com.fullsail.android.busted.net;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.fullsail.android.busted.MainActivity;
import com.fullsail.android.busted.object.Member;


public class GetMembersTask extends AsyncTask<Void, Void, ArrayList<Member>> {
	
	private static final String API_URL = "https://www.govtrack.us/api/v2/role?current=true";

	private static final String TAG = "member is hitting";

	private final MainActivity mActivity;

	private ProgressDialog proGress;


	public GetMembersTask(MainActivity _activity) {
		mActivity = _activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

            Log.e(TAG, "onPreExecute: pre hit " );
            proGress = new ProgressDialog(mActivity);
            proGress.setTitle("Downloading");
            proGress.setIndeterminate(true);
            proGress.show();

	}

	@Override
	protected ArrayList<Member> doInBackground(Void... _params) {

		String data = NetworkUtils.getNetworkData(API_URL);
        Log.i(TAG, "doInBackground: dont work");
       if(data!= null)
       {
		   try {
			   Log.i(TAG, "doInBackground: url works");
			   JSONObject response = new JSONObject(data);

			   JSONArray membersJson = response.getJSONArray("objects");

			   ArrayList<Member> members = new ArrayList<>();

			   for(int i = 0; i < membersJson.length(); i++)
			   {
				   JSONObject obj = membersJson.getJSONObject(i);
				   JSONObject person = obj.getJSONObject("person");

				   int id = getIdFromLink(person.getString("link"));
				   String name = person.getString("name");
				   String party = obj.getString("party");

				   members.add(new Member(id, name, party));
			   }

			   // Update the UI
			   //mActivity.showMembersListScreen(members);
			   return members;

		   } catch(JSONException e) {
			   e.printStackTrace();
		   }
	   }

		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<Member> _result) {
		super.onPostExecute(_result);
		proGress.dismiss();
		mActivity.showMembersListScreen(_result);

	}

	private int getIdFromLink(String _link)
    {
		int index = _link.lastIndexOf('/');
		if(index > -1 && (index+1) < _link.length()) {
			try {
				return Integer.parseInt(_link.substring(index+1));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		Log.e("ERROR", "Unable to find ID in string \"" + _link + "\".");
		return 0;
	}

}