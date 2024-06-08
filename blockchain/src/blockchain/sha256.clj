(ns blockchain.sha256
  (:import [java.security MessageDigest])
)

(defn sha256 [data]
  (let [digest (.digest (MessageDigest/getInstance "SHA-256") (.getBytes data "UTF-8"))]
    (apply str (map (partial format "%02x") digest))))