## Project Structure

```text
AutoFix-Manager/
├── .gitattributes
├── .gitignore
├── .mvn/wrapper/maven-wrapper.properties
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/autofix/
    │   │   ├── AutofixApplication.java
    │   │   ├── controller/
    │   │   │   ├── OrderController.java
    │   │   │   ├── PublicTrackingController.java
    │   │   │   └── VehicleController.java
    │   │   ├── dto/
    │   │   │   ├── ChangeStatusRequest.java
    │   │   │   ├── CreateOrderRequest.java
    │   │   │   ├── OrderResponse.java
    │   │   │   ├── PublicOrderResponse.java
    │   │   │   └── VehicleHistoryResponse.java
    │   │   ├── exception/
    │   │   │   ├── ConflictException.java
    │   │   │   └── NotFoundException.java
    │   │   ├── model/
    │   │   │   ├── Customer.java
    │   │   │   ├── Order.java
    │   │   │   ├── RepairStatus.java
    │   │   │   └── Vehicle.java
    │   │   ├── repository/
    │   │   │   ├── CustomerRepository.java
    │   │   │   ├── OrderRepository.java
    │   │   │   └── VehicleRepository.java
    │   │   └── service/
    │   │       ├── OrderService.java
    │   │       └── VehicleService.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/com/autofix/
            └── AutofixApplicationTests.java
```

## How to Run

### Prerequisites
- **Java (JDK 17 or JDK 21)** installed and configured in your IDE / `PATH`
- **Bundled Maven 3 (version 3.9.9)** (for example, IntelliJ IDEA's bundled Maven 3.9.9) or Maven Wrapper (`./mvnw` / `mvnw.cmd`)
- **Git** (optional if downloading a ZIP archive)

### 1. Clone the repository
```bash
git clone [https://github.com/kertrick/AutoFix-Manager-.git]
cd AutoFix-Manager-
```

### 2. Check database configuration
Open `src/main/resources/application.properties` and verify the SQLite database path and server settings. The SQLite database file will be created automatically when the web application starts if configured there.

### 3. Run the web application

#### Option A: Run via `AutofixApplication.java` using bundled Maven 3 (3.9.9)
1. Open the project in your IDE and ensure it uses **bundled Maven 3 (3.9.9)** to import dependencies from `pom.xml`[cite: 3].
2. Open `src/main/java/com/autofix/AutofixApplication.java`[cite: 3].
3. Run `AutofixApplication.main()` (using the IDE run button or Maven 3.9.9 `spring-boot:run` configuration).

#### Option B: Run from terminal using Maven 3.9.9 / Maven Wrapper
```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### 4. Open the website
Wait for the console log showing `Started AutofixApplication`, then open your browser and go to:
```text
http://localhost:8080
```








# AutoFix-Manager-1

z1-[Karta_Projektu_AutoFix_Manager.pdf](https://github.com/user-attachments/files/34130057/Karta_Projektu_AutoFix_Manager.pdf)

z2
Temat: Dopełnienie zadania 2 – AutoFix Manager (Backlog, Sprinty, DoD)Link do repozytorium i tablicy projektu:https://github.com/kertrick/AutoFix-Manager- 
        Opis wykonania zadania:
  Backlog & User Stories: Utworzyliśmy co najmniej 8 User Stories z określonymi priorytetami (High, Medium, Low).  
  Sprinty i Tablica: Założyliśmy tablicę Kanban w GitHub Projects. Podzieliliśmy pracę na sprinty, a zadania z pierwszego Sprintu przemieściliśmy do kolumny In Progress / Todo.   
  Definition of Done (DoD): Zdefiniowaliśmy kryteria ukończenia zadań i umieściliśmy je w opisie projektu/README

