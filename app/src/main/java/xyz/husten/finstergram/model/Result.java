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

  protected Result(Parcel in) {
    id = in.readString();
    type = in.readString();
    likes = in.readParcelable(Counter.class.getClassLoader());
    comments = in.readParcelable(Counter.class.getClassLoader());
    user = in.readParcelable(User.class.getClassLoader());
    images = in.readParcelable(ImageSet.class.getClassLoader());
  }

  public static final Creator<Result> CREATOR = new Creator<Result>() {
    @Override public Result createFromParcel(Parcel in) {
      return new Result(in);
    }

    @Override public Result[] newArray(int size) {
      return new Result[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(type);
    dest.writeParcelable(likes, flags);
    dest.writeParcelable(comments, flags);
    dest.writeParcelable(user, flags);
    dest.writeParcelable(images, flags);
  }
}
