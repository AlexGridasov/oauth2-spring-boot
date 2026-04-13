package com.gri.alex.provider;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.UserStorageProviderFactory;

public class RemoteUserStorageProviderFactory
    implements UserStorageProviderFactory<UserStorageProvider> {

  public static final String PROVIDER_NAME="demo-remote-user-storage-provider";

  @Override
  public UserStorageProvider create(KeycloakSession session, ComponentModel model) {
    return new RemoteUserStorageProvider(session, model);
  }

  @Override
  public String getId() {
    return PROVIDER_NAME;
  }
}
