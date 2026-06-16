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
public final class ObserveLikedIdsUseCase_Factory implements Factory<ObserveLikedIdsUseCase> {
  private final Provider<CommunityRepository> repositoryProvider;

  public ObserveLikedIdsUseCase_Factory(Provider<CommunityRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ObserveLikedIdsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ObserveLikedIdsUseCase_Factory create(
      Provider<CommunityRepository> repositoryProvider) {
    return new ObserveLikedIdsUseCase_Factory(repositoryProvider);
  }

  public static ObserveLikedIdsUseCase newInstance(CommunityRepository repository) {
    return new ObserveLikedIdsUseCase(repository);
  }
}
