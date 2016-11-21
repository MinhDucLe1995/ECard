(ns ecard.containers.side-panel.subs
    (:require   [re-frame.core :as re-frame :refer [reg-sub]]))

(reg-sub :side-panel/selected-tab-index
    (fn [db _]
        (get-in db [:side-panel :selected-tab-index] 1)))