(ns ecard.containers.overlays
        (:require [re-frame.core :as re-frame :refer [subscribe dispatch]] 
                  [ecard.containers.overlays.events]
                  [ecard.containers.overlays.subs]
                  [ecard.components.selection-box :refer [selection-box]]))

(defn overlays []
     (let [rotate-deg (subscribe [:overlays/rotate-deg])
           top (subscribe [:overlays/translate-top])
           left (subscribe [:overlays/translate-left])
           selected-element (subscribe [:overlays/selected-element])]
        [:div {:class "overlays"}
            (if (false? (nil? @selected-element)) 
                [selection-box @top @left 100 100 @rotate-deg 54 430 
                    (fn [rotate] (dispatch [:overlays/set-rotate-deg rotate]))
                    (fn [top left] (dispatch [:overlays/set-top-left top left]))])]))