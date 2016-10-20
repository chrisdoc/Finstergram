package xyz.husten.finstergram.ui.finstergrams;

import dagger.Module;
import dagger.Provides;

@Module
public class FinstergramsPresenterModule {
  private final FinstergramsContract.View view;

  public FinstergramsPresenterModule(FinstergramsContract.View view) {
    this.view = view;
  }

  @Provides
  FinstergramsContract.View providesFinstergramsContractView() {
    return view;
  }
}
