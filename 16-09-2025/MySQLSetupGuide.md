# MySQL Database Setup Guide

## Option 1: Quick Working Demo (Already Done)
✅ **InMemoryDatabaseDemo.java** - This works immediately without any setup!

## Option 2: Real MySQL Database Setup

### Step 1: Install MySQL
1. Download MySQL from: https://dev.mysql.com/downloads/mysql/
2. Install with default settings
3. Remember your root password during installation

### Step 2: Download MySQL Connector
1. Download from: https://dev.mysql.com/downloads/connector/j/
2. Extract the JAR file (mysql-connector-java-x.x.x.jar)

### Step 3: Create Database
Open MySQL Command Line or MySQL Workbench and run:
```sql
CREATE DATABASE restaurantdb;
USE restaurantdb;
```

### Step 4: Update Connection Details
In p1.java, update these lines:
```java
conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/restaurantdb", 
    "root", 
    "YOUR_ACTUAL_PASSWORD"  // Replace with your MySQL password
);
```

### Step 5: Compile and Run
```bash
# Compile
javac -cp mysql-connector-java-8.0.33.jar p1.java

# Run
java -cp ".;mysql-connector-java-8.0.33.jar" p1
```

## Option 3: Online MySQL (Easiest)
Use online MySQL services like:
- db4free.net (free)
- freemysqlhosting.net (free)
- PlanetScale (free tier)

## Troubleshooting Common Errors

### Error: "Driver not found"
- Make sure MySQL Connector JAR is in classpath
- Check JAR file name matches the command

### Error: "Access denied"
- Check username/password
- Make sure MySQL service is running

### Error: "Connection refused"
- Check if MySQL is running on port 3306
- Check firewall settings

## What Each File Does

1. **InMemoryDatabaseDemo.java** ✅ - Works immediately, simulates database operations
2. **p1.java** - Real MySQL connection (needs MySQL server + connector)
3. **SimpleDatabaseDemo.java** - SQLite version (lightweight, but had dependency issues)