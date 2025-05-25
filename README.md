# CURA Healthcare Selenium Test Suite

## Overview

This project is an automated test suite for the [CURA Healthcare Service Demo Site](https://katalon-demo-cura.herokuapp.com/), using **Java**, **Selenium**, **JUnit 5**, and **Gradle**.  
It demonstrates web UI automation, page object pattern, random data, browser configuration, and more.

---

## Getting Started

### Prerequisites

- Java 17+
- Gradle (or use the provided Gradle Wrapper)
- Internet connection for downloading dependencies

### Installation

1. **Clone the repository**  
   ```sh
   git clone https://github.com/yourusername/your-repo-name.git
   cd your-repo-name

# check config/test-config.properties
base.url=https://katalon-demo-cura.herokuapp.com/profile.php#login
email=John Doe
password=ThisIsNotAPassword

# You can run all tests in the suite with:

gradle clean test --info --tests "tests.AllTestsSuite"



