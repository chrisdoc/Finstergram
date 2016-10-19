package xyz.husten.finstergram.components;

import dagger.Component;
import javax.inject.Singleton;
import xyz.husten.finstergram.FinsterGridActivity;
import xyz.husten.finstergram.modules.AppModule;
import xyz.husten.finstergram.modules.NetModule;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
  void inject(FinsterGridActivity activity);
}