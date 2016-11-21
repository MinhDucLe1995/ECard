(ns ecard.containers.overlays.subs
    (:require   [re-frame.core :as re-frame :refer [reg-sub]]))

(reg-sub :overlays/rotate-deg
    (fn [db _]
        (get-in db [:overlays :rotate-deg] 0)))

(reg-sub :overlays/translate-top
    (fn [db _]
        (get-in db [:overlays :translate :top] 100)))

(reg-sub :overlays/translate-left
    (fn [db _]
        (get-in db [:overlays :translate :left] 100)))

(reg-sub :overlays/selected-element
    (fn [db _]
        (get-in db [:overlays :selected-elements] nil)))

