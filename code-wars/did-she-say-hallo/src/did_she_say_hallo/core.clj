(ns did-she-say-hallo.core
  (:gen-class))

(defn validate-hello
  [greetings]
  (let [languages #{"hello" "ciao" "salut" "hallo" "hola" "ahoj" "czesc"}]
    (as-> greetings $
      (.toLowerCase $)
      (some #(.contains $ %) languages)
      (or $ false))))

(defn -main
  ""
  [& args])
