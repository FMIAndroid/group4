package bg.unisofia.fmi.contactapp;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
	
	public static final String KEY = User.class.getSimpleName() + '2';
	
	private String mUsername;
	private String mPassword;
	private String mPhone;
	private String mEmail;
	
	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		public User createFromParcel(Parcel in) {
		    return new User(in);
		}

		public User[] newArray(int size) {
		    return new User[size];
		}
	};
	
	public User() {
		
	}

	public User(Parcel in) {
		setUsername(in.readString());
		setPassword(in.readString());
		setPhone(in.readString());
		setEmail(in.readString());
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getUsername());
		dest.writeString(getPassword());
		dest.writeString(getPhone());
		dest.writeString(getEmail());
	}
	
	public String getUsername() {
		return mUsername;
	}
	
	public void setUsername(String username) {		
		mUsername = username;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	public String getPassword() {
		return mPassword;
	}

	public void setPassword(String password) {
		mPassword = password;
	}

	public String getPhone() {
		return mPhone;
	}

	public void setPhone(String phone) {
		mPhone = phone;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String email) {
		mEmail = email;
	}
	
}
