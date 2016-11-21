(ns ecard.components.selection-box)

(defn selection-box [top left width height rotate parent-offset-y parent-offset-x onRotate onMove]
    (let [rotate-anchor-x (+ left (/ width 2))
          rotate-anchor-y (+ top (/ height 2))
          transform  (atom {})]
        (def moveMouseMoveHandler)
        (def moveMouseUpHandler)
        (def scaleMouseMoveHandler)
        (def scaleMouseUpHandler)
        (def rotateMouseMoveHandler)
        (def rotateMouseUpHandler)

        (def selection-box-style 
            {:transform (str "translate(" left "px, " top "px) rotateZ(" rotate "deg)")
             :width width
             :height height})
            
        ;/////////////////////////////
        ;/// ROTATE HANDLER
        (defn btnRotate_MouseMoveHandler  [event]
            ; mouse-x = event.clientX - parent-offset-x - rotate-anchor-x
            ; mouse-y = event.clientY - parent-offset-y - rotate-anchor-y
            (let [mouse-x (- (.-clientX event) parent-offset-x rotate-anchor-x)
                  mouse-y (- (.-clientY event) parent-offset-y rotate-anchor-y)
                  ; rotate-end = 57.2958 * atan(mouse-y / mouse-x) + (mouse-x < 0)?270:90
                  rotate-end 
                    (+ (if (< mouse-x 0) 270 90)  
                       (*  (Math/atan (/ mouse-y mouse-x)) 57.2958 ))]
                (onRotate rotate-end)))
        (defn btnRotate_MouseUpHandler [event]
            (js/removeEventListener "mousemove" rotateMouseMoveHandler)
            (js/removeEventListener "mouseup" rotateMouseUpHandler))
        (defn btnRotate_MouseDownHandler [event]
            (.preventDefault event)
            (set! rotateMouseMoveHandler btnRotate_MouseMoveHandler)
            (set! rotateMouseUpHandler btnRotate_MouseUpHandler)
            ; removeEventListener need ref of listener function
            (js/addEventListener "mousemove" rotateMouseMoveHandler)
            (js/addEventListener "mouseup" rotateMouseUpHandler))

        ;/////////////////////////////
        ;/// SCALE HANDLER    
        (defn btnScaleMouseMoveHandler [event]
            (js/console.log "scale"))
        (defn btnScaleMouseUpHandler [event]
            (js/removeEventListener "mousemove" scaleMouseMoveHandler)
            (js/removeEventListener "mouseup" scaleMouseUpHandler)) 
        (defn btnScaleMouseDownHandler [event]
            (.preventDefault event)
            (set! scaleMouseMoveHandler btnScaleMouseMoveHandler)
            (set! scaleMouseUpHandler btnScaleMouseUpHandler)
            ; removeEventListener need ref of listener function
            (js/addEventListener "mousemove" scaleMouseMoveHandler)
            (js/addEventListener "mouseup" scaleMouseUpHandler))

        ;/////////////////////////////
        ;/// MOVE HANDLER
        (defn btnMoveMouseMoveHandler [event]
            ; mouse-x = event.clientX - parent-offset-x
            ; mouse-y = event.clientY - parent-offset-y
            (let [mouse-x (- (.-clientX event) parent-offset-x)
                  mouse-y (- (.-clientY event) parent-offset-y)]
                (onMove 
                    ; top = translate-y + mouse-y - mouse-start-y
                    ; left = translate-x + mouse-x - mouse-start-x
                    (+ (:translate-y @transform) (- mouse-y (:mouse-start-y @transform)))
                    (+ (:translate-x @transform) (- mouse-x (:mouse-start-x @transform))))))
        (defn btnMoveMouseUpHandler [event]
            (js/removeEventListener "mousemove" moveMouseMoveHandler)
            (js/removeEventListener "mouseup" moveMouseUpHandler))
        (defn btnMoveMouseDownHandler [event top left]
            (.preventDefault event)
            ; mouse-x = event.clientX - parent-offset-x
            ; mouse-y = event.clientY - parent-offset-y
            (let [mouse-x (- (.-clientX event) parent-offset-x)
                  mouse-y (- (.-clientY event) parent-offset-y)]
                ;transform.mouse-start-x = mouse-x
                (swap! transform assoc :mouse-start-x  mouse-x)
                (swap! transform assoc :mouse-start-y mouse-y)
                (swap! transform assoc :translate-x left)
                (swap! transform assoc :translate-y top)
                (set! moveMouseMoveHandler btnMoveMouseMoveHandler)
                (set! moveMouseUpHandler btnMoveMouseUpHandler)
                ; removeEventListener need ref of listener function
                (js/addEventListener "mousemove" moveMouseMoveHandler)
                (js/addEventListener "mouseup" moveMouseUpHandler)))
  
        [:div { :class "selection-box cornerHandles" 
            :style selection-box-style
            :onMouseDown (fn [e] (if (= "DIV" e.target.tagName)
                            (btnMoveMouseDownHandler e top left)
                            (if (= "SPAN" e.target.tagName) (btnScaleMouseDownHandler e) )))}
            [:div  { :class "inner"}]
            [:span { :class "handle tl"}]
            [:span { :class "handle tr"}]
            [:span { :class "handle bl"}]
            [:span { :class "handle br"}]
            [:a { :class "rotate"
                    :onMouseDown btnRotate_MouseDownHandler}]
            [:div  { :class "move"}]]))
    