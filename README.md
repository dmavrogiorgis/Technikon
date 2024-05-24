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

