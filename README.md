Online Exam Question Bank System – Java Console Application

**Project Overview**

The Online Exam Question Bank System is a Java-based console application designed to manage exam questions and test papers efficiently.
This project demonstrates the implementation of:
Object-Oriented Programming (OOP)
Custom Exception Handling
Layered Architecture (App → Service → DAO → Bean → Util)
Business rule validation
Question pool management logic
The system allows administrators to add questions, create test papers, and manage question removal with proper validations and constraints.

**Features:**

**Add New Question:**
Add ACTIVE questions to the question bank
Fields include:
Question ID
Subject
Topic
Difficulty (EASY / MEDIUM / HARD)
Marks
Validates input before adding
Create Test Paper (Draft Mode)

**Create test paper based on:**

Subject
Total marks
Difficulty distribution (e.g., EASY=2,MEDIUM=8,HARD=0)
Checks if sufficient ACTIVE questions are available
Throws custom exception if question pool is insufficient

**Remove Question**

Remove a question from the question bank
Prevents deletion if:
Question exists in a PUBLISHED test paper
Throws custom exception to maintain integrity

**Technologies Used**

Java (JDK 8+)
Eclipse IDE
OOP Principles
Java Collections
Custom Exception Handling

**Learning Objectives**

This project helps in understanding:
Clean layered architecture
Separation of concerns
Exception-driven business logic
Input validation
Real-world exam management workflow simulation

**How to Run**

Open the project in Eclipse.
Ensure JDK is configured properly.
Run ExamMain.java.
Observe console output.

**OUTPUT:**

<img width="1573" height="951" alt="image" src="https://github.com/user-attachments/assets/1145ab3b-feb8-488e-95d8-22984e2fa261" />

<img width="1592" height="973" alt="image" src="https://github.com/user-attachments/assets/8da8d23d-12b6-4c54-943b-2e2c185481bb" />

