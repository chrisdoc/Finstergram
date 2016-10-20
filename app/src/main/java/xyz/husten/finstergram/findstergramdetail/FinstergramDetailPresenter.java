package xyz.husten.finstergram.findstergramdetail;

import java.util.Locale;
import javax.inject.Inject;
import xyz.husten.finstergram.model.Result;

public final class FinstergramDetailPresenter implements FinstergramDetailContract.Presenter {
  private final FinstergramDetailContract.View view;
  private final Result result;

  @Inject
  FinstergramDetailPresenter(FinstergramDetailContract.View view, Result result) {
    this.view = view;
    this.result = result;
  }

  @Inject
  void setupListeners() {
    view.setPresenter(this);
  }

  @Override public void start() {
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
