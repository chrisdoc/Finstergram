package xyz.husten.finstergram.finstergrams;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.husten.finstergram.FinstergramApp;
import xyz.husten.finstergram.R;
import xyz.husten.finstergram.api.InstagramApi;
import xyz.husten.finstergram.model.SearchResult;
import xyz.husten.finstergram.utils.ActivityUtils;

public class FinsterGridActivity extends Activity {
  @Inject FinstergramsPresenter presenter;
  @Inject InstagramApi api;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_finster_grid);

    FinsterGridFragment gridFragment =
        (FinsterGridFragment) getFragmentManager().findFragmentById(R.id.content_frame);

    if (gridFragment == null) {
      // Create the fragment
      gridFragment = FinsterGridFragment.newInstance();
      ActivityUtils.addFragmentToActivity(
          getFragmentManager(), gridFragment, R.id.content_frame);
    }

    DaggerFinstergramsComponent
        .builder()
        .netComponent(((FinstergramApp)getApplication()).getNetComponent())
        .finstergramsPresenterModule(new FinstergramsPresenterModule(gridFragment))
        .build()
        .inject(this);
  }

  @Override public void onAttachedToWindow() {
    super.onAttachedToWindow();

  }
}