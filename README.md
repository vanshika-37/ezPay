
# Ezpay - Digital Payment Solution

Ezpay is a comprehensive digital payment solution that allows users to securely and efficiently manage their financial transactions. The application provides a seamless experience for initiating, processing, and tracking payments with robust security, real-time updates, and customer support services.

---

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
  - [Backend Setup (Spring Boot)](#backend-setup-spring-boot)
  - [Frontend Setup (React)](#frontend-setup-react)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)

---

## Features (Use Case Study-6 Support Ticket System with Chatbot and Responsive User Interface)
- **Support Ticket System with Chatbot**: 
   - Integrated support system where users can raise tickets for issues or inquiries.
   - **Chatbot** powered by **Dialogflow API** provides automated responses, assisting users with common queries. It also escalates to human support via an automated email when needed.
- **Responsive Design**: Fully responsive UI, ensuring a consistent and user-friendly experience across mobile, tablet, and desktop devices.

---

## Tech Stack

### Frontend: 
- **React** - A JavaScript library for building user interfaces.
- **React Router** - For managing navigation.
- **Bootstrap/Material-UI** - For responsive design and UI components.

### Backend:
- **Spring Boot** - For creating RESTful APIs.
- **Hibernate/JPA** - For database interaction.
- **Oracle SQL** - As the relational database.

### Support System & Chatbot:
- **Dialogflow API** - Integrated chatbot to handle user inquiries and assist with support tickets.

---

## Installation

### Backend Setup (Spring Boot)

1. **Clone the repository**:
   ```bash
   git clone http://hcltechgitlab1.career-shaper.com/Chandrasekhar_Baratam/ezpay_backend.git
   
   ```
2. **Navigate to the backend directory**:
   ```bash
   cd ezpay_backend
   git checkout Squad-6
   ```
3. **Install the required dependencies**:
   ```bash
   mvn install
   ```
4. **Configure Oracle SQL Database**:
   - Update the `application.properties` or `application.yml` file with your **Oracle SQL** credentials.
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
5. **Run the backend**:
   ```bash
   mvn spring-boot:run
   ```

---

### Frontend Setup (React)

1. **Clone the frontend repository**:
   ```bash
   git clone http://hcltechgitlab1.career-shaper.com/Chandrasekhar_Baratam/ezpay_frontend.git
   ```
2. **Navigate to the frontend directory**:
   ```bash
   cd ezpay_frontend
   git checkout Squad-6
   ```
3. **Install dependencies**:
   ```bash
   npm install
   ```
4. **Run the application**:
   ```bash
   npm start
   ```

---

## Configuration
1. **Dialogflow Configuration**:  
   - Set up a **Dialogflow agent** and integrate the **API** keys in your backend for chatbot interaction.
   - Add your API key to the resources directory and add path to code in dialogflow service.
---

## Running the Application

1. **Backend**:  
   Start the backend Spring Boot server using:
   ```bash
   mvn spring-boot:run
   ```

2. **Frontend**:  
   Start the React application using:
   ```bash
   npm start
   ```

---

## API Endpoints
- **Create Ticket**: `/api/support/create`
- **Get Ticket**: `/api/support/gettick/user/{userId}`
- **Delete Ticket**: `/api/support/delete/{ticketId}`
- **Resolve Ticket**: `/api/support/resolveticket/{ticketId}`
- **Get Chat**: `/api/support/getchat/{ticketId}*`
- **Get Chat Response**: `/api/support/sendusermessage/{ticketId}*`

---


