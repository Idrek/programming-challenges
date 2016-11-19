(ns liga-de-padel.core
  (:require [clojure.java.io :as clj-io]
            [clojure.string :as clj-str])
  (:gen-class))

(defn calculate-points
  "Generate puntuation from match result.
  Example:
   [Input] result -> ['team1' 0 'team2' 3]
   [Output] {:team1 2, :team2 1}"
  [result]
  (let [result-map (apply hash-map result)
        score (map #(Integer. %) (vals result-map))
        teams (map keyword (keys result-map))
        points (case (apply compare score)
                 1 [2 1]
                 -1 [1 2])
        team-points (zipmap teams points)]
    team-points))

(defn calculate-winner
  "Calculate winner and number of missed matches from total planned.
  Example:
   [Input] statistics -> {:team1 4, :team2 3, :team3 6}
           matches-played -> 4
   [Output] {:team3 2}"
  [statistics matches-played]
  (if (empty? statistics)
    nil
    (let [num-teams (-> statistics keys count)
          total-matches (* num-teams (dec num-teams))
          first-two-positions (->> statistics (sort-by val >) (take 2))
          winner (if (apply = (vals first-two-positions))
                   :EMPATE
                   (ffirst first-two-positions))]
      {winner (- total-matches matches-played)})))

(defn parse-match
  "Parse line and call different callbacks. There are the types of
  lines:
  - End of category: FIN
  - Category title: Unique string without spaces.
  - Match: Match result, like 'team1 2 team2 1'"
  [match-str {:keys [end-category-fn category-fn match-fn]}]
  (cond
    (= "FIN" match-str) (end-category-fn)
    (not= nil (re-matches #"\S+" match-str)) (category-fn)
    :else (match-fn)))

(defn read-league-line
  "Read following line from reader"
  [reader]
  (-> reader line-seq first))

(defn update-statistics
  "Update stadistics map with puntuation assigned according to
  the match result.
  Example:
    [Input] statistics -> {:team1 3, :team2 4, :team3 1}
            match-str 'team1 1 team3 0'
    [Output] {:team1 5, :team2 4, :team3 1}"
  [statistics match-str]
  (merge-with + statistics
               (calculate-points
                (clj-str/split match-str #"\s+" 4))))

(defn print-winners
  "Print winners vector of maps structure: [{:team2 9}, {:team3 7}]"
  [winners]
  (doseq [winner winners]
    (->> (for [[k v] winner] [(name k) v])
         first
         (apply printf "%s %d\n"))))

(defn parse-league
  "Generate winner and number of missed matches for each category"
  [input-file]
  (with-open [reader (clj-io/reader input-file)]
    (loop [match-str (read-league-line reader)
           [statistics matches-played winners] [{} 0 []]]
      (if (nil? match-str)
        (filter (complement nil?) winners)
        (recur
         (read-league-line reader)
         (cond
           (= "FIN" match-str)
           [{} 0 (conj winners
                       (some->> [statistics matches-played]
                                (apply calculate-winner)))]
           
           (not= nil (re-matches #"\S+" match-str))
           [{} 0 winners]

           :else
           [(update-statistics statistics match-str)
            (inc matches-played)
            winners]))))))

(defn -main
  "https://www.aceptaelreto.com/problem/statement.php?id=109"
  ([] (-main *in*))
  ([input-file]
   (-> input-file
       parse-league
       print-winners)))

