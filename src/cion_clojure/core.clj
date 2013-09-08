(ns cion-clojure.core
	(:gen-class)
	(:require [clj-http.client :as client])
	(:require [clojure.data.json :as json])
	(:use [clojure.data.xml], [clojure.tools.cli :only [cli]]))

(defn create-project [project-name ci-server-url]
	(client/post (str ci-server-url "/httpAuth/app/rest/projects")
		{	
			:body project-name
			:content-type "text/plain"
			:accept :xml
		}))

(defn extract-name [node]
	(:name (:attrs node)))
	
(defn project-names-xml [ci-server-url]
	(parse-str (:body (client/get (str ci-server-url "/httpAuth/app/rest/projects")))))

(defn get-project-names [ci-server-url]
	(map extract-name (:content (project-names-xml ci-server-url))))

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
			(def command-line-values (first command-line-options))
			(def ci-server-url (:url command-line-values))
			(if (= (:file command-line-values) "")
				(do
					(def project-names (get-project-names ci-server-url))
					(defn project-as-json [project-name]
						(str "{	\"project\": { \"name\": " (quoted project-name) "}}"))
					(println "{ \"projects\" : [" (clojure.string/join ",\n " (map project-as-json project-names)) "] }"))
				(do
					(def projects (map :project (:projects (json/read-str (slurp (:file command-line-values)) :key-fn keyword))))
					(def project-names (map :name projects))
					(defn create-project-on-ci-server [project-name] (create-project project-name ci-server-url))
					(map create-project-on-ci-server project-names))))))