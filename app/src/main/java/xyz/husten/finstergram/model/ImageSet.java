package xyz.husten.finstergram.model;

import com.squareup.moshi.Json;

public class ImageSet {
  @Json(name = "low_resolution")
  public Image lowResoultion;
  public Image thumbnail;
  @Json(name = "standard_resolution")
  public Image standardResolution;
}
