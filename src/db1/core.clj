(ns db1.core
  (:require [hikari-cp.core :refer [make-datasource]]
            [clojure.java.jdbc :as j]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all :as helpers])
  (:gen-class))

(defonce datasource
  (delay (-> "db.edn"
             slurp
             read-string
             make-datasource)))

(defn -main
  [& args]
  (let [my-telno "038"]
    (j/with-db-connection [conn {:datasource @datasource}]
      (println (j/query conn
                        (-> (select :*)
                            (from :person)
                            (where [:= :telno my-telno])
                            sql/format))))))
