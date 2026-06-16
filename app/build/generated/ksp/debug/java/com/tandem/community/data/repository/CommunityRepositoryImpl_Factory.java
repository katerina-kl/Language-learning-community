package com.tandem.community.data.repository;

import com.tandem.community.data.local.LikedMemberDao;
import com.tandem.community.data.remote.CommunityApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("com.tandem.community.di.IoDispatcher")
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
public final class CommunityRepositoryImpl_Factory implements Factory<CommunityRepositoryImpl> {
  private final Provider<CommunityApi> apiProvider;

  private final Provider<LikedMemberDao> likedMemberDaoProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public CommunityRepositoryImpl_Factory(Provider<CommunityApi> apiProvider,
      Provider<LikedMemberDao> likedMemberDaoProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.apiProvider = apiProvider;
    this.likedMemberDaoProvider = likedMemberDaoProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public CommunityRepositoryImpl get() {
    return newInstance(apiProvider.get(), likedMemberDaoProvider.get(), ioDispatcherProvider.get());
  }

  public static CommunityRepositoryImpl_Factory create(Provider<CommunityApi> apiProvider,
      Provider<LikedMemberDao> likedMemberDaoProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new CommunityRepositoryImpl_Factory(apiProvider, likedMemberDaoProvider, ioDispatcherProvider);
  }

  public static CommunityRepositoryImpl newInstance(CommunityApi api, LikedMemberDao likedMemberDao,
      CoroutineDispatcher ioDispatcher) {
    return new CommunityRepositoryImpl(api, likedMemberDao, ioDispatcher);
  }
}
