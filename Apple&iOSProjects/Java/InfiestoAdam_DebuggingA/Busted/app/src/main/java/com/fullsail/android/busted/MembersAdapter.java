//Adam S Infiesto
//Java 1
//MembersAdapter
package com.fullsail.android.busted;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fullsail.android.busted.object.Member;

class MembersAdapter extends BaseAdapter {
	
	private static final long ID_CONSTANT = 0x010101010L;
	
	private final Context mContext;
	private final ArrayList<Member> mMembers;
	
	public MembersAdapter(Context _context, ArrayList<Member> _members) {
		mContext = _context;
		mMembers = _members;
	}

	@Override
	public int getCount() {
		if(mMembers != null)
		{
			return mMembers.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int _position) {
		if(mMembers != null && _position >= 0 && _position < mMembers.size())
		{
			return mMembers.get(_position);
		}
		return null;
	}

	@Override
	public long getItemId(int _position) {
		if(mMembers != null && _position >= 0 && _position < mMembers.size())
		{
			return ID_CONSTANT + _position;
		}
		else
			{
				return 0;
			}
	}

	@Override
	public View getView(int _position, View _convertView, ViewGroup _parent) {


		if(_convertView == null) {
			_convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, _parent, false);

		}

		Member member = (Member)getItem(_position);
		TextView text1 = (TextView) _convertView.findViewById(android.R.id.text1);
		text1.setText(member.getName());
		TextView text2 = (TextView) _convertView.findViewById(android.R.id.text2);
	    (text2).setText(member.getParty());

		return _convertView;
	}
	
}