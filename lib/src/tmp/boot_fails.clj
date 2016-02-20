(ns tmp.boot-fails
  {:boot/export-tasks true}
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [boot.pod :as pod]
            [boot.core :as core]
            [boot.util :as util]
            [boot.task.built-in :as builtin]))

(def lib-config "howdy")

(core/deftask nss
  "dump namespaces"
  [v verbose bool "Print trace messages."]
  (let [nss (all-ns)]
    (doseq [ns (sort (map #(ns-name %) nss))]
      (println "ns: " ns))))

(core/deftask ns-
  "dump interned syms in namespace"
  [n namespace NS str "namespace to dump. default: *ns*"
   v verbose bool "Print trace messages."]
  (let [the-ns (if namespace (symbol namespace) *ns*)]
    (println "syms interned in " (if (not namespace) "*ns* ") the-ns ":")
    (doseq [[isym ivar] (ns-interns the-ns)]
      (println (format "\t%s\t%s" isym ivar)))))

;; NOTE: at runtime boot.user is defined by user's build.boot (profile.boot, etc.)
;; at build time (for this lib), it's the local build.boot(?)
;; this will be run whenever you run a task:
(println "config: " lib-config " (" (var lib-config) ")")

;; this line will crash, but same line will work if its IN a task!
;;(println "config: " (deref (ns-resolve 'boot.user 'app-config)) (ns-resolve 'boot.user 'app-user))

(core/deftask vars
  "investigate config vars"
  [v verbose bool "Print trace messages."]
  (let [buns (find-ns 'boot.user)
        cfg (ns-resolve 'boot.user 'app-config)
        msg (deref (ns-resolve 'boot.user 'app-config))]
    (println "app-config: " msg " (" cfg ")")
    (println "lib-config: " lib-config " (" (var lib-config) ")")))

(core/deftask hello
  "hello task"
  [v verbose bool "Print trace messages."]
  (println lib-config "from boot-fails/hello lib")
  ;; uncomment this and compile will fail with "no such var"
  ;; run boot ns- -n boot.user and you will see #'boot.user/myconfig
  #_(println boot.user/my-config))
