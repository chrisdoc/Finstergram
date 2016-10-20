package xyz.husten.finstergram.repository;

import dagger.Module;
import dagger.Provides;
import xyz.husten.finstergram.api.InstagramApi;

@Module
public class ResultsRepositoryModule {
  @Provides
  ResultsDataSource provideDataSource(InstagramApi api){
    return new ResultsRepository(api);
  }
}