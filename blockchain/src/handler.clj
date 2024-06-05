(ns blockchain.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-body]]
            [cheshire.core :as	json]
            [blockchain.db :as db]
            [blockchain.transactions :as transactions]))

(defn as-json [content & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (json/generate-string content)})

(defroutes app-routes
  (GET "/" [] "BlockChain")
  (GET "/blockchain" [] (as-json {:blockchain (db/blockchain)}))
  (GET "/mine-block" [] (as-json {:blockchain (db/blockchain)})) ; ajustar isso
  (POST "/new-block" request 
    (if (transactions/valid? (:body request))
          (-> (db/register (:body request))
              (as-json 201))
        (as-json {:message "Invalid Request"} 422)))
  (route/not-found "Resource not found"))

(def app
  (-> (wrap-defaults app-routes api-defaults)
      (wrap-json-body {:keywords? true :bigdecimals? true})))