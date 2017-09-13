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


(def demo-sentence "Colorless green ideas sleep furiously")

(defn remove-punct [txt]
  (apply str (remove #((set ";:.,?!-'\"(){}") %) txt)))

(defn process-data [data]
  (json/parse-string (slurp data) true))

(defn assoc-tag-with-word [tags words]
  (map vector tags words))

(defn load-demo-tokens []
  (let [words (Sentence. demo-sentence)]
    (response {:tokens (.words words)})))

(defn token-handler [txt]
  (let [sent (Sentence. (remove-punct (get txt :data)))]
    (response {:tokens (.words sent)})))

(defn freq-dist-handler [txt]
  (let [sent (remove-punct (get txt :data))]
    (response {:dist (frequencies (str/split sent #"\s+"))})))

(defn load-demo-tags []
  (let [demo "John from Boston worked tirelessly as a mechanic for Ford in Detroit"
        sent (Sentence. (remove-punct demo))
        result (assoc-tag-with-word (.nerTags sent) (.words sent))]
    (response {:tags result})))

(defn ner-handler [txt]
  (let [sent (Sentence. (remove-punct (get txt :data)))]
    (response
      {:tags (assoc-tag-with-word (.nerTags sent) (.words sent))})))

(defn load-demo-lemmas []
  (let [demo "The monkeys jumped from branch to branch"
        sent (Sentence.  demo)]
    (response
      {:lemmas (frequencies (.lemmas sent))})))

(defn lemmas-handler [txt]
  (let [sent (Sentence. (remove-punct (get txt :data)))]
    (response
      {:lemmas (frequencies (.lemmas sent))})))

(defn load-demo-pos-tags []
  (let [demo (Sentence. demo-sentence)]
    (response
      {:postags
        (assoc-tag-with-word (.posTags demo) (.words demo))})))

(defn pos-tag-handler [txt]
  (let [sent (Sentence. (remove-punct (get txt :data)))]
    (response
      {:postags
      (assoc-tag-with-word (.posTags sent) (.words sent))})))

(defn load-demo-parse-tree []
  (let [demo (Sentence. demo-sentence)]
    (response {:tree (.toString (.parse demo))})))

(defn parse-tree-handler [txt]
  (let [sent (Sentence. (remove-punct (get txt :data)))]
    (response
      {:tree (.toString (.parse sent))})))

(defroutes app-routes
  (GET "/" [] "meow")
  (GET "/api/tokenization"
    []
    (load-demo-tokens))
  (POST "/api/tokenization"
    {data :body}
    (token-handler
      (process-data data)))
  (POST "/api/freq-dist"
    {data :body}
    (freq-dist-handler
      (process-data data)))
  (GET "/api/ner"
    []
    (load-demo-tags))
  (POST "/api/ner"
    {data :body}
    (ner-handler
      (process-data data)))
  (GET "/api/lemmas"
    []
    (load-demo-lemmas))
  (POST "/api/lemmas"
    {data :body}
    (lemmas-handler
      (process-data data)))
  (GET "/api/pos"
    []
    (load-demo-pos-tags))
  (POST "/api/pos"
    {data :body}
    (pos-tag-handler
      (process-data data)))
  (GET "/api/parse"
    []
    (load-demo-parse-tree))
  (POST "/api/parse"
    {data :body}
    (parse-tree-handler
      (process-data data)))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
    (wrap-json-response)
    (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))
