package xyz.husten.finstergram.findstergramdetail;

import xyz.husten.finstergram.BasePresenter;
import xyz.husten.finstergram.BaseView;
import xyz.husten.finstergram.model.Result;

public interface FinstergramDetailContract {

  interface View extends BaseView<Presenter> {
    void showResult(Result result);
    void openImage(String url);
  }

  interface Presenter extends BasePresenter {
    void imageClicked();
  }

}
