package com.tandem.community.di;

import com.tandem.community.data.remote.CommunityApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class NetworkModule_ProvideCommunityApiFactory implements Factory<CommunityApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideCommunityApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public CommunityApi get() {
    return provideCommunityApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideCommunityApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideCommunityApiFactory(retrofitProvider);
  }

  public static CommunityApi provideCommunityApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideCommunityApi(retrofit));
  }
}
