(ns ecard.containers.side-panel
    (:require   [re-frame.core :as re-frame :refer [subscribe dispatch]] 
                [ecard.components.tabs :refer [tabs]]
                [ecard.containers.side-panel.events]
                [ecard.containers.side-panel.subs]
                [ecard.containers.side-panel.pages.developing :refer [developing]]
                [ecard.containers.side-panel.pages.picture :refer [picture-page]]))

(defn side-panel []
    (let [selected-tab-index (subscribe [:side-panel/selected-tab-index])]
        (defonce tabs-list [
            {:index 0 :page developing :name "layout" :icon "th-large"}
            {:index 1 :page picture-page :name "picture" :icon "picture-o"}
            {:index 2 :page developing :name "text" :icon "font"}
            {:index 3 :page developing :name "background" :icon "tint"}
            {:index 4 :page developing :name "upload" :icon "upload"}])
        [:div {:class "side-panel"}
            [tabs tabs-list @selected-tab-index (fn [tab] (dispatch [:side-panel/set-selected-tab tab]))]]))
