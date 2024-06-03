(ns finance.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-body]]
            [cheshire.core :as	json]
            [finance.db :as db]
            [finance.transactions :as transactions]))

(defn as-json [content & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (json/generate-string content)})

(defroutes app-routes
  (GET "/" [] "BlockChain")
  (GET "/blockchain" [] (as-json {:balance (db/balance)}))
  (GET "/transactions" [] (as-json {:transactions (db/transactions)}))

  (route/not-found "Resource not found"))

(def app
  (-> (wrap-defaults app-routes api-defaults)
      (wrap-json-body {:keywords? true :bigdecimals? true})))