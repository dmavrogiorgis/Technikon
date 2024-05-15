## Technikon - The Technicians on the Web

Create a docker postgresql database:

    docker run --name technikon -d -p 5432:5432 -e POSTGRES_PASSWORD=@technikon@@@ -e POSTGRES_USER=team3 -e POSTGRES_DB=technikon postgres:alpine
