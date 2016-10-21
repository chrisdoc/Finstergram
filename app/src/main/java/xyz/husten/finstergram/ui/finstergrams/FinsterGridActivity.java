package xyz.husten.finstergram.ui.finstergrams;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import javax.inject.Inject;
import xyz.husten.finstergram.BuildConfig;
import xyz.husten.finstergram.FinstergramApp;
import xyz.husten.finstergram.R;
import xyz.husten.finstergram.model.LatitudeLongitude;
import xyz.husten.finstergram.utils.ActivityUtils;

public class FinsterGridActivity extends AppCompatActivity {
  @Inject FinstergramsPresenter presenter;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_finster_grid);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FinsterGridFragment gridFragment =
        (FinsterGridFragment) getFragmentManager().findFragmentById(R.id.content_frame);

    if (gridFragment == null) {
      // Create the fragment
      gridFragment = FinsterGridFragment.newInstance();
      ActivityUtils.addFragmentToActivity(
          getFragmentManager(), gridFragment, R.id.content_frame);
    }

    LatitudeLongitude latitudeLongitude = new LatitudeLongitude(BuildConfig.Latitude, BuildConfig.Longitude);
    DaggerFinstergramsComponent
        .builder()
        .netComponent(((FinstergramApp)getApplication()).getNetComponent())
        .finstergramsPresenterModule(new FinstergramsPresenterModule(gridFragment, latitudeLongitude))
        .build()
        .inject(this);
  }

  @Override public void onAttachedToWindow() {
    super.onAttachedToWindow();

  }
}
