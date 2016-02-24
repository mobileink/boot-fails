(def +project+ 'tmp.boot/fails-app)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :asset-paths #{"assets"}
 :resource-paths #{"resources"}
 :source-paths #{"src"}
 :target-path "build"
 :repositories {"clojars" "https://clojars.org/repo"
                "maven-central" "http://mvnrepository.com"
                "central" "http://repo1.maven.org/maven2/"}
 :dependencies   '[[org.clojure/clojure "1.8.0" :scope "provided"]
                   [boot/core "2.5.5" :scope "provided"]
                   [tmp.boot/boot-fails "0.1.0-SNAPSHOT"]])

(require '[tmp.boot-fails :as fails]
         '[clojure.string :as str])

(task-options!
 pom  {:project     +project+
       :version     +version+
       :description "Example code, boot, miraj, GAE"
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}})

(def app-config "goodbye")

(deftask goodbye
  "locally defined (i.e. app) task"
  []
  (println boot.user/app-config "from fails-app"))
