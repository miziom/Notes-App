# Notes-App

## Required

1. Java 11.0.2
2. MySQL (with MySQL Workbench)
3. node.js
4. Postman

## How to?

1. #### Clone or download this repository.

2. #### Setup Spring Boot Note-Api

   1. #### Setup database

      - ##### Option A (RECOMENDED)

        1. Open MySQL Workbench and log to your MySQL Connection

        2. Open added SQL Script --> `setup_db_script.sql`
        3. Execute Script

      - ##### Option B

        Run command 

        `mysql --host=localhost --user=your_username --password=your_password  -e "setup_db_script.sql"`

        Change `your_username` and `your_password` to your login and password.

   2. #### Run project

      - Open `notes-api` directory

      - check `src/main/resources/application.properties`

        set your mySQL *username*, *password* and *url*

      - In terminal run command below to build

        `mvnw clean install`

      - In terminal run command below to boot run

        `mvnw spring-boot:run`

        Your api run on localhost: 8080

3. #### Setup Angular CLI Api

   - open `notes-ui` directory

   - In terminal run command below to build

     `npm install`

   - In terminal run command below to run

     `ng serve`

   - Navigate to `http://localhost:4200/`

## Example Usage

When Spring Boot Note-Api is running use Postman.

#### Get All Notes 

- HTTP request methods:  GET

- Request URL:  http://localhost:8080/notes/all

- Response:

  ```
  [
      {
          "id": 1,
          "version": 3,
          "title": "Important note",
          "content": "Very important note.",
          "createdDate": "2021-02-26T12:58:31.114346",
          "modifiedDate": "2021-02-26T20:45:15.055414"
      },
      {
          "id": 2,
          "version": 1,
          "title": "Note 2",
          "content": "This is content of note note 2.",
          "createdDate": "2021-02-26T12:58:41.622621",
          "modifiedDate": "2021-02-26T12:58:41.622621"
      },
      {
          "id": 4,
          "version": 1,
          "title": "Note 4",
          "content": "This note is better then other.",
          "createdDate": "2021-02-26T20:44:44.621908",
          "modifiedDate": "2021-02-26T20:44:44.621908"
      }
  ]
  ```

#### Get Note By Id 

- HTTP request methods:  GET

- Request URL:  http://localhost:8080/notes?id=1

- Response:

  ```
  {
      "id": 1,
      "version": 3,
      "title": "Important note",
      "content": "Very important note.",
      "createdDate": "2021-02-26T12:58:31.114346",
      "modifiedDate": "2021-02-26T20:45:15.055414"
  }
  ```

#### Get Note History By Id 

- HTTP request methods:  GET

- Request URL:  http://localhost:8080/historical?id=1

- Response:

  ```
  [
      {
          "id": 1,
          "version": 1,
          "title": "Some note",
          "content": "Very important note.",
          "createdDate": "2021-02-26T12:58:31.114346",
          "modifiedDate": "2021-02-26T20:45:15.055414"
      },
      {
          "id": 1,
          "version": 2,
          "title": "Note 1",
          "content": "Very important note.",
          "createdDate": "2021-02-26T12:58:31.114346",
          "modifiedDate": "2021-02-26T20:45:15.055414"
      },
      {
          "id": 1,
          "version": 3,
          "title": "Important note",
          "content": "Very important note.",
          "createdDate": "2021-02-26T12:58:31.114346",
          "modifiedDate": "2021-02-26T20:45:15.055414"
      }
  ]
  ```

#### Add Note

- HTTP request methods:  POST

- Request URL:  http://localhost:8080/notes

- Body (JSON):

  ```
  {
      "title": "Example Title",
      "content": "Some Content"
  }
  ```

- Response:

  ```
  {
      "id": 4,
      "version": 1,
      "title": "title4",
      "content": "content4",
      "createdDate": "2021-02-26T20:44:44.6219078",
      "modifiedDate": "2021-02-26T20:44:44.6219078"
  }
  ```

**When all fields are not filled:**

- Body (JSON):

  ```
  {
      "content": "content"
  }
  ```

- Response:

  ```
  {
      "message": "Title can't be blank/empty/null"
  }
  ```

When field is empty/blank:

- Body (JSON)

  ```
  {
      "title": "title",
      "content": " "
  }
  ```

- Response:

  ```
  {
      "message": "Content can't be blank/empty/null"
  }
  ```

#### Update Note By Id

- HTTP request methods:  PUT

- Request URL:  http://localhost:8080/notes?id=2

- Body (JSON):

  ```
  {
      "title": "Update Note 2"
  }
  ```

- Response:

  ```
  {
      "id": 2,
      "version": 2,
      "title": "Update Note 2",
      "content": "This is content of note note 2.",
      "createdDate": "2021-02-26T12:58:41.622621",
      "modifiedDate": "2021-02-26T23:13:53.640172"
  }
  ```

#### Delete Note By Id

- HTTP request methods:  DELETE

- Request URL: http://localhost:8080/notes?id=2
- Response: NONE

##### When is not note with id (works also with get and put)

- HTTP request methods:  DELETE

- Request URL: http://localhost:8080/notes?id=33

- Response: 

  ```
  {
      "message": "Could not found note with id: 33"
  }
  ```

  