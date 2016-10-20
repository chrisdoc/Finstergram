package xyz.husten.finstergram.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Result implements Parcelable {
  public String id;
  public String type;
  public Counter likes;
  public Counter comments;
  public User user;
  public ImageSet images;
  public Location location;
  public String link;

  public Result() {

  }

  protected Result(Parcel in) {
    id = in.readString();
    type = in.readString();
    likes = in.readParcelable(Counter.class.getClassLoader());
    comments = in.readParcelable(Counter.class.getClassLoader());
    user = in.readParcelable(User.class.getClassLoader());
    images = in.readParcelable(ImageSet.class.getClassLoader());
    location = in.readParcelable(Location.class.getClassLoader());
    link = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(type);
    dest.writeParcelable(likes, flags);
    dest.writeParcelable(comments, flags);
    dest.writeParcelable(user, flags);
    dest.writeParcelable(images, flags);
    dest.writeParcelable(location, flags);
    dest.writeString(link);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<Result> CREATOR = new Creator<Result>() {
    @Override public Result createFromParcel(Parcel in) {
      return new Result(in);
    }

    @Override public Result[] newArray(int size) {
      return new Result[size];
    }
  };
}
