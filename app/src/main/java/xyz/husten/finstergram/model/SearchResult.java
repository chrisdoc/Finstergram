package xyz.husten.finstergram.model;

import com.squareup.moshi.Json;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchResult {
  @Json(name = "data")
  public List<Result> results;

  public SearchResult(Collection<Result> values) {
    results = new ArrayList<>(values);
  }
}
