## Technikon - The Technicians on the Web
 
 
#### Docker
 
Create a ```Docker``` PostgreSQL database:
 
    docker run --name technikon -d -p 5432:5432 -e POSTGRES_PASSWORD=@technikon@@@ -e POSTGRES_USER=team3 -e POSTGRES_DB=technikon postgres:alpine
 
or just ```restart``` it, in case you have already created it, either from a terminal:
 
    docker restart technikon
 
or with the ```play``` button of the Docker Desktop App.
 
---
 
#### Application
 
Run the ```TechnikonApplication``` Java class to initialize the Application.
 
---
 
#### API Endpoints
 
View the list of available endpoint from this Swagger URL:
 
    http://localhost:8080/swagger-ui/index.html
 
---
 
#### Front-End
 
Navigate to the path of the ```front-end``` of the directory:
 
    cd path_to_Technikon\front-end
 
for example:
 
    cd C:\IntelliJprojects\Technikon\front-end
 
and run:
 
    npm i
 
and then:
 
    npm run dev
 
Then the Application URL is exposed:
 
     http://localhost:5173/
