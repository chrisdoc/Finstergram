package xyz.husten.finstergram.model;

import com.squareup.moshi.Json;

public class SingleResult {
  @Json(name = "data")
  public Result result;
}
