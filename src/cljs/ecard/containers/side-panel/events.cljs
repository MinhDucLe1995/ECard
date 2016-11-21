(ns ecard.containers.side-panel.events
    (:require   [re-frame.core :as re-frame :refer [reg-event-db]]))

(reg-event-db :side-panel/set-selected-tab
    (fn [db [_ tab]]
        (assoc-in db [:side-panel :selected-tab-index] (:index tab))))
