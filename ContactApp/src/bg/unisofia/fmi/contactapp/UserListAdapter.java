package bg.unisofia.fmi.contactapp;

import java.util.List;

import bg.unisofia.fmi.contactapp.model.User;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserListAdapter extends BaseAdapter{

	private List<User> users;
	
	private static class ViewHolder {
		private ImageView imageView;
		private TextView textView;
	}

	public UserListAdapter(List<User> users) {
		this.users = users;
	}
	
	@Override
	public int getCount() {
		return users.size();
	}

	@Override
	public User getItem(int position) {
		return users.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout userItemView = null;
		if(convertView == null) {
			userItemView = (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, null);
		} else {
			userItemView = (LinearLayout) convertView;
		}
		ViewHolder viewHolder = (ViewHolder) userItemView.getTag();
		if(viewHolder == null) {
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) userItemView.findViewById(R.id.image);
			viewHolder.textView = (TextView) userItemView.findViewById(R.id.fullName);
			userItemView.setTag(viewHolder);
		}
		User user = getItem(position);
		viewHolder.imageView.setImageResource(user.getImageResource());
		viewHolder.textView.setText(user.getFullName());
		return userItemView;
	}

}
