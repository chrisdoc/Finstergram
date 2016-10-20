package xyz.husten.finstergram.components;

import com.squareup.picasso.Picasso;
import dagger.Component;
import javax.inject.Singleton;
import xyz.husten.finstergram.api.InstagramApi;
import xyz.husten.finstergram.modules.AppModule;
import xyz.husten.finstergram.modules.NetModule;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
  InstagramApi getInstagramApi();
  Picasso getPicasso();
}
