package xyz.husten.finstergram.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable{
  public double latitude;
  public double longitude;
  public int id;
  public String name;

  public Location(int id, double latitude, double longitude, String name) {
    this.id = id;
    this.latitude = latitude;
    this.longitude = longitude;
    this.name = name;
  }

  protected Location(Parcel in) {
    latitude = in.readDouble();
    longitude = in.readDouble();
    id = in.readInt();
    name = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeDouble(latitude);
    dest.writeDouble(longitude);
    dest.writeInt(id);
    dest.writeString(name);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<Location> CREATOR = new Creator<Location>() {
    @Override public Location createFromParcel(Parcel in) {
      return new Location(in);
    }

    @Override public Location[] newArray(int size) {
      return new Location[size];
    }
  };
}
