package xyz.husten.finstergram.findstergramdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import javax.inject.Inject;
import xyz.husten.finstergram.R;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.utils.ActivityUtils;

public class FinstergramDetailActivity extends AppCompatActivity {
  public static final String EXTRA_RESULT_DATA = "RESULT_DATA";
  @Inject FinstergramDetailPresenter presenter;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_finstergram_detail);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FinstergramDetailFragment detailFragment =
        (FinstergramDetailFragment) getFragmentManager().findFragmentById(R.id.content_frame);

    if (detailFragment == null) {
      // Create the fragment
      detailFragment = FinstergramDetailFragment.newInstance();
      ActivityUtils.addFragmentToActivity(
          getFragmentManager(), detailFragment, R.id.content_frame);
    }

    Result result = getIntent().getParcelableExtra(EXTRA_RESULT_DATA);

    DaggerFinstergramDetailComponent
        .builder()
        .finstergramDetailModule(new FinstergramDetailModule(detailFragment, result))
        .build()
        .inject(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_detail, menu);
    return true;
  }
}
