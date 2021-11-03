(defproject todo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license  {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
             :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [cli-matic "0.4.3"]]
  :plugins [[lein-cloverage "1.2.2"]]

  :profiles {:uberjar {:aot :all}
             :kaocha {:dependencies [[lambdaisland/kaocha "1.60.945"]]}}
  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]}
  :repositories [["cognitect-dev-tools" {:url"https://dev-tools.cognitect.com/maven/releases/"
                                         :username :env
                                         :password :env}]]
  :main ^:skip-aot todo.core
  :target-path "target/%s")
