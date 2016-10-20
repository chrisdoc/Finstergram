package xyz.husten.finstergram.ui.findstergramdetail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import xyz.husten.finstergram.model.Image;
import xyz.husten.finstergram.model.ImageSet;
import xyz.husten.finstergram.model.Location;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.repository.ResultsDataSource;
import xyz.husten.finstergram.repository.ResultsRepository;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class FinstergramDetailPresenterTest {

  private Result result;
  private FinstergramDetailPresenter presenter;

  @Mock
  private ResultsRepository resultsRepository;

  @Mock
  private FinstergramDetailContract.View view;

  @Captor
  private ArgumentCaptor<ResultsDataSource.GetResultCallback> getResultCallbackCaptor;

  @Before public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);

    result = new Result();
    result.location = new Location(122121, 12.32, 41.22, "TestDroid");
    result.link = "https://www.husten.xyz";
    result.images = new ImageSet();
    result.images.standardResolution = new Image("https://www.husten.xyz/image.jpg", 300, 300);
    result.id = "123";

    presenter = new FinstergramDetailPresenter(view, resultsRepository, result.id);
  }

  @Test public void setupListeners() throws Exception {
    presenter.setupListeners();
    verify(view).setPresenter(presenter);
  }

  @Test public void start() throws Exception {
    presenter.start();

    verify(resultsRepository).loadResult(eq(result.id), getResultCallbackCaptor.capture());
    getResultCallbackCaptor.getValue().onResultLoaded(result); // Trigger callback
    verify(view).showResult(result);
  }

  @Test public void imageClicked() throws Exception {
    presenter.bindResult(result);
    presenter.imageClicked();
    verify(view).openImage(result.images.standardResolution.url);
  }

  @Test public void directions() throws Exception {
    presenter.bindResult(result);
    presenter.directions();
    verify(view).showDirectionUri("google.navigation:q=12.320000,41.220000");
  }

  @Test public void map() throws Exception {
    presenter.bindResult(result);
    presenter.map();
    verify(view).showMapUri("geo:0,0?q=12.320000,41.220000(TestDroid)");
  }

  @Test public void share() throws Exception {
    presenter.bindResult(result);
    presenter.share();
    verify(view).shareLink(result.link);
  }
}