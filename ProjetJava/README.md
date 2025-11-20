# ProjetJava

## Description
ProjetJava is a Java application that manages student information and their grades. It provides functionalities to create student records, assign grades for different subjects, and calculate average grades based on the subjects' coefficients.

## Project Structure
```
ProjetJava
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── projetjava
│   │               ├── App.java
│   │               ├── models
│   │               │   ├── Etudiant.java
│   │               │   └── Matiere.java
│   │               └── utils
│   │                   └── Helpers.java
│   └── test
│       └── java
│           └── com
│               └── projetjava
│                   └── AppTest.java
├── pom.xml
├── .gitignore
└── README.md
```

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Apache Maven

### Installation
1. Clone the repository:
   ```
   git clone https://github.com/yourusername/ProjetJava.git
   ```
2. Navigate to the project directory:
   ```
   cd ProjetJava
   ```
3. Build the project using Maven:
   ```
   mvn clean install
   ```

### Running the Application
To run the application, execute the following command:
```
mvn exec:java -Dexec.mainClass="com.projetjava.App"
```

### Running Tests
To run the unit tests, use the following command:
```
mvn test
```

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.