package xyz.husten.finstergram.ui.finstergrams;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import xyz.husten.finstergram.BuildConfig;
import xyz.husten.finstergram.model.LatitudeLongitude;

@Module
public class FinstergramsPresenterModule {
  private final FinstergramsContract.View view;
  private final LatitudeLongitude latitudeLongitude;

  public FinstergramsPresenterModule(FinstergramsContract.View view, LatitudeLongitude latitudeLongitude) {
    this.view = view;
    this.latitudeLongitude = latitudeLongitude;
  }

  @Provides
  FinstergramsContract.View providesFinstergramsContractView() {
    return view;
  }

  @Provides
  LatitudeLongitude providesLatitudeLongitude() {
    return latitudeLongitude;
  }
}
