package xyz.husten.finstergram.model;

import com.squareup.moshi.Json;
import java.util.List;

public class SearchResult {
  @Json(name = "data")
  public List<Result> results;
}
