(ns db1.jsonb
  (:require [next.jdbc :as j]
            [db1.datasource :refer [load-ds]]
            [jsonista.core :as json]
            [next.jdbc.result-set :refer [ReadableColumn]])
  (:import (org.postgresql.util PGobject))
  (:gen-class))

(defn >jsonb [d]
  (doto (PGobject.)
    (.setType "jsonb")
    (.setValue (json/write-value-as-string d))))

(defn read-pgobject
  [x]
  (condp = (.getType x)
      "jsonb" (->> x
                   .getValue
                   json/read-value)
      x))

(extend-protocol ReadableColumn
  PGobject
  (read-column-by-label [x _]
    (read-pgobject x))
  (read-column-by-index [x _2 _3]
    (read-pgobject x)))

(defn -main
  [& args]
  (with-open [ds (load-ds)]
    (j/execute! ds ["INSERT INTO meta1(id, metadata) VALUES (?, ?)"
                    10 (>jsonb {:title "toto"})])
    (->> (into []
               (map :metadata)
               (j/plan ds ["SELECT * FROM meta1"]))
         prn)))
