package com.gri.alex.provider;

import com.gri.alex.api.UserApiService;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.UserStorageProviderFactory;

public class RemoteUserStorageProviderFactory
    implements UserStorageProviderFactory<UserStorageProvider> {

  public static final String PROVIDER_NAME="demo-remote-user-storage-provider";
  public static final String USER_SERVICE_URL = "http://localhost:8099";

  @Override
  public RemoteUserStorageProvider create(KeycloakSession session, ComponentModel model) {
    return new RemoteUserStorageProvider(
        session,
        model,
        buildHttpClient(USER_SERVICE_URL));
  }

  @Override
  public String getId() {
    return PROVIDER_NAME;
  }

  private UserApiService buildHttpClient(String uri) {
    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target = client.target(uri);

    return target.proxyBuilder(UserApiService.class)
        .classloader(UserApiService.class.getClassLoader())
        .build();
  }
}
