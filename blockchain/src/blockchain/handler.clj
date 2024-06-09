(ns blockchain.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-body]]
            [cheshire.core :as	json]
            [blockchain.db :as db]
            [ring.middleware.cors :refer [wrap-cors]]))

(defn as-json [content & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (json/generate-string content)})

(defroutes app-routes
  (GET "/blockchain" [] (as-json (db/blockchain)))
  (POST "/new-block" request (-> (db/register (:body request))(as-json 201)))  
  (route/not-found "Resource not found"))

(def app
  (-> (wrap-defaults app-routes api-defaults)
      (wrap-json-body {:keywords? true :bigdecimals? true})
      (wrap-cors :access-control-allow-origin [#"http://localhost:5173"]
                 :access-control-allow-methods [:get :post :put :delete])))