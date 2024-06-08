(ns blockchain.db
  (:require [blockchain.sha256 :as blockchain])
)

(def record
  (atom []))

(defn clear-blockchain []
  (reset! record []))

(defn find-nonce [nonce previous-hash transactions id]
    (let [hash (blockchain/sha256 (str nonce transactions previous-hash id))]
      (if (and (= (subs hash 0 4) "0000"))
        [nonce hash]
        (recur (inc nonce) previous-hash transactions id)))
)

(defn create-block [nonce previous-hash hash transactions id]
   (let [new-block {:id id
     :nonce nonce
     :previous-hash previous-hash
     :hash hash
     :transactions transactions}]
     (swap! record conj new-block)
     (println new-block)
     new-block
   ))

(defn create-first-block []
  (let [
    [nonce hash](find-nonce 0 "0x" "Esse eh o primeiro bloco" 0)
    new-block(create-block nonce "0x" hash "Esse eh o primeiro bloco" 0)])
)

(defn blockchain []
  (if (empty? @record)
    (create-first-block)())
    @record)


(defn register [transactions]
  (let[
    previous-block(last @record)
    previous-hash(:hash previous-block)
    previous-id(:id previous-block)
    id(inc previous-id)
    [nonce hash](find-nonce 0 previous-hash transactions id)
    new-block(create-block nonce previous-hash hash transactions id) 
  ]
  new-block
  )
)
