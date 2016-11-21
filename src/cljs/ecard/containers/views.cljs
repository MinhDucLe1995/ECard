(ns ecard.containers
    (:require [re-frame.core :as re-frame]
              [ecard.containers.side-panel :refer [side-panel]]
              [ecard.containers.main-panel :refer [main-panel]]))

(defn views
  []
  [:div {:class "container h100"}
    [:div {:class "main-nav"}]
    [:div {:class "editor"}
      [side-panel]
      [main-panel] ]])
