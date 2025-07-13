# 📝 Task Tracker

A simple and extensible **Task Tracker** built in Java with support for **Command Line Interface (CLI)** and designed to be easily extendable to support APIs.

---

## 📦 Features

- Add, update, delete tasks
- Mark tasks as `DONE`, `IN PROGRESS`, or `NOT DONE`
- List tasks (with optional filters by status)
- Store tasks in a JSON file using Gson
- Command parsing with support for quoted task names
- Extensible to support API mode via `OperationMode` interface

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17 or above
- Maven (optional, if using for dependencies)

### 🔧 Configuration

Edit the `configuration.properties` file to set the path for storing task data:

```properties
json.file.path=tasks.json

```

## 📂 Project Structure
```
Task_Tracker/
├── configuration.properties # Application config file
├── tasks.json # JSON file to persist tasks
├── README.md # Project documentation
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── tommy/
│                   ├── TaskTracker.java # Main runner class
│                   ├── TaskManager.java # Logic layer
│                   ├── base/ # Enums, modes (CLI/API), status
│                   ├── models/ # Task model
│                   ├── parser/ # Argument and command parsing
│                   ├── persist/ # File I/O and persistence
│                   └── exception/ # Custom exceptions
```

---

## 🖥️ CLI Usage

Run the Task Tracker in console mode:

```bash
java -cp <classpath> org.tommy.TaskTracker console
```

## 💡 Supported Commands
```declarative
task-cli add "Task description"
task-cli update <taskId> "New task description"
task-cli delete <taskId>
task-cli mark-in-progress <taskId>
task-cli mark-done <taskId>
task-cli list [done|not-done|in-progress]
task-cli help
task-cli exit
```

## 🛠 Design Overview
* **TaskManager** handles all task-related operations.

* **TaskSaver** is the interface for persistence (currently implemented with FileSaver).

* **CliMode** implements OperationMode for CLI execution.

* **TaskTracker** is the entry point.

* **Gson** is used for serialization/deserialization of tasks.

## 📦 Dependencies

* Gson 2.10.1

* Apache Commons Lang (for StringUtils)