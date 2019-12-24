(ns db1.core
  (:require [hikari-cp.core :refer [make-datasource]]
            [clojure.java.jdbc :as j])
  (:gen-class))

(def db {:dbtype "postgresql"
         :dbname "db1"
         :host "localhost"
         :user "postgres"})

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
  (j/with-db-connection [conn {:datasource @datasource}]
    (println (j/query conn
                      "SELECT * FROM person"))))
