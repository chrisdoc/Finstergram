package xyz.husten.finstergram.model;

import com.squareup.moshi.Json;

public class User {
  public String username;
  @Json(name = "full_name")
  public String fullName;
}
