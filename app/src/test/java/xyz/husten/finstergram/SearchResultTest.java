package xyz.husten.finstergram;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.io.File;
import okio.BufferedSource;
import okio.Okio;
import org.junit.Before;
import org.junit.Test;
import xyz.husten.finstergram.model.Location;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.model.SearchResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SearchResultTest {

  private SearchResult searchResult;

  @Before
  public void setUp() throws Exception {
    File searchResults = Utils.getFileFromPath(this, "searchResult.json");
    BufferedSource resultsSource = Okio.buffer(Okio.source(searchResults));
    assertNotNull(resultsSource);
    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<SearchResult> jsonAdapter = moshi.adapter(SearchResult.class);
    searchResult = jsonAdapter.fromJson(resultsSource);

  }

  @Test
  public void resultsLength() {
    assertEquals(8, searchResult.results.size());
  }

  @Test
  public void resultsFirstElementType() {
    Result result = searchResult.results.get(0);
    assertEquals("image", result.type);
  }

  @Test
  public void resultsFirstElementUser() {
    Result result = searchResult.results.get(0);
    assertEquals("kieslichc", result.user.username);
    assertEquals("Christoph Kieslich", result.user.fullName);
  }

  @Test
  public void resultsFirstElementLikes() {
    Result result = searchResult.results.get(0);
    assertEquals(20, result.likes.count);
  }

  @Test
  public void resultsFirstElementId() {
    Result result = searchResult.results.get(0);
    assertEquals("1363631508120490413_8979339", result.id);
  }

  @Test
  public void resultsFirstElementLocation() {
    Result result = searchResult.results.get(0);
    Location location = result.location;
    assertEquals("Alexanderplatz Berlin", location.name);
    assertEquals(213645520, location.id);
    assertEquals(52.52128, location.latitude, 0.01);
    assertEquals(13.4126999, location.longitude, 0.01);
  }

  @Test
  public void resultsFirstElementComments() {
    Result result = searchResult.results.get(0);
    assertEquals(0, result.comments.count);
  }

  @Test
  public void resultsFirstElementLowResolutionImage() {
    Result result = searchResult.results.get(0);
    assertEquals(320, result.images.lowResolution.width);
    assertEquals(239, result.images.lowResolution.height);
    assertEquals("https://scontent.cdninstagram.com/t51.2885-15/s320x320/e35/14676648_316932122018857_5087099633503567872_n.jpg?ig_cache_key=MTM2MzYzMTUwODEyMDQ5MDQxMw%3D%3D.2", result.images.lowResolution.url);
  }

  @Test
  public void resultsFirstElementThumbnailResolutionImage() {
    Result result = searchResult.results.get(0);
    assertEquals(150, result.images.thumbnail.width);
    assertEquals(150, result.images.thumbnail.height);
    assertEquals("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/c135.0.809.809/14676648_316932122018857_5087099633503567872_n.jpg?ig_cache_key=MTM2MzYzMTUwODEyMDQ5MDQxMw%3D%3D.2.c", result.images.thumbnail.url);
  }

  @Test
  public void resultsFirstElementStandardsResolutionImage() {
    Result result = searchResult.results.get(0);
    assertEquals(640, result.images.standardResolution.width);
    assertEquals(479, result.images.standardResolution.height);
    assertEquals("https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/14676648_316932122018857_5087099633503567872_n.jpg?ig_cache_key=MTM2MzYzMTUwODEyMDQ5MDQxMw%3D%3D.2", result.images.standardResolution.url);
  }

}
