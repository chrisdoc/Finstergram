package xyz.husten.finstergram.repository;

import dagger.Component;
import javax.inject.Singleton;
import xyz.husten.finstergram.modules.NetModule;

@Singleton
@Component(modules = {ResultsRepositoryModule.class, NetModule.class})
public interface ResultsRepositoryComponent {

  //ResultsRepository getResultsRepository();
}