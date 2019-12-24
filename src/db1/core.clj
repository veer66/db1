(ns db1.core
  (:require [hikari-cp.core :refer [make-datasource]]
            [clojure.java.jdbc :as j]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all :as helpers])
  (:gen-class))

(def datasource-options {:pool-name          "db-pool"
                         :adapter            "postgresql"
                         :username           "postgres"
                         :database-name      "db1"
                         :server-name        "localhost"})

(defonce datasource
  (delay (make-datasource datasource-options)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [my-telno "038"]
    (j/with-db-connection [conn {:datasource @datasource}]
      (println (j/query conn
                        (-> (select :*)
                            (from :person)
                            (where [:= :telno my-telno])
                            sql/format))))))
