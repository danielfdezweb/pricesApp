# 🛍️ Price Selector Service – Spring Boot API

Welcome!  
This project is a Spring Boot RESTful API designed to retrieve the applicable product price based on a given application date, product ID, and brand ID. It simulates the backend pricing logic of a retail platform (e.g., ZARA) based on predefined pricing rules.

---

## 📦 What This Project Does

The API exposes a single REST endpoint that:

- Accepts as input:
  - Application date (date and time)
  - Product ID
  - Brand ID

- Returns as output:
  - Product ID
  - Brand ID
  - Applicable price list (tariff)
  - Start and end date of the price validity
  - Final price

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 in-memory database
- Maven
- JUnit (for testing)
- Postman / curl (for manual API testing)

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/danielfdezweb/pricesApp.git
cd prices
```

### 2. Build the Project

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The application will be available at:  
`http://localhost:8080`

---

## 🧠 Notes on the Database

- This project uses an **in-memory H2 database**.
- The database **is not initialized automatically** with pricing data on startup.
- You must **insert the sample data manually** (e.g., using H2 console or via SQL scripts).
- The H2 console is available at:  
  `http://localhost:8080/h2-console`  
  (Default JDBC URL: `jdbc:h2:mem:testdb`)

---

## 🧪 Test Cases

The application includes unit tests validating that the correct price is returned based on the following test cases:

| Test | Date and Time         | Product ID | Brand ID | Expected Price List |
|------|------------------------|------------|----------|----------------------|
| 1    | 2020-06-14 10:00:00    | 35455      | 1        | 1                    |
| 2    | 2020-06-14 16:00:00    | 35455      | 1        | 2                    |
| 3    | 2020-06-14 21:00:00    | 35455      | 1        | 1                    |
| 4    | 2020-06-15 10:00:00    | 35455      | 1        | 3                    |
| 5    | 2020-06-16 21:00:00    | 35455      | 1        | 4                    |

---

## 📬 API Usage

**Endpoint:**  
`GET api/prices`

**Query Parameters:**

- `date` (e.g. `2020-06-14T10:00:00`)
- `productId` (e.g. `35455`)
- `brandId` (e.g. `1`)

**Example Request:**

```http
GET api/prices?date=2020-06-14T10:00:00&productId=35455&brandId=1
```

**Example Response:**

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}

