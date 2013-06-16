(ns cion-clojure.core
  (:gen-class))

(client/post "http://ivan:password@localhost:8080/httpAuth/app/rest/projects/"
	{:body "bobbyboo"
