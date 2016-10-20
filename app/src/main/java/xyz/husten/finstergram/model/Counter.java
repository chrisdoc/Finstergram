package xyz.husten.finstergram.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Counter implements Parcelable {
  public int count;

  protected Counter(Parcel in) {
    count = in.readInt();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(count);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<Counter> CREATOR = new Creator<Counter>() {
    @Override public Counter createFromParcel(Parcel in) {
      return new Counter(in);
    }

    @Override public Counter[] newArray(int size) {
      return new Counter[size];
    }
  };
}
