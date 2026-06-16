package com.tandem.community.di;

import com.tandem.community.data.local.AppDatabase;
import com.tandem.community.data.local.LikedMemberDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideLikedMemberDaoFactory implements Factory<LikedMemberDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideLikedMemberDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public LikedMemberDao get() {
    return provideLikedMemberDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideLikedMemberDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideLikedMemberDaoFactory(databaseProvider);
  }

  public static LikedMemberDao provideLikedMemberDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideLikedMemberDao(database));
  }
}
