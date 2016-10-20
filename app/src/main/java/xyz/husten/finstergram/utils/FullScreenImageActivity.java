package xyz.husten.finstergram.utils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import xyz.husten.finstergram.FinstergramApp;
import xyz.husten.finstergram.R;

public class FullScreenImageActivity extends Activity {
  public static final String EXTRA_IMAGE_URL = "IMAGE_URL";
  @BindView(R.id.iv_image) ImageView imageView;
  @Inject Picasso picasso;
  private String imageUrl;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_full_screen_image);

    imageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);

    ((FinstergramApp)getApplication()).getNetComponent().inject(this);
    ButterKnife.bind(this);
  }

  @Override public void onAttachedToWindow() {
    super.onAttachedToWindow();
    picasso.load(imageUrl).into(imageView);
  }
}
