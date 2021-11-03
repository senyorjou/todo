(ns todo.cmds-test
  (:require [todo.cmds :as cmds]
            [clojure.test :as t]))


(t/deftest parse-expiration-test
  (t/testing "Parse expiration with a bad parameter"
    (t/is (nil? (cmds/parse-expiration "")))
    (t/is (nil? (cmds/parse-expiration "foo")))
    (with-redefs [cmds/error-and-exit (constantly "error and exit")]
      (t/is (= "error and exit" (cmds/parse-expiration "1Q")))))

  (t/testing "Parse expiration with correct params"
    (let [now (java.time.LocalDateTime/now)
          now+1S (cmds/parse-expiration "1S")
          now+1M (cmds/parse-expiration "1M")
          now+1H (cmds/parse-expiration "1H")
          now+1D (cmds/parse-expiration "1D")
          now+1W (cmds/parse-expiration "1W")]
      (t/is (.isBefore now now+1S))
      (t/is (.isBefore now+1S now+1M))
      (t/is (.isBefore now+1M now+1H))
      (t/is (.isBefore now+1H now+1D))
      (t/is (.isBefore now+1D now+1W)))))

(t/deftest add-todo-test
  (t/testing "Crate a full todo with no expiration"
    (let [expected {:todo "Foo bar" :done false :expire nil}]
      (t/is (= (cmds/add-todo {:todo "Foo bar" :expire ""}) expected))
      (t/is (= (cmds/add-todo {:todo "Foo bar" :expire nil}) expected))
      (t/is (= (cmds/add-todo {:todo "Foo bar"}) expected))))

  (t/testing "Crate a full todo with expiration"
    (let [now (java.time.LocalDateTime/now)
          todo (cmds/add-todo {:todo "Foo bar" :expire "1D"})]
     (t/is (.isBefore now (:expire todo))))))
