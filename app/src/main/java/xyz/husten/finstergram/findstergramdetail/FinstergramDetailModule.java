package xyz.husten.finstergram.findstergramdetail;

import dagger.Module;
import dagger.Provides;
import xyz.husten.finstergram.model.Result;

@Module
public class FinstergramDetailModule {
  private final FinstergramDetailContract.View view;
  private final Result result;

  public FinstergramDetailModule(FinstergramDetailContract.View view, Result result) {
    this.view = view;
    this.result = result;
  }

  @Provides
  FinstergramDetailContract.View provideFinstergramsContractView() {
    return view;
  }

  @Provides
  Result provideResult() {
    return result;
  }
 }
