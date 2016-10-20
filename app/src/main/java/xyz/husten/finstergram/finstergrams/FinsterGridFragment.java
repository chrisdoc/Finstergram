package xyz.husten.finstergram.finstergrams;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import javax.inject.Inject;
import xyz.husten.finstergram.R;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.model.SearchResult;

public class FinsterGridFragment extends Fragment implements FinstergramsContract.View {

  @Inject FinstergramsContract.Presenter presenter;

  public FinsterGridFragment() {
    // Required empty public constructor
  }

  public static FinsterGridFragment newInstance() {
    return new FinsterGridFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_finster_grid, container, false);
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public void onDetach() {
    super.onDetach();
  }

  @Override public void onResume() {
    super.onResume();
    presenter.start();
  }

  @Override public void setLoadingIndicator(final boolean active) {
    if (getView() == null) {
      return;
    }
    final SwipeRefreshLayout srl =
        (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

    // Make sure setRefreshing() is called after the layout is done with everything else.
    srl.post(new Runnable() {
      @Override
      public void run() {
        srl.setRefreshing(active);
      }
    });
  }

  @Override public void showResults(SearchResult result) {

  }

  @Override public void showNoResults() {

  }

  @Override public void setPresenter(FinstergramsContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
