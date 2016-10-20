package xyz.husten.finstergram.finstergrams;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import xyz.husten.finstergram.FinstergramApp;
import xyz.husten.finstergram.R;
import xyz.husten.finstergram.findstergramdetail.FinstergramDetailActivity;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.model.SearchResult;
import xyz.husten.finstergram.utils.FullScreenImageActivity;

public class FinsterGridFragment extends Fragment implements FinstergramsContract.View {

  @Inject FinstergramsContract.Presenter presenter;
  private Picasso picasso;

  @BindView(R.id.rv_results) RecyclerView recyclerView;
  @BindView(R.id.refresh_layout) SwipeRefreshLayout swipeRefreshLayout;

  public FinsterGridFragment() {
    // Required empty public constructor
  }

  public static FinsterGridFragment newInstance() {
    return new FinsterGridFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if(getActivity()!=null) {
      picasso = ((FinstergramApp)getActivity().getApplication()).getNetComponent().getPicasso();
    }
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    recyclerView.setAdapter(createAdapter(Collections.<Result>emptyList()));
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        presenter.loadResults(true, false);
      }
    });
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_finster_grid, container, false);
    ButterKnife.bind(this, view);
    return view;
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

    // Make sure setRefreshing() is called after the layout is done with everything else.
    swipeRefreshLayout.post(new Runnable() {
      @Override
      public void run() {
        swipeRefreshLayout.setRefreshing(active);
      }
    });
  }

  @Override public void showResults(SearchResult result) {
    recyclerView.swapAdapter(createAdapter(result.results), true);
  }

  @Override public void showError(String error) {
    Snackbar.make(recyclerView, error, Snackbar.LENGTH_LONG)
        .show();
  }

  @Override public void setPresenter(FinstergramsContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override public void openResult(Result result) {
    Intent intent = new Intent(getContext(), FinstergramDetailActivity.class);
    intent.putExtra(FinstergramDetailActivity.EXTRA_RESULT_ID, result.id);
    startActivity(intent);
  }

  private FinstergramsAdapter createAdapter(List<Result> results) {
    return new FinstergramsAdapter(results, picasso, new FinstergramsAdapter.OnResultClickListener() {
      @Override public void onClick(Result result) {
        presenter.openResultDetails(result);
      }
    });
  }
}
