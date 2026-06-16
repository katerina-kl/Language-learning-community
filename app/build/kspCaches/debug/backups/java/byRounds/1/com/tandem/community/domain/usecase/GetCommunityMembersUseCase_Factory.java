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
public final class GetCommunityMembersUseCase_Factory implements Factory<GetCommunityMembersUseCase> {
  private final Provider<CommunityRepository> repositoryProvider;

  public GetCommunityMembersUseCase_Factory(Provider<CommunityRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetCommunityMembersUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetCommunityMembersUseCase_Factory create(
      Provider<CommunityRepository> repositoryProvider) {
    return new GetCommunityMembersUseCase_Factory(repositoryProvider);
  }

  public static GetCommunityMembersUseCase newInstance(CommunityRepository repository) {
    return new GetCommunityMembersUseCase(repository);
  }
}
