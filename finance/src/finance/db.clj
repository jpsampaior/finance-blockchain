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
  (reduce calculate 0 @record))

(defn clear-transactions []
  (reset! record []))

(defn transactions []
  @record)

(defn register [transaction]
  (swap! record conj (merge transaction {:id (+ (count @record) 1)})))