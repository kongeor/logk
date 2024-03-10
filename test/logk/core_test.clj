(ns logk.core-test
  (:require [clojure.test :refer :all]
            [logk.core :refer [parse-log]])
  (:import (java.time LocalDateTime)))

(def example-log-1 "2024-02-24 20:09:58+0200 > <Enter>\n2024-02-24 20:09:58+0200 > <Enter><RShft>$$ <BckSp><BckSp><BckSp>\n2024-02-24 20:10:00+0200 > <Enter><RShft>$<BckSp><BckSp><RShft>## <RShft>extracting<LCtrl><BckSp><RShft><#+17><RShft><#+9><RShft>r<BckSp><RShft>real worl<LCtrl><BckSp><BckSp><RShft>example 1<LShft>: <RShft>parsing logkeys logs<Esc>o\n")

(deftest parse-log-test
  (is (= [[(LocalDateTime/parse "2024-02-24T20:09:58.020")
           ["<Enter>"]]
          [(LocalDateTime/parse "2024-02-24T20:09:58.020")
           ["<Enter>"
            "<RShft>"
            "$"
            "$"
            " "
            "<BckSp>"
            "<BckSp>"
            "<BckSp>"]]
          [(LocalDateTime/parse "2024-02-24T20:10:00.020")
           ["<Enter>"
            "<RShft>"
            "$"
            "<BckSp>"
            "<BckSp>"
            "<RShft>"
            "#"
            "#"
            " "
            "<RShft>"
            "e"
            "x"
            "t"
            "r"
            "a"
            "c"
            "t"
            "i"
            "n"
            "g"
            "<LCtrl>"
            "<BckSp>"
            "<RShft>"
            "<"
            "#"
            "+"
            "1"
            "7"
            ">"
            "<RShft>"
            "<"
            "#"
            "+"
            "9"
            ">"
            "<RShft>"
            "r"
            "<BckSp>"
            "<RShft>"
            "r"
            "e"
            "a"
            "l"
            " "
            "w"
            "o"
            "r"
            "l"
            "<LCtrl>"
            "<BckSp>"
            "<BckSp>"
            "<RShft>"
            "e"
            "x"
            "a"
            "m"
            "p"
            "l"
            "e"
            " "
            "1"
            "<LShft>"
            ":"
            " "
            "<RShft>"
            "p"
            "a"
            "r"
            "s"
            "i"
            "n"
            "g"
            " "
            "l"
            "o"
            "g"
            "k"
            "e"
            "y"
            "s"
            " "
            "l"
            "o"
            "g"
            "s"
            "<Esc>"
            "o"]]] (parse-log example-log-1))))
