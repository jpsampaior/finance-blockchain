;; (ns finance.saldo-aceitacao-test
;;   (:require 	[midje.sweet :refer :all]
;; 							[finance.handler	:refer [app]]
;; 							[ring.adapter.jetty	:refer [run-jetty]]
;; 							[clj-http.client :as http]))

;; (def servidor (atom nil))

;; (defn iniciar-servidor [porta]
;;   (swap! servidor
;;     (fn [_]
;;       (run-jetty app {:port porta :join? false}))))

;; (defn parar-servidor []
;;   (.stop @servidor))

;; (fact "Inicia e para o servidor"
;;   (iniciar-servidor 3001)
;;   (parar-servidor))

;; (fact "O saldo inicial é 0"
;; 	(iniciar-servidor 3001)
;; 	(:body (http/get "http://localhost:3001/saldo")) => "0"
;; 	(parar-servidor))
