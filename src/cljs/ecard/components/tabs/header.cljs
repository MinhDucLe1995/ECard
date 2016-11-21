(ns ecard.components.tabs.header)

(defn header [tabs-list set-selected-tab]
    [:div {:class "header"}
        [:ul
            (map (fn [tab]
                [:li {:key (:index tab)}
                    [:a {:on-Click #(set-selected-tab tab)}
                        [:i {:class (str "fa fa-" (:icon tab))}]]]) tabs-list)]
        [:span {:id "selectedTab"} [:span][:span]]])