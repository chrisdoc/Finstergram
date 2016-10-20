package xyz.husten.finstergram.ui.findstergramdetail;

import dagger.Module;
import dagger.Provides;

@Module public class FinstergramDetailModule {
  private final FinstergramDetailContract.View view;
  private final String resultId;

  public FinstergramDetailModule(FinstergramDetailContract.View view, String resultId) {
    this.view = view;
    this.resultId = resultId;
  }

  @Provides FinstergramDetailContract.View provideFinstergramsContractView() {
    return view;
  }

  @Provides String provideResultId() {
    return resultId;
  }
}
