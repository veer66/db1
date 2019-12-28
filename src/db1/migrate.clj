(ns db1.migrate
  (:require [migratus.core :as migratus]
            [hikari-cp.core :refer [make-datasource]]
            [clojure.edn :as edn]))

(def config {:store :database
             :db  (-> "db.edn"
                      slurp
                      read-string
                      make-datasource)})

(defn -main
  []
  (migratus/migrate config))

