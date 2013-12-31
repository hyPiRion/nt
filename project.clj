(defproject com.hypirion/nt "0.1.0-SNAPSHOT"
  :description "Number theory in Clojure"
  :url "https://github.com/hyPiRion/nt"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/math.numeric-tower "0.0.2"]
                 [com.hypirion/primes "0.2.1"]]
  :profiles {:dev {:dependencies [[reiddraper/simple-check "0.5.3"]]}})
