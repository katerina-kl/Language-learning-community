package com.tandem.community.presentation.community;

import com.tandem.community.domain.usecase.GetCommunityMembersUseCase;
import com.tandem.community.domain.usecase.ObserveLikedIdsUseCase;
import com.tandem.community.domain.usecase.ToggleLikeUseCase;
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
public final class CommunityViewModel_Factory implements Factory<CommunityViewModel> {
  private final Provider<GetCommunityMembersUseCase> getCommunityMembersProvider;

  private final Provider<ObserveLikedIdsUseCase> observeLikedIdsProvider;

  private final Provider<ToggleLikeUseCase> toggleLikeProvider;

  public CommunityViewModel_Factory(
      Provider<GetCommunityMembersUseCase> getCommunityMembersProvider,
      Provider<ObserveLikedIdsUseCase> observeLikedIdsProvider,
      Provider<ToggleLikeUseCase> toggleLikeProvider) {
    this.getCommunityMembersProvider = getCommunityMembersProvider;
    this.observeLikedIdsProvider = observeLikedIdsProvider;
    this.toggleLikeProvider = toggleLikeProvider;
  }

  @Override
  public CommunityViewModel get() {
    return newInstance(getCommunityMembersProvider.get(), observeLikedIdsProvider.get(), toggleLikeProvider.get());
  }

  public static CommunityViewModel_Factory create(
      Provider<GetCommunityMembersUseCase> getCommunityMembersProvider,
      Provider<ObserveLikedIdsUseCase> observeLikedIdsProvider,
      Provider<ToggleLikeUseCase> toggleLikeProvider) {
    return new CommunityViewModel_Factory(getCommunityMembersProvider, observeLikedIdsProvider, toggleLikeProvider);
  }

  public static CommunityViewModel newInstance(GetCommunityMembersUseCase getCommunityMembers,
      ObserveLikedIdsUseCase observeLikedIds, ToggleLikeUseCase toggleLike) {
    return new CommunityViewModel(getCommunityMembers, observeLikedIds, toggleLike);
  }
}
