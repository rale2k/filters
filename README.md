# filters
An application which has a button to create new filter and representation of already created filters.

## Frontend
TypeScript + React 19 + Vite + MaterialUI

No tests

NodeJS 22 recommended

```sh 
npm i 
npm run dev
```

### Comments

The following acceptance criteria seemed conflicting to me (should it be resizable or fixed??):

> - Filter dialog **should have fixed size** and be configurable to operate in modal/non-modal mode
> - Let the user **resize only the height**

I chose to make it fixed, following the point:

> - If there are more rows than dialog can fit, scrolling should appear

The design image also showed a select option, but as this was not mentioned in the requirements so I omitted that also.

## Backend
Spring Boot 3.5.2 + H2 DB + Flyway

Tests

Java 21 recommended

```sh 
chmod +x ./gradlew
./gradlew bootRun
```

## Time spent

```
28.06.2025 - 1.5h - checking out requirements, boilerplate setup
29.06.2025 - 8h - dev, test

i set myself an arbitrary deadline on sunday to not spend too much time
```
