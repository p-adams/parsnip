(ns server.handler
  (import edu.stanford.nlp.simple.Document)
  (import edu.stanford.nlp.simple.Sentence)
  (:require [compojure.core :refer :all]
            [clojure.string :as str]
            [compojure.route :as route]
            [cheshire.core :as json]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.response :refer [response]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))


;; routes
; constituency parser
; frequency distribution : done
; lemma
; named-entity recognition
; parts-of-speech
; parse
; tokenization : done



(defn remove-punct [txt]
  (apply str (remove #((set ";:.,?!-'\"(){}") %) txt)))

(defn process-data [data]
  (json/parse-string (slurp data) true))

(defn load-demo-tokens []
  (let [words (Sentence. "Colorless green ideas sleep furiously")]
  (response {:tokens (.words words)})))

(defn token-handler [txt]
  (let [sent (Sentence. (remove-punct (get txt :data)))]
  (response {:tokens (.words sent)})))

(defn freq-dist-handler [txt]
  (let [sent (remove-punct (get txt :data))]
  (response {:dist (frequencies (str/split sent #"\s+"))})))

(defn load-demo-lemmas []
  (let [demo "Colorless green ideas sleep furiously"
        sent (Sentence.  demo)]
    (response
      {:data
        {:og (remove-punct demo) :lem (.lemmas sent)}})))

(defn lemmas-handler [txt]
  (let [processed (remove-punct txt)]
    (processed)))

(defroutes app-routes
  (GET "/" [] "meow")
  (GET "/api/tokenization"
    []
    (load-demo-tokens))
  (POST "/api/tokenization" {data :body}
    (token-handler
      (process-data data)))
  (POST "/api/freq-dist" {data :body}
    (freq-dist-handler
      (process-data data)))
  (GET "/api/lemmas"
    []
    (load-demo-lemmas))
  (POST "/api/lemmas"
    req (lemmas-handler req))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
    (wrap-json-response)
    (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))
