package xyz.husten.finstergram.findstergramdetail;

import dagger.Module;
import dagger.Provides;
import xyz.husten.finstergram.model.Result;

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
