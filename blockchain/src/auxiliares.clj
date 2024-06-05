(ns	blockchain.auxiliaries
		(:require [ring.adapter.jetty :refer [run-jetty]]
				  [blockchain.handler :refer [app]]
				  [cheshire.core :as json]
				  [clj-http.client :as http]))

(defn content-as-json [transaction]
    {:content-type :json
    :body (json/generate-string transaction)
    :throw-exception false})

(defn expenditure [value]
    (content-as-json {:value value :type "expenditure"}))

(defn income [value]
    (content-as-json {:value value :type "income"}))    