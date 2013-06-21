(ns cion-clojure.core
  (:gen-class)
  (:require [clj-http.client :as client])
  (:use [clojure.data.xml]))

(defn create-project [project-name]
  (client/post "http://ivan:password@localhost:8080/httpAuth/app/rest/projects/"
  {:body project-name
   :content-type "text/plain"
   :accept :xml}))
   
(defn get-project-names []
	(parse-str (:body (client/get "http://ivan:password@localhost:8080/httpAuth/app/rest/projects/"))))
  
(defn -main [& args]
  (alter-var-root #'*read-eval* (constantly false))
  (println "Hello, World!")
  (get-project-names))
   
