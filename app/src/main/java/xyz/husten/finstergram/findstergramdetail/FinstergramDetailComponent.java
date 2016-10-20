package xyz.husten.finstergram.findstergramdetail;

import dagger.Component;
import xyz.husten.finstergram.components.NetComponent;
import xyz.husten.finstergram.utils.FragmentScoped;

@FragmentScoped
@Component(dependencies = NetComponent.class, modules = FinstergramDetailModule.class)
public interface FinstergramDetailComponent {

  void inject(FinstergramDetailActivity taskDetailActivity);
}
