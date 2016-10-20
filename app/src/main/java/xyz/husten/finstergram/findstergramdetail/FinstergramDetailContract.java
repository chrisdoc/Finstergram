package xyz.husten.finstergram.findstergramdetail;

import xyz.husten.finstergram.BasePresenter;
import xyz.husten.finstergram.BaseView;
import xyz.husten.finstergram.model.Result;

public interface FinstergramDetailContract {

  interface View extends BaseView<Presenter> {
    void showResult(Result result);
    void openImage(String url);
    void showDirectionUri(String googleMapUri);
    void showMapUri(String googleMapUri);
    void shareLink(String link);
  }

  interface Presenter extends BasePresenter {
    void imageClicked();
    void directions();
    void map();
    void share();
  }

}
