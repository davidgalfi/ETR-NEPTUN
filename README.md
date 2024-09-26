# ETR-NEPTUN 🌍

## Overview
ETR-NEPTUN is a project developed as part of a database management and design course at the University of Szeged. It serves as a replica of the Neptun Education System, a platform used by students and teachers to manage courses and exams. The system distinguishes between two types of users: teachers and students. Teachers can create courses, while students can access these courses if they meet the specified requirements. This tool aims to streamline educational interactions by providing a user-friendly interface for both students and faculty members. This description highlights the educational context and functionality of the project, focusing on its role as a Neptun system replica designed for course management.

## Table of Contents 📚
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Installation 🛠️

To set up the ETR-NEPTUN project locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/davidgalfi/ETR-NEPTUN.git
   cd ETR-NEPTUN
2. Navigate to the project directory:
   ```bash
    cd ETR-NEPTUN
    ```
3. Set up the database:
   - Use SQL to create the necessary database schema.
   - Populate the database with initial data required for operation.
   - 
## Usage 📈

1. Build the project:
   
   ```bash
    mvn clean install
    ```
2. Ensure that your database is running and accessible by the application.  

3. Compile and run NeptunApplication.java:

    ```bash
    javac src/main/java/com/example/NeptunApplication.java
    java -cp src/main/java com.example.NeptunApplication
    ```

3. Adjust parameters in the `config.json` file to simulate different drug treatment regimens.

## Contributing 🤝

Contributions are welcome! Feel free to submit issues or pull requests. Please make sure to follow the coding standards and include tests when adding new features.

## License 📄

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for more details.

---
 
