**About the app**

The application is a web service. It does no real “transactional” work, just emulate the financial transactions logic (debit and credit).
It emulates debit and credit operations for a single user, so always has just one financial account.
No security.
No real persistence. 

**Stack**
- Backend: Java 8, Dropwizard
- Frontend: TypeScript, React 16.9, react hooks, fetch, vanilla css + classnames

**Build**

Preconditions:
- Java 8
- Maven 3.6.0
- NPM 6.14.5

Steps to build app:
1. navigate to `account-web-application/frontend/accounting-notebook`
2. run `npm install`
3. run `npm run-script build`
4. navigate to `account-web-application`
5. run `mvn clean package -P include-webapp`

If frontend is not really needed then `mvn clean package` is enough to build the backend with API
Now `backend/target` folder contains jar which in which everything including frontend static content is packaged within

**Run**

To run the application one will need:
 1. application config, which can be found in `backend/conf`
 2. jar file
 3. run command which will look something like follows 
 `java -jar target/backend-1.0-SNAPSHOT.jar server conf/application.yml`
 
 App ui is available on `http://localhost:8080/`
 API is available on `http://localhost:8080/api`

**PLEASE NOTE!** java 8 is required to run the app. No guarantees with the 8+ versions.

**API**

- GET     /api/ - `returns account balance as text`
- GET     /api/transactions - `returns list of transaction, sorted from latest to oldest, latest come first`
- POST    /api/transactions - `accepts transaction. e.g. {type: 'debit', amount: 25}`
- GET     /api/transactions/{transactionId} - `returns transaction details`

**UI**

Static content is being served from the root of the context. 
Meaning if one serves an app on `http://localhost:8080` then ui is available on `http://localhost:8080/`
