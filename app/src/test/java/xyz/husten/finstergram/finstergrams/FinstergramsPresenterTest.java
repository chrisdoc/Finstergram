package xyz.husten.finstergram.finstergrams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.notification.Failure;
import org.mockito.Mock;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.husten.finstergram.api.InstagramApi;
import xyz.husten.finstergram.helpers.FailureCall;
import xyz.husten.finstergram.helpers.SuccessCall;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.model.SearchResult;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyZeroInteractions;

public class FinstergramsPresenterTest {
  private FinstergramsContract.View view;
  private InstagramApi api;
  private FinstergramsPresenter presenter;
  private SearchResult result;
  @Before public void setUp() {
    view = mock(FinstergramsContract.View.class);
    api = mock(InstagramApi.class);
    result = new SearchResult();

    List<Result> results = new ArrayList<>();
    results.add(createResult("123"));
    results.add(createResult("143"));
    results.add(createResult("153"));
    result.results = results;

    when(api.search(anyDouble(), anyDouble(), anyInt())).thenReturn(new SuccessCall<SearchResult>(result));
    presenter = new FinstergramsPresenter(api, view);
  }

  private Result createResult(String id) {
    Result result = new Result();
    result.id = id;
    return result;
  }

  @Test public void callLoadResultsWithShowLoadingUI() {
    presenter.loadResults(true);
    verify(view).setLoadingIndicator(true);
  }

  @Test public void callLoadResultsWithOutShowLoadingUI() {
    presenter.loadResults(false);
    verify(view, never()).setLoadingIndicator(anyBoolean());
  }

  @Test public void verifyViewShowResults() {
    presenter.loadResults(true);
    verify(view).showResults(result);
  }

  @Test public void verifyStartLoadsResults() {
    presenter.start();
    verify(view).showResults(result);
  }

  @Test public void setupListeners() {
    presenter.setupListeners();
    verify(view).setPresenter(presenter);
  }

  @Test public void opensResult() {
    Result r = result.results.get(0);
    presenter.openResultDetails(r);
    verify(view).openResult(r);
  }

  @Test public void showError() {
    reset(api);
    Exception e = new Exception("Unknown access token");
    when(api.search(anyDouble(), anyDouble(), anyInt())).thenReturn(new FailureCall(e));
    presenter.loadResults(true);
    verify(view).showError(e.getMessage());
  }
}