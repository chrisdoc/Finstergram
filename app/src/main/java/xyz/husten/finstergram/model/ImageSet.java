package xyz.husten.finstergram.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.squareup.moshi.Json;

public class ImageSet implements Parcelable {
  @Json(name = "low_resolution")
  public Image lowResoultion;
  public Image thumbnail;
  @Json(name = "standard_resolution")
  public Image standardResolution;

  public ImageSet() {

  }

  protected ImageSet(Parcel in) {
    lowResoultion = in.readParcelable(Image.class.getClassLoader());
    thumbnail = in.readParcelable(Image.class.getClassLoader());
    standardResolution = in.readParcelable(Image.class.getClassLoader());
  }

  public static final Creator<ImageSet> CREATOR = new Creator<ImageSet>() {
    @Override public ImageSet createFromParcel(Parcel in) {
      return new ImageSet(in);
    }

    @Override public ImageSet[] newArray(int size) {
      return new ImageSet[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(lowResoultion, flags);
    dest.writeParcelable(thumbnail, flags);
    dest.writeParcelable(standardResolution, flags);
  }
}
