package xyz.husten.finstergram.findstergramdetail;

import org.junit.Before;
import org.junit.Test;
import xyz.husten.finstergram.model.Image;
import xyz.husten.finstergram.model.ImageSet;
import xyz.husten.finstergram.model.Location;
import xyz.husten.finstergram.model.Result;

import static org.mockito.Mockito.*;

public class FinstergramDetailPresenterTest {

  private FinstergramDetailContract.View view;
  private Result result;
  private FinstergramDetailPresenter presenter;

  @Before public void setUp() throws Exception {
    view = mock(FinstergramDetailContract.View.class);

    result = new Result();
    result.location = new Location(122121, 12.32, 41.22, "TestDroid");
    result.link = "https://www.husten.xyz";
    result.images = new ImageSet();
    result.images.standardResolution = new Image("https://www.husten.xyz/image.jpg", 300, 300);

    presenter = new FinstergramDetailPresenter(view, result);
  }

  @Test public void setupListeners() throws Exception {
    presenter.setupListeners();
    verify(view).setPresenter(presenter);
  }

  @Test public void start() throws Exception {
    presenter.start();
    verify(view).showResult(result);
  }

  @Test public void imageClicked() throws Exception {
    presenter.imageClicked();
    verify(view).openImage(result.images.standardResolution.url);
  }

  @Test public void directions() throws Exception {
    presenter.directions();
    verify(view).showDirectionUri("google.navigation:q=12.320000,41.220000");
  }

  @Test public void map() throws Exception {
    presenter.map();
    verify(view).showMapUri("geo:0,0?q=12.320000,41.220000(TestDroid)");
  }

  @Test public void share() throws Exception {
    presenter.share();
    verify(view).shareLink(result.link);
  }
}