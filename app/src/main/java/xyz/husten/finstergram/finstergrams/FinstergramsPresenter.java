package xyz.husten.finstergram.finstergrams;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.husten.finstergram.api.InstagramApi;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.model.SearchResult;

final class FinstergramsPresenter implements FinstergramsContract.Presenter {

  private final InstagramApi api;
  private final FinstergramsContract.View finstergramsView;

  @Inject
  FinstergramsPresenter(InstagramApi api, FinstergramsContract.View finstergramsView) {
    this.api = api;
    this.finstergramsView = finstergramsView;
  }

  @Inject
  void setupListeners() {
    finstergramsView.setPresenter(this);
  }

  @Override public void loadResults(final boolean showLoadingUI) {
    if (showLoadingUI) {
      finstergramsView.setLoadingIndicator(true);
    }
    api.search("52.52","13.413", 5000).enqueue(new Callback<SearchResult>() {
      @Override public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
        finstergramsView.showResults(response.body());
        if (showLoadingUI) {
          finstergramsView.setLoadingIndicator(false);
        }
      }

      @Override public void onFailure(Call<SearchResult> call, Throwable t) {
        if (showLoadingUI) {
          finstergramsView.setLoadingIndicator(false);
        }
      }
    });
  }

  @Override public void openResultDetails(@NonNull Result requestedResult) {

  }

  @Override public void start() {
    loadResults(true);
  }
}
