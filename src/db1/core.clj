(ns db1.core
  (:require [next.jdbc :as j]
            [honeysql.core :as sql]
            [honeysql.helpers :as h]
            [db1.datasource :refer [load-ds]]) 
  (:gen-class))

(defn -main
  [& args]
  (let [my-telno "038"]
    (with-open [ds (load-ds)]
      (prn (j/execute! ds
                       (-> (h/select :*)
                           (h/from :person)
                           (h/where [:= :telno my-telno])
                           sql/format))))))

