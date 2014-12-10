package bg.unisofia.fmi.contactapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import bg.unisofia.fmi.contactapp.R;
import bg.unisofia.fmi.contactapp.model.User;

public class UserListAdapter extends BaseAdapter implements Filterable{

	private List<User> users;
	private List<User> allUsers;
	
	private static class ViewHolder {
		private ImageView imageView;
		private TextView textView;
	}
	
	private class UserListFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			final List<User> filteredUsers = new ArrayList<User>(allUsers.size());
			Pattern pattern = Pattern.compile(constraint.toString(), Pattern.CASE_INSENSITIVE);
			for(User user: allUsers) {
				if(pattern.matcher(user.getFullName()).find()) {
					filteredUsers.add(user);
				}
			}
			final FilterResults results = new FilterResults();
			results.values = filteredUsers;
			results.count = filteredUsers.size();
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			users = (List<User>)results.values;
			notifyDataSetChanged();
		}

	}

	public UserListAdapter(List<User> users) {
		this.allUsers = users;
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
			userItemView = (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
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

	@Override
	public Filter getFilter() {
		return new UserListFilter();
	}

}
