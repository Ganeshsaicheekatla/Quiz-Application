# Quiz Application - Spring Boot Microservices Architecture

This project is a **Quiz Application** built using **Spring Boot** and follows a microservices-based architecture. It provides functionalities for managing quizzes and questions with support for difficulty levels, categories, and real-time scoring. The application is designed to be scalable and leverages the **Eureka Service Registry** for service discovery.

---

## Features

1. **Service-Oriented Architecture**:
   - Divided into distinct services like `quiz-service` and `question-service` for better modularity and scalability.

2. **Quiz Service**:
   - Create quizzes based on a specific category or difficulty level.
   - Retrieve questions for a quiz by quiz ID.
   - Submit a quiz and calculate the score based on user responses.

3. **Question Service**:
   - Add, update, delete, and retrieve questions.
   - Fetch random questions by category or difficulty level.
   - Generate a set of questions dynamically for a quiz.
   - Supports `DifficultyLevel` enumeration (e.g., EASY, MEDIUM, HARD).

4. **Service Registry**:
   - Integrated with **Eureka** for service discovery and fault tolerance.

5. **RESTful APIs**:
   - Exposes endpoints for easy integration with frontend or third-party services.

6. **DTO Usage**:
   - Implements Data Transfer Objects (DTOs) for secure and efficient data handling between services.

7. **Technologies Used**:
   - **Spring Boot** for rapid application development.
   - **Eureka** for service discovery and registration.
   - **REST APIs** for communication between services.
   - **Java** as the core programming language.

---

## Project Structure

1. **Service Registry**:
   - Handles the discovery of services and provides centralized routing.

2. **Quiz Service**:
   - Manages quizzes, including creation, fetching, and scoring.

3. **Question Service**:
   - Handles CRUD operations on questions and supports random question generation.

---

## Endpoints

### Quiz Service:
- **`POST /quiz/create`**: Create a quiz with a title, category, and number of questions.
- **`POST /quiz/create/difficultyLevel`**: Create a quiz based on difficulty level.
- **`POST /quiz/get/{id}`**: Fetch all questions for a specific quiz.
- **`POST /quiz/submit/{id}`**: Submit a quiz and calculate the score.

### Question Service:
- **`GET /question/getAllQuestions`**: Retrieve all questions.
- **`GET /question/category/{category}`**: Retrieve questions by category.
- **`POST /question/addQuestion`**: Add a new question.
- **`PUT /question/updateQuestion/questionId/{questionId}`**: Update a question by ID.
- **`DELETE /question/deleteQuestion/questionId/{questionId}`**: Delete a question by ID.
- **`GET /question/generate`**: Fetch random questions by category.
- **`GET /question/generateByDifficulty`**: Fetch random questions by category and difficulty level.
- **`POST /question/getQuestions`**: Retrieve questions by their IDs.
- **`POST /question/getScore`**: Calculate the score based on user responses.

---

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/ganeshsaicheekatla/Quiz-Application.git
