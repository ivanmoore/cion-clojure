(ns cion-clojure.core
  (:gen-class)
  (:require [clj-http.client :as client]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (println "Hello, World!")
  (client/post "http://ivan:password@localhost:8080/httpAuth/app/rest/projects/"
  {:body "bobbyboo"
   :content-type "text/plain"
   :accept :xml}))