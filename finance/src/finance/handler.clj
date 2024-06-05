(ns finance.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-body]]
            [cheshire.core :as	json]
            [finance.db :as db]
            [finance.transactions :as transactions]
            [ring.middleware.cors :refer [wrap-cors]]))

(defn as-json [content & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (json/generate-string content)})

(defroutes app-routes
  (GET "/" [] "Finance API")
  (GET "/balance" [] (as-json {:balance (db/balance)}))
  (GET "/transactions" [] (as-json {:transactions (db/transactions)}))
  (POST "/transactions" request 
    (if (transactions/valid? (:body request))
          (-> (db/register (:body request))
              (as-json 201))
        (as-json {:message "Invalid Request"} 422)))
  (route/not-found "Resource not found"))

(def app
  (-> (wrap-defaults app-routes api-defaults)
      (wrap-json-body {:keywords? true :bigdecimals? true})
      (wrap-cors :access-control-allow-origin [#"http://localhost:5173"]
                 :access-control-allow-methods [:get :post :put :delete])))