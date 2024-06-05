(ns blockchain.transactions)

(defn valid?   ; adicionar logica da blockchain
  [block]
  (and
    (contains? transaction :value)
    (number? (:value transaction))
    (pos? (:value transaction))
    (contains? transaction :type)
    (or (= "expenditure" (:type transaction))
        (= "income" (:type transaction)))))
