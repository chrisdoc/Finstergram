package xyz.husten.finstergram.findstergramdetail;

import java.util.Locale;
import javax.inject.Inject;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.repository.ResultsDataSource;
import xyz.husten.finstergram.repository.ResultsRepository;

public final class FinstergramDetailPresenter implements FinstergramDetailContract.Presenter {
  private final FinstergramDetailContract.View view;
  private final ResultsRepository resultsRepository;
  private final String id;
  private Result result;

  @Inject
  FinstergramDetailPresenter(FinstergramDetailContract.View view, ResultsRepository resultsRepository, String id) {
    this.view = view;
    this.resultsRepository = resultsRepository;
    this.id = id;
  }

  @Inject
  void setupListeners() {
    view.setPresenter(this);
  }

  @Override public void start() {
    resultsRepository.loadResult(id, new ResultsDataSource.GetResultCallback() {
      @Override public void onResultLoaded(Result result) {
        bindResult(result);
      }
      @Override public void onResultNotAvailable(String error) {

      }
    });
  }

  protected void bindResult(Result result) {
    this.result = result;
    view.showResult(result);
  }

  @Override public void imageClicked() {
    view.openImage(result.images.standardResolution.url);
  }

  @Override public void directions() {
    String googleMapUri = String.format("google.navigation:q=%f,%f",result.location.latitude, result.location.longitude);
    view.showDirectionUri(googleMapUri);
  }

  @Override public void map() {
    String googleMapUri = String.format("geo:0,0?q=%f,%f(%s)",result.location.latitude, result.location.longitude, result.location.name);
    view.showMapUri(googleMapUri);
  }

  @Override public void share() {
    view.shareLink(result.link);
  }
}
