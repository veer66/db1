__db1__ is an example project for learning how to use a database in Clojure.

## Run PostgreSQL

````
docker run -v $(pwd)/data:/var/lib/postgresql/data --net=host -e POSTGRES_DB=db1 postgres:12
````

## Prepare

### Docker

````
docker run --rm -it -u 1000 --net=host -v $(pwd):/work -e HOME=/work -w /work clojure:tools-deps bash
````

### Migrate DB

```
clojure -m db1.migrate
```

## Run

````
clojure -m db1.core
````

## Create migration

````
clojure -m db1.create-migration <name>
````