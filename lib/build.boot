(def +project+ 'tmp.boot/boot-fails)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :source-paths #{"src"}
 :resource-paths #{"src"}
 :asset-paths #{"src"}
 :repositories {"clojars" "https://clojars.org/repo"
                #_["maven-central" "http://mvnrepository.com"]
                "central" "http://repo1.maven.org/maven2/"}
 :dependencies   '[[org.clojure/clojure "1.8.0" :scope "provided"]
                   [boot/core "2.5.2" :scope "provided"]
                   [adzerk/boot-test "1.0.7" :scope "test"]])

(def root-config "hello")

(println root-config " (" (var root-config) ")")

(task-options!
 target {:dir "build"}
 pom  {:project     +project+
       :version     +version+
       :description "Boot failures"
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}})
