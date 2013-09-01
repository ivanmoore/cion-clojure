(ns cion-clojure.core
  (:gen-class)
  (:require [clj-http.client :as client])
  (:use [clojure.data.xml], [clojure.tools.cli :only [cli]]))

(defn create-project [project-name]
  (client/post "http://ivan:password@localhost:8080/httpAuth/app/rest/projects/"
  {:body project-name
   :content-type "text/plain"
   :accept :xml}))
   
(defn create-projects [project-names]
	(map create-project project-names))

(defn extract-name [node]
	(:name (:attrs node)))
	
(defn project-names-xml []
	(parse-str (:body (client/get "http://ivan:password@localhost:8080/httpAuth/app/rest/projects/"))))

(defn get-project-names []
	(map extract-name (:content (project-names-xml))))

(defn quoted [s]
	(str "\"" s "\""))

(defn -main [& args]
  (alter-var-root #'*read-eval* (constantly false))
  (def command-line-options
	(cli args 
	["-u" "--url" "CI server api url" :default ""]
	["-f" "--file" "Setup CI server using specified file" :default ""]))
  (if (= args nil) 
	(print (nth command-line-options 2))
	(do
		(def command-line-values
			(first command-line-options))
		(if (= (:file command-line-values) "")
			(println (str "(create-projects (list " (clojure.string/join " " (map quoted (get-project-names))) "))"))
			(print command-line-values))
	)
  )
)