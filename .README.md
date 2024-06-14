# Expense Tracker

## Overview

Expense Tracker is a comprehensive application designed to help users manage their finances by tracking expenses efficiently. This tool is perfect for individuals looking to gain insights into their spending habits and make informed budgeting decisions.

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- Java 21
- Gradle
- Docker
- Docker Compose
- Node.js (version 20.10.0 or later)
- npm (version 10.5.0 or later)

## Running Locally

1. Clone the repository: `git clone https://github.com/viverbungag/Expense-Tracker`
2. Navigate to the project directory: `cd Expense-Tracker`

### Backend

1. Navigate to the backend directory: `cd backend`
2. Build using gradle: `./gradlew assemble`
3. Run the application: `java -jar build/libs/Expense-Tracker-0.0.1-SNAPSHOT.jar`

### Frontend

1. Navigate to the frontend directory: `cd frontend`
2. Install dependencies: `npm install`
3. Run the frontend: `npm run dev`

## Building the backend Docker Image

1. Make sure you are in the backend directory
2. Build the Docker image: `docker build -t countable/backend .`

## Running the containers using docker compose

1. Make sure you are in the backend directory
2. Make sure you have built the Backend Docker image.
3. Run the containers:`docker compose -p countable up -d`
