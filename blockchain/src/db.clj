(ns finance.db)

(defn expenditure? [transaction]
  (= (:type transaction) "expenditure"))

(defn calculate [total transaction]
  (let [amount (:value transaction)]
    (if (expenditure? transaction)
      (- total amount)
      (+ total amount))))

(def record
  (atom []))

(defn balance []
  (println "Current transactions:" @record) ;; Adiciona mensagem de depuração
  (reduce calculate 0 @record))

(defn clear-transactions []
  (reset! record []))

(defn transactions []
  @record)

(defn register [transaction]
  (let [updated-collection (swap! record conj transaction)]
    (println "Registered transaction:" transaction) ;; Adiciona mensagem de depuração
    (println "Updated collection:" @record) ;; Adiciona mensagem de depuração
    (merge transaction {:id (count updated-collection)})))