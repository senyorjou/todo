(ns todo.core
  (:require [cli-matic.core :refer [run-cmd]]
            [todo.cmds :refer [add-todo]])
  (:gen-class))

(def CONFIGURATION
  {:app         {:command     "todo"
                 :description "A command-line todo"
                 :version     "0.0.1"}
   :global-opts [{:option  "show-all"
                  :as      "Show all todos"
                  :type    :flag
                  :default false}]
   :commands    [{:command     "add" :short "a"
                  :description ["Adds a todo to the database"]
                  :opts        [{:option "todo" :short "t" :as "Todo text" :type :string}
                                {:option "expire" :short "e" :as "Expire (1H, 2H, 1D, 1W...)" :type :string}]
                  :runs        add-todo}]})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-cmd args CONFIGURATION))
