# 🎄 Advent of Code 2025 – Java Automation Setup

A **zero-friction, fully automated Advent of Code setup in Java**, designed for speed, cleanliness, and daily usability.

This project:

* Automatically fetches puzzle inputs
* Caches inputs locally
* Auto-generates daily solution templates
* Benchmarks Part 1, Part 2, and total runtime
* Runs seamlessly from **IntelliJ IDEA** and terminal
* Requires **no reconfiguration after initial setup**

---

## ✨ Features

* 🚀 **One-command daily workflow**
* 📥 **Automatic input download** (with caching)
* 🧱 **Auto-generate DayXX templates**
* ⏱ **Built-in benchmarking**
* 🧠 **Reflection-based runner** (no switch cases)
* 💻 **Pure Java (no external libraries)**
* 🪟 **Windows-friendly**
* 🔒 **Session-safe configuration**

---

## 📁 Project Structure

```
aoc-2025/
│
├── inputs/                  # auto-downloaded inputs (gitignored)
│   └── day01.txt
│
├── src/
│   └── aoc/
│       ├── Main.java
│       │
│       ├── config/
│       │   └── AoCConfig.java
│       │
│       ├── core/
│       │   ├── Day.java
│       │   ├── Runner.java
│       │   ├── Generator.java
│       │   └── Benchmark.java
│       │
│       ├── util/
│       │   └── InputFetcher.java
│       │
│       └── days/
│           ├── Day01.java
│           ├── Day02.java
│           └── ...
│
├── aoc.bat                  # terminal helper script
└── README.md
```

---

## 🧰 Requirements

* Java **11+** (Java 17 recommended)
* **IntelliJ IDEA** (Community or Ultimate)
* Internet access (for first-time input download)

---

## 🔐 One-Time Setup

### 1️⃣ Clone the Repository

```bash
git clone <your-repo-url>
cd aoc-2025
```

---

### 2️⃣ Configure Session Cookie (IMPORTANT)

Create or edit:

```java
src/aoc/config/AoCConfig.java
```

```java
package aoc.config;

public final class AoCConfig {
    public static final int YEAR = 2025;
    public static final String SESSION = "PASTE_YOUR_SESSION_COOKIE_HERE";
}
```

#### How to get the session cookie:

1. Log in to Advent of Code
2. Open browser DevTools → Application → Cookies
3. Copy **only** the value of `session`
4. Paste it above (no quotes, no `session=` prefix)

⚠️ **Never commit this file**

---

### 3️⃣ IntelliJ Configuration (One-Time)

#### Mark Source Root

```
Right-click src → Mark Directory as → Sources Root
```

#### Set Java Version

```
File → Project Structure
Project SDK: Java 17
Language Level: 17
```

#### Ensure Terminal Uses Project JDK

```
Settings → Tools → Terminal
☑ Use Project SDK for terminal
```

#### Enable Auto-Compilation (Recommended)

```
Settings → Build, Execution, Deployment → Compiler
☑ Build project automatically

Advanced Settings:
☑ Allow auto-make to start even if application is running
```

---

## 🚀 How to Use (Daily Workflow)

All commands are run from the **IntelliJ Terminal**.

---

### 🧱 Generate a Day Template

```bash
aoc gen 5
```

Creates:

```
src/aoc/days/Day05.java
```

---

### ✍ Solve the Puzzle

Edit:

```java
public class Day05 implements Day {

    private final String input;

    public Day05() throws Exception {
        input = InputFetcher.fetch(5);
    }

    public Object part1() {
        return 0;
    }

    public Object part2() {
        return 0;
    }
}
```

---

### ▶ Run a Day

```bash
aoc run 5
```

Output example:

```
Part 1: 123456 (4 ms)
Part 2: 654321 (9 ms)
Total: 14 ms
```

✔ Input auto-downloads on first run
✔ Cached in `inputs/` for future runs

---

## 🪄 Terminal Helper Script (`aoc.bat`)

For Windows, this script allows running without worrying about classpaths.

```bat
@echo off
set MODULE=aoc-2025
java -cp out\production\%MODULE% aoc.Main %*
```

Usage:

```bash
aoc gen 7
aoc run 7
```

---

## 🧠 How It Works (Internals)

* **Generator** creates DayXX classes
* **Runner** loads day classes via reflection
* **InputFetcher** downloads + caches inputs
* **Benchmark** measures runtime per part
* IntelliJ handles compilation automatically

---

## 🛠 Troubleshooting

### ❌ Input Fetch Fails

* Session cookie expired → re-copy cookie
* Missing `User-Agent` → already handled in code

### ❌ Class Not Found

* Ensure file name is `Day05.java`, not `Day5.java`
* Ensure package is `aoc.days`

### ❌ Runtime Version Mismatch

* Ensure Project SDK, Module SDK, and Terminal Java are the same
* Recommended: Java 17 everywhere

### ❌ Run Works Only After Rebuild

* Enable **Build project automatically**
* Or use IntelliJ Run button once

---

## 🏁 Final Daily Flow (Minimal)

```bash
aoc gen <day>
(edit code)
aoc run <day>
```

That’s it.

---

## 🧑‍💻 Author Notes

This setup is designed to:

* Scale cleanly to Day 25
* Avoid daily setup fatigue
* Be portfolio-ready
* Be competitive-speed friendly

Happy coding & Merry AoC 🎄🚀
