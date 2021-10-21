(ns api-azure-functions.example-test
  (:require [clojure.test :refer [deftest is testing async]]))

(deftest -it-works
  (testing "works!"
    (async done
      (is (true? false))
      (done))
    )
  )
