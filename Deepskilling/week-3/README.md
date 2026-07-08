# Master Workspace Manual: Week 3 Training Module

This unified training workspace consolidates all requested design architectures into separate, isolated project runtimes.

---

## 📁 Project Architecture Portfolio

```text
week-3/
├── README.md                          <-- (This file)
├── 📂 Assignment-1-Core-XML/          <-- Pure IoC & Bean configurations
├── 📂 Assignment-2-Boot-Logging/      <-- Spring Boot & Custom Logging Frameworks
├── 📂 Assignment-3-REST-Testing/      <-- Web Endpoints & Automated MockMVC Tests
└── 📂 Assignment-4-JWT-Security/      <-- Spring Security & Stateless JWT Filters
```
# 🛠️ Step-by-Step Assignment Execution Manual
## 📑 1. Assignment-1-Core-XML
Concepts Demonstrated: ClassPathXmlApplicationContext initialization, bean instantiation matching scopes (Singleton vs Prototype).

### Execution Commands:

Bash
cd Assignment-1-Core-XML
mvn clean package
mvn exec:java -Dexec.mainClass="com.cognizant.springlearn.SpringCoreApplication"
Expected Visual Output Screenshot Simulation:

### Plaintext
Parsed Date: Mon Dec 31 00:00:00 UTC 2018
--- Country Demonstration ---
Inside Country Constructor.
Are both instances identical? (Singleton Check): true
Loaded Countries: [Country [code=IN, name=India], Country [code=US, name=United States], ...]

# 📑 2. Assignment-2-Boot-Logging
Concepts Demonstrated: Configuration properties routing, custom log pattern interpolation (yyMMdd|HH:mm:ss.SSS|thread|level|logger|msg).

### Execution Commands:

Bash
cd ../Assignment-2-Boot-Logging
mvn spring-boot:run
Expected Visual Output Screenshot Simulation:

Plaintext
260708|17:45:12.102|main                | INFO|o.s.b.SpringApplication  |logStarting             |Starting SpringLearnApplication v0.0.1-SNAPSHOT
260708|17:45:12.105|main                | INFO|c.c.s.SpringLearnApplicat|main                    |START
260708|17:45:13.826|main                |DEBUG|c.c.s.SpringLearnApplicat|displayDate             |Current Context operational date loaded successfully.
260708|17:45:13.827|main                | INFO|c.c.s.SpringLearnApplicat|main                    |END


# 📑 3. Assignment-3-REST-Testing
Concepts Demonstrated: Jackson data conversion engine, @PathVariable tracking maps, path fallback exceptional status code routes, MockMVC orchestration.

### Execution Commands:

Bash
cd ../Assignment-3-REST-Testing
mvn clean test
Expected Visual Output Screenshot Simulation:

### Plaintext
[INFO] Running com.cognizant ringlearn.SpringLearnApplicationTests
260708|17:49:02.311|main                | INFO|o.s.t.c.s.AbstractContext|     |Started SpringLearnApplicationTests in 1.42 seconds
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
# 📑 4. Assignment-4-JWT-Security
Concepts Demonstrated: Stateless Filter execution pipelines, Base64 authentication credential extraction, Jwts Token cryptographic signing.

### Execution Commands:

Bash
cd ../Assignment-4-JWT-Security
mvn spring-boot:run
Step A: Testing Basic Protection Fallback (Expected 401 Unauthorized Error)
Bash
curl -s http://localhost:8090/countries
Output:

JSON
{"timestamp":"2026-07-08T17:52:10.112+0000","status":401,"error":"Unauthorized","message":"Unauthorized","path":"/countries"}
Step B: Token Generation Extraction Pipeline
Bash
curl -s -u user:pwd http://localhost:8090/authenticate
Output Response:

JSON
{"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNTcwMzc5NDc0LCJleHAiOjE1NzAzODA2NzR9.t3LRvlCV-hwKfoqZYlaVQqEUiBloWcWn0ft3tgv0dL0"}
Step C: Authenticated Request via Bearer Token Validation Channel
Bash
curl -s -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNTcwMzc5NDc0LCJleHAiOjE1NzAzODA2NzR9.t3LRvlCV-hwKfoqZYlaVQqEUiBloWcWn0ft3tgv0dL0" http://localhost:8090/countries
Output Response:

JSON
[{"code":"IN","name":"India"},{"code":"US","name":"United States"},{"code":"DE","name":"Germany"},{"code":"JP","name":"Japan"}]