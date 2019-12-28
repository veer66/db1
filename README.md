__db1__ is an example project for learning how to use a database in Clojure.

## Run PostgreSQL

````
docker run -v $(pwd)/data:/var/lib/postgresql/data --net=host -e POSTGRES_DB=db1 postgres:12
````

## Create a table and add data

````
cat v1.sql | docker run -i --net=host postgres:12 psql -h localhost -U postgres db1
````


## Run

````
docker run --rm -it -u 1000 --net=host -v $(pwd):/work -e HOME=/work -w /work clojure:tools-deps
````

````
clojure -m db1.core
````