(ns finance.db)

(def record
  (atom []))

(defn balance []
  (println "Current Block Chain:" @record) ;; Adiciona mensagem de depuração
  (reduce calculate 0 @record))

(defn mine [nonce prev_id data prev_hash]
  (let [block conj nonce (+ prev_id 1) data prev_hash])

)

(defn clear-blockchain []
  (reset! record []))

(defn blockchain []
    @record)

(defn register [block]
  (let [updated-collection (swap! record conj block)]
    (println "Registered block:" block) ;; Adiciona mensagem de depuração
    (println "Updated collection:" @record) ;; Adiciona mensagem de depuração
    (merge block {:id (count updated-collection)})))