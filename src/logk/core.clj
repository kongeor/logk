(ns logk.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io])
  (:import (java.time LocalDateTime)
           (java.time.format DateTimeFormatter)
           (java.time.temporal ChronoUnit)))

(def formatter (DateTimeFormatter/ofPattern "yyyy-MM-dd HH:mm:ss+SSSS"))

(defn parse-local-date [date-str]
  (LocalDateTime/parse date-str formatter))

(defn date-diff [date1 date2]
  (let [millis ChronoUnit/MILLIS]
    (/ (.between millis (first date1) (second date2)) 1000)))

(defn parse-line [line]
  (map second (re-seq #"(<[A-Z][^>.]*>|\p{ASCII})" line)))

(defn parse-log
  "TODO"
  [log]
  (->> log
       (str/split-lines)
       (map #(str/split % #">" 2))
       (filter #(= 2 (count %)))
       (map (fn [[date-str char-str]]
              [(parse-local-date (str/trim date-str))
               (parse-line (str/trim char-str))]))))

(def a-z (set (map str (map char (range (int \a) (inc (int \z)))))))

(defn char-stats [chars]
  (sort-by second > (frequencies chars)))

(comment
  (char-stats (parse-log (slurp (io/file "/home/kostas/logkeys.log")))))

(comment
  (def log-chars (parse-log (slurp (io/file "/home/kostas/logkeys.log")))))

(comment
  (frequencies (mapcat second log-chars)))

(comment
  (take 30 (char-stats (partition 3 1 (mapcat second log-chars)))))
