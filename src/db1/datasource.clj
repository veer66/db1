(ns db1.datasource
  (:import (com.zaxxer.hikari HikariDataSource)))

(defn load-ds []
  (let [db-spec (-> "db.edn"
                slurp
                read-string)]
    (doto (HikariDataSource.)
      (.setJdbcUrl (str "jdbc:"
                        (:dbtype db-spec)
                        "://"
                        (:host db-spec)
                        "/"
                        (:dbname db-spec)))
      (.setUsername (:user db-spec)))))
