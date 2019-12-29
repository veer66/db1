(ns db1.core
  (:require [next.jdbc :as j]
            [next.jdbc.connection :as conn]
            [honeysql.core :as sql]
            [honeysql.helpers :as h])
  (:import (com.zaxxer.hikari HikariDataSource))
  (:gen-class))

(def db-spec (-> "db.edn"
                 slurp
                 read-string))

(defn -main
  [& args]
  (let [my-telno "038"]
    (with-open [^HikariDataSource ds (doto (HikariDataSource.)
                                       (.setJdbcUrl (str "jdbc:"
                                                         (:dbtype db-spec)
                                                         "://"
                                                         (:host db-spec)
                                                         "/"
                                                         (:dbname db-spec)))
                                       (.setUsername (:user db-spec)))]
      (prn (j/execute! ds
                       (-> (h/select :*)
                           (h/from :person)
                           (h/where [:= :telno my-telno])
                           sql/format))))))


