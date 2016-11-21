(ns ecard.containers.main-panel
    (:require [ecard.containers.overlays :refer [overlays]]))

(defn main-panel []
    [:div {:class "main-panel"}
        [overlays]])