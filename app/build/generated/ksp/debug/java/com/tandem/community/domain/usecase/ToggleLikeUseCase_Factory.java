package com.tandem.community.domain.usecase;

import com.tandem.community.domain.repository.CommunityRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ToggleLikeUseCase_Factory implements Factory<ToggleLikeUseCase> {
  private final Provider<CommunityRepository> repositoryProvider;

  public ToggleLikeUseCase_Factory(Provider<CommunityRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ToggleLikeUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ToggleLikeUseCase_Factory create(Provider<CommunityRepository> repositoryProvider) {
    return new ToggleLikeUseCase_Factory(repositoryProvider);
  }

  public static ToggleLikeUseCase newInstance(CommunityRepository repository) {
    return new ToggleLikeUseCase(repository);
  }
}
