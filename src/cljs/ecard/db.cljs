(ns ecard.db)

(def default-db
  {:side-panel {
    :side-panels-list [{:index 0
                        :name "layout"
                        :icon "th-large"}
                       {:index 1
                        :name "picture"
                        :icon "picture-o"}
                       {:index 2
                        :name "text"
                        :icon "font"}
                       {:index 3
                        :name "background"
                        :icon "tint"}
                       {:index 4
                        :name "upload"
                        :icon "upload"}]
   :selected-index 0
   }})
