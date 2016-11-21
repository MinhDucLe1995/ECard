(ns ecard.containers.overlays.events
    (:require   [re-frame.core :as re-frame :refer [reg-event-db]]))

(reg-event-db :overlays/set-rotate-deg
    (fn [db [_ deg]]
        (assoc-in db [:overlays :rotate-deg] deg)))

(reg-event-db :overlays/set-top-left
    (fn [db [_ top left]]
        (assoc-in db [:overlays :translate] {:top top :left left })))

