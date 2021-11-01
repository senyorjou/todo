(ns todo.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [todo.core :refer [CONFIGURATION]]))

(deftest a-test
  (testing "Configuration"
    (let [conf-keys [:app :global-opts :commands]]
      (is (= conf-keys (keys CONFIGURATION))))))
