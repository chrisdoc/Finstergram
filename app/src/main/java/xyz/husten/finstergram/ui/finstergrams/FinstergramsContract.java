package xyz.husten.finstergram.ui.finstergrams;

import android.support.annotation.NonNull;
import xyz.husten.finstergram.BasePresenter;
import xyz.husten.finstergram.BaseView;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.model.SearchResult;

public interface FinstergramsContract {

  interface View extends BaseView<Presenter> {
    void setLoadingIndicator(boolean active);
    void showResults(SearchResult result);
    void openResult(Result result);
    void showError(String error);
  }

  interface Presenter extends BasePresenter {
    void loadResults(final boolean showLoadingUI, final boolean useCache);
    void openResultDetails(@NonNull Result requestedResult);
  }
}
