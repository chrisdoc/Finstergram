package xyz.husten.finstergram.ui.finstergrams;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import xyz.husten.finstergram.model.LatitudeLongitude;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.model.SearchResult;
import xyz.husten.finstergram.repository.ResultsDataSource;
import xyz.husten.finstergram.repository.ResultsRepository;

final class FinstergramsPresenter implements FinstergramsContract.Presenter {

  private final FinstergramsContract.View finstergramsView;
  private final ResultsRepository resultsRepository;
  private final LatitudeLongitude latitudeLongitude;

  @Inject
  FinstergramsPresenter(FinstergramsContract.View finstergramsView, ResultsRepository resultsRepository, LatitudeLongitude latitudeLongitude) {
    this.finstergramsView = finstergramsView;
    this.resultsRepository = resultsRepository;
    this.latitudeLongitude = latitudeLongitude;
  }

  @Inject
  void setupListeners() {
    finstergramsView.setPresenter(this);
  }

  @Override public void loadResults(final boolean showLoadingUI, final boolean useCache) {
    if (showLoadingUI) {
      finstergramsView.setLoadingIndicator(true);
    }

    if (!useCache) {
      resultsRepository.refreshResults();
    }

    resultsRepository.loadResults(latitudeLongitude.latitude, latitudeLongitude.longitude, 5000, new ResultsDataSource.LoadResultsCallback() {
          @Override public void onResultsLoaded(SearchResult result) {
            finstergramsView.showResults(result);
            if (showLoadingUI) {
              finstergramsView.setLoadingIndicator(false);
            }
          }

          @Override public void onDataNotAvailable(String error) {
            if (showLoadingUI) {
              finstergramsView.setLoadingIndicator(false);
            }
            finstergramsView.showError(error);
          }
        });
  }

  @Override public void openResultDetails(@NonNull Result requestedResult) {
    finstergramsView.openResult(requestedResult);
  }

  @Override public void start() {
    loadResults(true, true);
  }
}
