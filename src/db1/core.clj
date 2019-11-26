(ns db1.core
  (:require [clojure.java.jdbc :as j])
  (:gen-class))

(def db {:dbtype "postgresql"
         :dbname "db1"
         :host "localhost"
         :user "postgres"})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
