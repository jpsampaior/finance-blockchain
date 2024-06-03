(ns finance.transactions)

(defn valid?
  [transaction]
  (and
    (contains? transaction :value)
    (number? (:value transaction))
    (pos? (:value transaction))
    (contains? transaction :type)
    (or (= "expenditure" (:type transaction))
        (= "income" (:type transaction)))))
