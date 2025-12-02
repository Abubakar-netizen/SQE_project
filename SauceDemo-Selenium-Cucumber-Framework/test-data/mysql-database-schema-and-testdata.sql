-- Create database
CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;

-- Users table for login credentials
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    user_type VARCHAR(20) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert test users
INSERT INTO users (username, password, user_type, is_active) VALUES
('standard_user', 'secret_sauce', 'standard', TRUE),
('locked_out_user', 'secret_sauce', 'locked', FALSE),
('problem_user', 'secret_sauce', 'problem', TRUE),
('performance_glitch_user', 'secret_sauce', 'performance', TRUE);

-- Products table
CREATE TABLE IF NOT EXISTS products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT,
    category VARCHAR(50),
    in_stock BOOLEAN DEFAULT TRUE
);

-- Insert test products
INSERT INTO products (product_name, price, description, category, in_stock) VALUES
('Sauce Labs Backpack', 29.99, 'carry.allTheThings() with the sleek, streamlined Sly Pack', 'Backpack', TRUE),
('Sauce Labs Bike Light', 9.99, 'A red light isn''t the desired state in testing', 'Light', TRUE),
('Sauce Labs Bolt T-Shirt', 15.99, 'Get your testing superhero on', 'Clothing', TRUE),
('Sauce Labs Fleece Jacket', 49.99, 'It''s not every day that you come across a midweight quarter-zip fleece jacket', 'Clothing', TRUE),
('Sauce Labs Onesie', 7.99, 'Rib snap infant onesie for the junior automation engineer', 'Clothing', TRUE),
('Test.allTheThings() T-Shirt (Red)', 15.99, 'This classic Sauce Labs t-shirt is perfect to wear', 'Clothing', TRUE);

-- Checkout information table
CREATE TABLE IF NOT EXISTS checkout_info (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    country VARCHAR(50) DEFAULT 'USA'
);

-- Insert test checkout data
INSERT INTO checkout_info (first_name, last_name, postal_code, country) VALUES
('John', 'Doe', '12345', 'USA'),
('Jane', 'Smith', '67890', 'USA'),
('Bob', 'Johnson', '54321', 'USA'),
('Alice', 'Williams', '98765', 'USA');

-- Test results table (optional - for tracking test execution)
CREATE TABLE IF NOT EXISTS test_results (
    id INT PRIMARY KEY AUTO_INCREMENT,
    test_name VARCHAR(200) NOT NULL,
    status VARCHAR(20) NOT NULL,
    execution_time DECIMAL(10,2),
    executed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Display tables
SELECT 'Users Table:' as '';
SELECT * FROM users;

SELECT 'Products Table:' as '';
SELECT * FROM products;

SELECT 'Checkout Info Table:' as '';
SELECT * FROM checkout_info;
