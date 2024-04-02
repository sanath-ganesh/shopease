# ShopEase (E-Commerce Platform)

Welcome to Shopease, a cutting-edge e-commerce platform designed to provide a seamless shopping experience for both customers and vendors.

## Features

ShopEase comes packed with a wide array of features designed to enhance your online shopping experience:

- User-friendly interface for easy navigation
- Secure user authentication and authorization
- Product management system for vendors
- Shopping cart and checkout process
- Order tracking and history
- And much more!

## Getting Started

Follow these instructions to get your copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Before you begin, ensure you have the following installed:
- JDK 1.8 or later
- Maven 3.2+
- Your favorite IDE (Eclipse, IntelliJ IDEA, etc.)

### Setting Up MySQL

1. Install MySQL if you haven't already and start the MySQL server.
2. Log in to the MySQL shell and create a new database for the project:
   ```sql
   CREATE DATABASE [YOUR_DATABASE_NAME];
3. (Optional) Create a new MySQL user with privileges for your new database:
   ```sql
   CREATE USER '[YOUR_USER]'@'localhost' IDENTIFIED BY '[YOUR_PASSWORD]';
   GRANT ALL PRIVILEGES ON [YOUR_DATABASE_NAME].* TO '[YOUR_USER]'@'localhost';
   FLUSH PRIVILEGES;

### Installation

A step-by-step guide to getting a development environment running:

1. Clone the repo
   ```sh
   git clone https://github.com/sanath-ganesh/shopease.git

2. Navigate to the project directory and edit `src/main/java/com/sanath/shopease/util/HibernateUtil.java` to set up Hibernate and MySQL configuration:
  ```java
  settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/[YOUR_DATABASE_NAME]");
  settings.put("hibernate.connection.username", "[YOUR_USER]");
  settings.put("hibernate.connection.password", "[YOUR_PASSWORD]");
  settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
  settings.put("hibernate.hbm2ddl.auto", "update");
  settings.put("hibernate.show_sql", "true");
  settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
  ```
  
3. Ensure that the hibernate.hbm2ddl.auto property is set appropriately for your development environment.

4. Build the project with Maven to resolve dependencies:
  ```sh
  mvn clean install
  ```

5. Run the application using Maven:
  ```sh
  mvn spring-boot:run
  ```
4. Access the application at `http://localhost:8080/`.

## Usage

ShopEase is designed to be intuitive for both customers and vendors. Below are some examples of common tasks you can perform on the platform:

### Browsing Products

1. **Navigate to the Home Page**: Upon visiting ShopEase, you'll be greeted with the HomePage.
2. **Navigate to Product Page**: If you're looking for Products, click the Explore Products button.

### Adding Products to Shopping Cart

1. **Select a Product**: Click on any product to view its details.
2. **Add to Cart**: Adjust the quantity if needed and click the 'Add to Cart' button.
3. **View Cart**: Click on the cart icon at the top right corner to view selected items, make adjustments, or proceed to checkout.

### Creating a User Account

1. **Sign Up**: Click on the 'Register Now' link at the top left corner of the page.
2. **Fill in Your Details**: Provide necessary information such as your name, email, and password.
3. **Log In**: Once your account is set up, log in to your account to start shopping.

### Managing Products (For Vendors)

1. **Access Vendor Dashboard**: Click on the Sell Procust at the bottom left corner of the page.
2. **Add a New Product**: Click on the 'Add Product' button and fill in the product details including name, description, pricing, images, and other relevant information.
3. **Edit or Remove Products**: In your product list, use the 'Edit' or 'Delete' buttons alongside each product to update product details or remove them from your store.

### Checking Out

1. **View Cart**: Ensure all desired products are in your cart and have the correct quantities.
2. **Proceed to Checkout**: Click the 'Checkout' button in your cart.
3. **Enter Shipping Details**: Fill in your shipping address and choose a shipping method.
5. **Place Your Order**: Review your order and click 'Place Order' to complete the purchase.
