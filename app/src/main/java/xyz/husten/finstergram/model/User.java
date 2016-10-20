package xyz.husten.finstergram.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.squareup.moshi.Json;

public class User implements Parcelable {
  public String username;
  @Json(name = "full_name")
  public String fullName;

  protected User(Parcel in) {
    username = in.readString();
    fullName = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(username);
    dest.writeString(fullName);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<User> CREATOR = new Creator<User>() {
    @Override public User createFromParcel(Parcel in) {
      return new User(in);
    }

    @Override public User[] newArray(int size) {
      return new User[size];
    }
  };
}
