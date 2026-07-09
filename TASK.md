## Task: Coffeeshop Configuration Management
### Objective:
Learn how to replace loosely typed @Value annotations with a robust, type-safe configuration system using @ConfigurationProperties. You will also learn how to manage environment-specific settings using YAML profiles.

### Requirements:

#### Type-Safe Configuration:

- Create a CoffeeShopProperties class to store application settings.

- Annotate it with @ConfigurationProperties(prefix = "coffee.shop") to automatically map YAML values to Java fields.

- Ensure the application uses @ConfigurationPropertiesScan to enable automatic discovery of your configuration bean.

#### YAML Configuration Hierarchy:

- Use a hierarchical YAML structure. Define base settings in application.yaml (e.g., server port).

- Create profile-specific files: application-dev.yaml and application-prod.yaml.

- Move shop-specific settings (name, max orders) into these files to allow different behaviors for dev and prod environments.

#### Dependency Injection:

- Inject your CoffeeShopProperties object into OrderController using constructor-based injection.

- Ensure the controller uses these properties to return business data via REST endpoints.

#### Endpoint Implementation:

- Implement /shop-name, /max-orders, and /info endpoints that reflect the currently active configuration.

#### Environment Switching:

- Demonstrate that you can launch the application with different profiles using the VM option SPRING_PROFILES_ACTIVE=dev (or prod).

#### Verification:

- Validate that the application reads the correct values from the respective YAML files depending on the active profile.

- Verify that nested properties (coffee.shop.name) are correctly bound to your Java class.

### Deliverables:

- A CoffeeShopProperties POJO mapped correctly to the YAML structure.

- A set of YAML files (base, dev, prod) demonstrating property overriding.

- An OrderController utilizing the type-safe configuration bean.