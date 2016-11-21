(ns ecard.components.tabs.content)

(defn content [tabs-list]
    [:div {:class "content"}
        (map (fn [tab]
                [:div {:key (:index tab)
                       :class "tab-page"}
                    [(:page tab)]]) tabs-list)])