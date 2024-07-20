# Project Overview: shop

## Project Description

**Shop** The "shop" project is a management system for stores and their inventory, providing the following key functionalities:

**Location:** Yerevan  
**Date:** 04/2024

**Developer:**  
Web Development in Java  
**Name:** Gagikovich Gor Mkhitaryan

**Project URL:** [https://github.com/mypy125/shop](https://github.com/mypy125/shop/tree/main/grails-app)

## Core Features

### Product Management

- Create, update, delete, and retrieve product information. 
- Manage product data, including product code, price, production date, and expiration date.

### Stock Management

- Create, update, delete, and retrieve stock information.
- Add and remove products in the stock.
- Track the quantity of products in stock.

### Store Management

- Create, update, delete, and retrieve store information.
- Add and remove products in the store.
- Sell products from the store.
- Return products from the store to the stock.

### Inventory and Expiry Check 

- Automatically check all stores for expired products and return such products to the stock.

### Components

- **ProductService** 
- Handles operations related to products. 
- Checks for unique product codes upon creation.
- Saves and updates product information in the database.

- **StockService**  
- Handles operations related to stock.
- Manages adding and removing products in the stock.
- Interacts with StockRepository to save and retrieve data.

- **StoreService**
- Handles operations related to stores.
- Manages adding and removing products in the store.
- Provides functionality for selling products from the store.
- Handles returning products from the store to the stock.
- Checks and returns expired products to the stock.


### Architecture

- **Repositories** Used for database interactions. Includes ProductRepository, StockRepository, and StoreRepository.
- **Services** Handle the business logic of the application and interact with repositories.
- **Exceptions** Handle errors such as product, store, or stock not found.

## User Interaction

- Users of the system can perform various operations such as creating new products,
- adding products to stock, selling products in the store, and checking product expiration dates.
- All these operations are supported through corresponding service methods.

- The "shop" project provides comprehensive inventory and product management, optimizing inventory and sales processes in retail.


