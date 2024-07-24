package com.bhoper.provider;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.storage.UserStorageProviderFactory;

import java.util.ArrayList;
import java.util.List;

public class MongoDBUserProviderFactory implements UserStorageProviderFactory<MongoDBUserProvider> {

    public static final String PROVIDER_ID = "mongodb-user-provider";

    @Override
    public MongoDBUserProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        return new MongoDBUserProvider(keycloakSession, componentModel);
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return new ArrayList<>();
    }
}
