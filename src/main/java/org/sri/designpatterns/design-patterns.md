### **Design Patterns Summary Table**  

| **Pattern**        | **Category**   | **Use Case** | **Analogy** | **Real-World Application** | **Key Benefits** |
|-------------------|--------------|------------|------------|--------------------------|----------------|
| **Singleton** | Creational | Ensure a single instance of a class | **CEO of a company**: One CEO makes all company-wide decisions | **Database Connection Manager, Logger, Configuration Manager** | Saves memory, avoids redundant instances |
| **Factory Method** | Creational | Create objects without specifying their exact class | **Coffee Machine**: You select a drink type, and it makes the correct one (Latte, Espresso, etc.) | **Database Connection Factory, Payment Gateway Factory** | Encapsulates object creation, flexible instantiation |
| **Builder** | Creational | Create complex objects step by step | **Building a Burger**: Choose bun, meat, toppings separately | **Creating complex objects like HTTP requests, Database Queries** | Step-by-step object creation, readable and scalable |
| **Observer** | Behavioral | One-to-many event notification system | **News Agency**: Sends updates to subscribers via email, SMS, newspaper | **Logging System, Event-Driven Architecture (Spring Boot Events), WebSockets** | Loose coupling, dynamic updates, scalability |
| **Iterator** | Behavioral | Traverse a collection sequentially without exposing its implementation | **Netflix Playlist**: You browse next/previous movies without knowing how the list is stored | **Collections in Java (ArrayList, HashSet), Database ResultSets** | Simplifies data traversal, avoids exposing internal structure |

---

### **Recap of What We Have Discussed**
1. **Singleton Pattern**  
   - Used in Spring Boot for **Database Connection Managers**, **Logging**, **Configuration Management**.  
   - Example: Ensuring a **single instance** of a database connection object.  
   - Alternative: **Enum Singleton** (Thread-safe and prevents reflection attacks).  

2. **Factory Method Pattern**  
   - Used to **create different types of objects dynamically** without modifying client code.  
   - Example: **Database Connection Factory** to support **MySQL, PostgreSQL, MongoDB, etc.**  
   - Works well with **Singleton to manage connections efficiently**.  

3. **Builder Pattern**  
   - Used to **construct complex objects step by step**.  
   - Example: Creating an **HTTP request** or **building a user profile object** in Spring Boot.  
   - Benefits: Improves **readability and maintainability**.  

4. **Observer Pattern**  
   - Used for **event-driven architectures** where multiple objects need updates when an event occurs.  
   - Example: **User Registration System** triggers:
     - **Email Service** (sends welcome email)  
     - **SMS Service** (sends confirmation)  
     - **Logging Service** (logs user registration)  
   - Built into **Spring Boot Application Events** using `@EventListener`.  

5. **Iterator Pattern**  
   - Used for **traversing collections** without exposing internal data structure.  
   - Example: **Netflix’s movie playlist**, **pagination in APIs**.  
   - In Java, it’s used in **List, Set, Map, and Streams**.  
   - Synchronization required in multi-threaded environments.  
