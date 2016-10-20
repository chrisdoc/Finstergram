package xyz.husten.finstergram.finstergrams;

import dagger.Component;
import xyz.husten.finstergram.components.NetComponent;
import xyz.husten.finstergram.utils.FragmentScoped;

@FragmentScoped
@Component(dependencies = NetComponent.class, modules = FinstergramsPresenterModule.class)
public interface FinstergramsComponent {
  void inject(FinsterGridActivity activity);
}
