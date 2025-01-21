(ns viewer.events
  (:require
   [re-frame.core :as rf]
   [viewer.db :as db]
   ))

(rf/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(rf/reg-event-db
 ::square-selected
 (fn [db [_ square]]

   (js/alert (str square))



   )
 )


