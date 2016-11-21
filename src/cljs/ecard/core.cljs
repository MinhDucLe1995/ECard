(ns ecard.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [ecard.events]
              [ecard.containers :as containers]
              [ecard.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (reagent/render [containers/views]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
