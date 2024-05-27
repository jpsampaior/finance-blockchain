(ns finance.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [cheshire.core :as	json]))

(defn balance-as-json []
  {:headers {"Content-Type"
             "application/json; charset=utf-8"}
   :body (json/generate-string {:balance 0})})

(defroutes app-routes
  (GET "/" [] "Finance API")
  (GET "/balance" [] (balance-as-json))
  (route/not-found "Resource not found"))

(def app
  (wrap-defaults app-routes site-defaults))
