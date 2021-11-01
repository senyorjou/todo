(ns todo.cmds
  (:require [clojure.string :as str]))


(defn parse-expiration
  "Calculates expiration time in forms:
   - 20S --> 20 seconds
   - 2D --> 2 days and so
   valid units are S, M, H, D, W
   returns LocalDateTime or nil"
  [input]
  (let [now (java.time.LocalDateTime/now)
        [match str-amount unit] (re-matches #"(\d+)(S|M|H|D|W)" input)]
    (when (some? match)
        (let [amount (Integer/parseInt str-amount 10)]
          (case (str/lower-case unit)
            "s" (.plusSeconds now amount)
            "m" (.plusMinutes now amount)
            "h" (.plusHours now amount)
            "d" (.plusDays now amount)
            "w" (.plusWeeks now amount))))))



(defn add-todo [{:keys [todo expire]}]
  {:todo todo
   :done false
   :expire (parse-expiration expire)})
