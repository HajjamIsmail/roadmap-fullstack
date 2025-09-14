
---

# Documentation – GitHub Actions for Mini Java Project

## 1️⃣ Role of GitHub Actions

GitHub Actions is a service integrated into GitHub that allows you to **create automated workflows** for your repository.
These workflows are triggered automatically by certain events (push, pull request, release, etc.) and can perform tasks such as:

* Compiling your code
* Running automated tests
* Deploying your application
* Linting or analyzing your code

**Main benefits:**

* CI/CD directly integrated with GitHub
* Automatic execution when an event occurs
* Detailed logs with clear step-by-step visualization
* Isolation by workflow or job for different projects

---

## 2️⃣ Key Basic Concepts

| Concept         | Description                                                                                                    |
| --------------- | -------------------------------------------------------------------------------------------------------------- |
| **Workflow**    | YAML file in `.github/workflows/` that defines an automated process.                                           |
| **Job**         | A set of steps executed on a runner (virtual machine). Each job can be independent or dependent on others.     |
| **Step**        | A single task within a job, e.g., checkout, build, test. Each step can use a GitHub Action or a shell command. |
| **Runner**      | Virtual machine (Ubuntu, Windows, macOS) that executes the jobs.                                               |
| **Trigger**     | Event that starts the workflow (`push`, `pull_request`, `schedule`, etc.).                                     |
| **Action**      | Reusable block that performs a specific task (e.g., `actions/checkout@v3`).                                    |
| **Path Filter** | Limits the workflow to run only when certain files or folders change.                                          |

---

## 3️⃣ Practical Example: Mini Java Project

**Project structure:**

```
global-repo/
├─ week1/day7-github-action/
├   ├─ pom.xml
├   ├─ src/
├   │  ├─ main/java/App.java
├   │  └─ test/java/AppTest.java
└─ .github/workflows/ci-mini-java.yml
```

* Minimal Maven Java project
* JUnit 5 tests included
* GitHub Actions workflow automatically compiles and tests the project

---

## 4️⃣ Content of `.github/workflows/ci-mini-java.yml`

```yaml
name: Java CI - Mini Project

on:
  push:
    paths:
      - 'week1/day7-github-action/**'
  pull_request:
    paths:
      - 'week1/day7-github-action/**'

jobs:
  build-and-test:
    runs-on: ubuntu-latest

    steps:
    - name: Checkout repository
      uses: actions/checkout@v3

    - name: Set up JDK 21
      uses: actions/setup-java@v3
      with:
        java-version: '21'
        distribution: 'temurin'

    - name: Build Project
      run: mvn clean install
      working-directory: week1/day7-github-action

    - name: Run Tests
      run: mvn test
      working-directory: week1/day7-github-action
```

---

## 5️⃣ Line-by-Line Explanation

| Line / Block                                  | Explanation                                                        |
| --------------------------------------------- | ------------------------------------------------------------------ |
| `name: Java CI - Mini Project`                | Name of the workflow displayed in GitHub Actions.                  |
| `on:`                                         | Defines the **triggers** that start the workflow.                  |
| `push:`                                       | Trigger the workflow on every push to the branch.                  |
| `paths:`                                      | Limit the workflow to files changed in `week1/day7-github-action`. |
| `pull_request:`                               | Trigger workflow on a pull request, also limited to the same path. |
| `jobs:`                                       | Defines the jobs in the workflow.                                  |
| `build-and-test:`                             | Job ID; each job has its own steps.                                |
| `runs-on: ubuntu-latest`                      | Runner: Ubuntu virtual machine provided by GitHub.                 |
| `steps:`                                      | List of steps in the job.                                          |
| `- name: Checkout repository`                 | Step to fetch the repository code.                                 |
| `uses: actions/checkout@v3`                   | Official GitHub Action for checking out code.                      |
| `- name: Set up JDK 21`                       | Step to install Java 21 on the runner.                             |
| `uses: actions/setup-java@v3`                 | Official action to configure Java.                                 |
| `with:`                                       | Parameters passed to the action: Java version and distribution.    |
| `- name: Build Project`                       | Step to compile the Maven project.                                 |
| `run: mvn clean install`                      | Shell command to build the project and generate JAR files.         |
| `working-directory: week1/day7-github-action` | Directory where the command should be executed.                    |
| `- name: Run Tests`                           | Step to execute JUnit tests.                                       |
| `run: mvn test`                               | Maven command to run tests.                                        |
| `working-directory: week1/day7-github-action` | Same directory as the build step, because the pom.xml is there.    |

---

## 6️⃣ Workflow Summary

1. **Trigger:** Push or pull request on the Java project
2. **Job `build-and-test`:** Runs on Ubuntu runner
3. **Steps:**

   * Checkout the code
   * Set up Java 21
   * Maven compilation (Build Project)
   * Run JUnit tests (Run Tests)
4. **Clear logs:** Each step is separate in GitHub Actions interface

---

