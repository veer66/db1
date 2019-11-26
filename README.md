__db1__ is an example project for learning how to use a database in Clojure.

## Run PostgreSQL

````
sudo docker run -v $(pwd)/data:/var/lib/postgresql/data --net=host -e POSTGRES_DB=db1 postgres:12
````
