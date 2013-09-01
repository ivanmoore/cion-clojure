(defproject cion-clojure "0.1.0-SNAPSHOT"
  :description "cion will be a tool for configuring ci servers"
  :url "https://github.com/ivanmoore/cion-clojure"
  :license {:name "JSON style License"
            :url "https://github.com/ivanmoore/cion-clojure/blob/master/LICENSE.txt"}
  :dependencies [	[org.clojure/clojure "1.5.1"], 
					[clj-http "0.7.2"], 
					[org.clojure/data.xml "0.0.7"],
					[org.clojure/tools.cli "0.2.4"]]
  :main cion-clojure.core)
