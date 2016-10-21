package xyz.husten.finstergram.ui.finstergrams;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import xyz.husten.finstergram.model.LatitudeLongitude;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.model.SearchResult;
import xyz.husten.finstergram.repository.ResultsDataSource;
import xyz.husten.finstergram.repository.ResultsRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FinstergramsPresenterTest {
  @Mock
  private FinstergramsContract.View view;
  @Mock
  private ResultsRepository resultsRepository;
  private FinstergramsPresenter presenter;
  private SearchResult result;

  @Captor
  private ArgumentCaptor<ResultsDataSource.LoadResultsCallback> getLoadResultsCallbackCaptor;
  private LatitudeLongitude latitudeLongitude;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    latitudeLongitude = new LatitudeLongitude(52.423, 12.43);
    List<Result> results = new ArrayList<>();
    results.add(createResult("123"));
    results.add(createResult("143"));
    results.add(createResult("153"));
    result = new SearchResult(results);

    presenter = new FinstergramsPresenter(view, resultsRepository, latitudeLongitude);
  }

  private Result createResult(String id) {
    Result result = new Result();
    result.id = id;
    return result;
  }

  @Test public void callLoadResultsWithShowLoadingUI() {
    presenter.loadResults(true, true);
    verify(view).setLoadingIndicator(true);
  }

  @Test public void callLoadResultsWithOutShowLoadingUI() {
    presenter.loadResults(false, true);
    verify(view, never()).setLoadingIndicator(anyBoolean());
  }

  @Test public void verifyViewShowResults() {
    presenter.loadResults(true, true);
    verify(resultsRepository).loadResults(eq(latitudeLongitude.latitude), eq(latitudeLongitude.longitude), anyInt(), getLoadResultsCallbackCaptor.capture());
    getLoadResultsCallbackCaptor.getValue().onResultsLoaded(result);
    verify(view).showResults(result);
  }

  @Test public void verifyStartLoadsResults() {
    presenter.start();
    verify(resultsRepository).loadResults(eq(latitudeLongitude.latitude), eq(latitudeLongitude.longitude), anyInt(), getLoadResultsCallbackCaptor.capture());
    getLoadResultsCallbackCaptor.getValue().onResultsLoaded(result);
    verify(view).showResults(result);
  }

  @Test public void setupListeners() {
    presenter.setupListeners();
    verify(view).setPresenter(presenter);
  }

  @Test public void opensResult() {
    Result r = result.results.get(0);
    assertNotNull(r);
    presenter.openResultDetails(r);
    verify(view).openResult(r);
  }

  @Test public void showError() {
    String error = "Unknown access token";
    presenter.loadResults(true, true);
    verify(resultsRepository).loadResults(anyDouble(), anyDouble(), anyInt(), getLoadResultsCallbackCaptor.capture());
    getLoadResultsCallbackCaptor.getValue().onDataNotAvailable(error);
    verify(view).showError(error);
  }
}