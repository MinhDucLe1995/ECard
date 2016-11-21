(ns ecard.components.tabs
    (:require   [ecard.components.tabs.header :refer [header]]
                [ecard.components.tabs.content :refer [content]]))

(defn tabs
    [tabs-list selected-tab-index set-selected-tab]
    [:div {:class (str "tabs " "active-" selected-tab-index)}
        [header tabs-list set-selected-tab]
        [content tabs-list]])
