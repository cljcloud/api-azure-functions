(ns api-azure-functions.api
  (:require [environ.core :refer [env]]
            [cljs.core.async :refer-macros [go]]
            [cljs.core.async.interop :refer-macros [<p!]]
            [api-azure-functions.cljcloud.azure
             :refer-macros [defhttp]
             :refer [json-err json-ok]]))

(defn utc-now []
  (js/Date.))

(defhttp products
         :route "api/utc-now"
         :handler (fn [ctx req res]
                    (go
                      (try
                        (prn [:req req])
                        (let [data {:time  (utc-now)
                                    :query (:query req)}]
                          (->> data
                               clj->js
                               json-ok
                               res))
                        (catch :default err
                          (prn [:api-error err])
                          (-> {:error   true
                               :message "Error code 1111"}
                              clj->js
                              json-err
                              res))))))
