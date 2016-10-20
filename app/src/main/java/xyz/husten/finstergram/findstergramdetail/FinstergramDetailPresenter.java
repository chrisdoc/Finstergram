package xyz.husten.finstergram.findstergramdetail;

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
}
