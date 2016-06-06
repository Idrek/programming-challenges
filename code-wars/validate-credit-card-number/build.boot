(def project 'validate-credit-card-number)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"resources" "src"}
          :source-paths   #{"test"}
          :dependencies   '[[org.clojure/clojure "RELEASE"]
                            [adzerk/boot-test "RELEASE" :scope "test"]
                            [numberto "0.0.3"]])

(task-options!
 aot {:namespace   #{'validate-credit-card-number.core}}
 pom {:project     project
      :version     version
      :description "Code Wars :: Validate Credit Card Number"
      :url         "http://www.codewars.com/kata/validate-credit-card-number"
      :scm         {:url "https://github.com/yourname/validate-credit-card-number"}
      :license     {"Eclipse Public License"
                    "http://www.eclipse.org/legal/epl-v10.html"}}
 jar {:main        'validate-credit-card-number.core
      :file        (str "validate-credit-card-number-" version "-standalone.jar")})

(deftask build
  "Build the project locally as a JAR."
  [d dir PATH #{str} "the set of directories to write to (target)."]
  (let [dir (if (seq dir) dir #{"target"})]
    (comp (aot) (pom) (uber) (jar) (target :dir dir))))

(deftask run
  "Run the project."
  [a args ARG [int] "the arguments for the application."]
  (require '[validate-credit-card-number.core :as app])
  (apply (resolve 'app/-main) args))

(require '[adzerk.boot-test :refer [test]])
