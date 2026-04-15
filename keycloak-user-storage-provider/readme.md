1. Build and Package

Compile your code and package it into a JAR file. Ensure you include necessary dependencies like `keycloak-server-spi` in your `pom.xml` or `build.gradle` as "provided" dependencies. 

2. Deploy to Keycloak

Keycloak (Quarkus) requires a build step to optimize the server with your new provider. 

  - **Copy the JAR**: Place your compiled JAR file in the `keycloak/providers/` directory.
  - **Run Build**: Execute the following command to register the provider:

```terminal
bin/kc.bat build
```
3. Check configuration

```terminal
bin/kc.bat show-config
```

4. **Start Server**: Launch Keycloak:

```terminal
bin/kc.bat start-dev
```

5. Enable in Admin Console

Once the server is running with the new provider:

1. Log in to the **Keycloak Admin Console**.
2. Select your **Realm**.
3. Navigate to **User Federation** in the left menu.
4. Click **Add provider** and select your provider (the name corresponds to the `getId()` method in your factory).
5. Configure any required settings and click **Save**.
