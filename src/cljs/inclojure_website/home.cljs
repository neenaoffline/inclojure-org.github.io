(ns inclojure-website.home
  (:require [inclojure-website.layout :as layout]
            [clojure.string :as string]
            [inclojure-website.talks :as talks]))

(defn intro []
  [:div.section
   [:p.section__content
    "IN/Clojure is India's annual Clojure conference. It is also the only Clojure conference in all of Asia. IN/Clojure’s primary focus is the free exchange of ideas between new and experienced Clojure programmers alike. IN/Clojure 2019 is the third edition of Asia's very first Clojure conference, and is scheduled to be held in "
    [:strong "Bengaluru, on 11th and 12th January,  2019."]]])

(defn event-detail [{:keys [title speakers company] :as talk}]
  [:a {:href (str "/talks#" (talks/talk-id talk))}
   [:div
    title
    [:br]
    [:div.talk-speakers
     (for [{:keys [name link]} speakers]
       [:span (str name ", " company)])]
    (when (= "Lightning talk" type) "TBD")]])

(defn schedule []
  [layout/section "schedule" "Schedule"
   [:div
    [:p "Day 1: Beginner and Intermediate workshop"]
    [:p "Day 2: The tentative schedule is as follows"]
    [:table.schedule-table
     [:thead
      [:tr
       (for [h [:time :event :type :duration]]
         [:th
          {:style {:text-align "left"}}
          (string/capitalize (name h))])]]
     [:tbody
      (for [{:keys [selected-talk? duration type time speakers company title link] :as talk} talks/selected-talks]
        [:tr
         {:class (when (and (nil? speakers) (not= "Lightning talk" type)) "non-talk")}
         [:td.td-time time]
         [:td.td-event
          (cond
            (= "Lightning talk" type) "TBD"
            selected-talk?            [event-detail talk]
            :else                     title)]
         [:td type]
         [:td duration]])]]]])

(defn workshops []
  [layout/section "workshops" "Workshops (11th January)"
   [:div
    [:p "We will have two workshops at IN/Clojure, an introductory Clojure workshop and an intermediate Clojure workshop. Note that both the workshops will be
         conducted simultaneously on the same day. Therefore, one cannot attend both the workshops."]
    [:h4 "Introductory workshop"]
    [:h5 "Audience"]
    [:p "Practising programmers and/or CS undergrads/grads who are new to Clojure (i.e. not absolute beginners in programming)."]
    [:h5 "Session outline"]
    [:div
     [:p "This will be a day-long hands-on workshop."]
     [:p "We will progressively grow logic for a little data-processing utility, by building concept upon concept until we have a working application."]
     [:p "We will try to achieve this with:"]
     [:ul
      [:li "A small set of core ideas,"]
      [:li "a handful of Clojure primitives, a few API design techniques, and"]
      [:li "an interactive (REPL-driven) programming workflow."]]
     [:p "We will do the following:"]
     [:ul
      [:li "Model things with pure data,"]
      [:li "design clean functional APIs,"]
      [:li "compose purely functional logic, and"]
      [:li "use the REPL to interactively \"grow\" and introspect code."]]
     [:p "Fully documented workshop material will be available for use at home."]]

    [:h4 "Intermediate workshop"]
    [:h5 "Audience"]
    [:p "Working Clojure programmers who are relatively new to the language, but have written some Clojure code, perhaps under someone else's supervision."]
    [:h5 "Session outline"]
    [:p "This will be a day-long hands-on workshop."]
    [:p "The aim of this workshop is to help the attendee work in Clojure more effectively. We will learn about the application of the following concepts:"]
    [:ul
     [:li "Java interop"]
     [:li "Polymorphism using Multimethods and Protocols"]
     [:li "Laziness"]
     [:li "Concurrency in Clojure"
      [:ul
       [:li "Basic: Futures, Promises, Atoms and Agents"]
       [:li "Advanced: Refs and Software Transactional Memory"]]]
     [:li "Macros"]
     [:li "Testing"]]
    [:p "To help illustrate and understand the application of these concepts, we will build a project that ties all these concepts together."]]])

(defn tickets []
  [layout/section "tickets" "Tickets"
   [:div
    [:iframe {:src "https://www.townscript.com/widget/inclojure-2019" :frameBorder "0" :height "500" :width "80%"}]
    [:p
     "Alternatively, tickets can be purchased directly at "
     [:a {:href "https://www.townscript.com/e/inclojure-2019/booking" :target "_blank"} "townscript."]]]])

(defn benefactor [benefactor-name site-link logo-src]
  [:li.benefactor
   [:a {:href site-link :target "_blank"}
    [:img.benefactor-logo {:alt benefactor-name,
                           :src logo-src
                           :href "/"}]]])

(defn sponsorship []
  [layout/section "sponsorship" "Sponsorship"
   [:p
    [:div.benefactors.gold
     [:p.benefactor-slab
      "Platinum"]
     [:ul.benefactors-list
      [benefactor "Go-Jek" "https://www.go-jek.com/" "images/sponsor-logos/go-jek.png"]
      [benefactor "Helpshift" "https://www.helpshift.com" "images/sponsor-logos/helpshift.png"]]]
    [:div.benefactors.gold
     [:p.benefactor-slab
      "Gold"]
     [:ul.benefactors-list
      [benefactor "nilenso" "https://nilenso.com" "images/sponsor-logos/nilenso.png"]
      [benefactor "Thoughtworks" "https://www.thoughtworks.com" "images/sponsor-logos/thoughtworks.png"]]]
    [:div.benefactors.silver
     [:p.benefactor-slab
      "Silver"]
     [:ul.benefactors-list
      [benefactor "Formcept" "https://www.formcept.com" "images/sponsor-logos/formcept.png"]
      [benefactor "Concur" "https://www.concur.co.in" "images/sponsor-logos/sap-concur.png"]]]
    "Sponsorship details are available as a prospectus "
    [:a {:href "pdf/inclojure-sponsorship.pdf" :target "_blank"} "here"]
    ". If you'd like to sponsor IN/Clojure 2019, check out the prospectus or contact us at "
    [:a {:href "mailto:events@inclojure.org"} "events@inclojure.org."]]])

(defn code-of-conduct []
  [layout/section "conduct" "Code Of Conduct"
   [:div
    [:p "Our conference is dedicated to providing a harassment-free conference experience for everyone, regardless of gender, gender identity and expression, age, sexual orientation, disability, physical appearance, body size, race, ethnicity, religion (or lack thereof), or technology choices."]
    [:p "We do not tolerate harassment of conference participants in any form."]
    [:p "Sexual language and imagery is not appropriate for any conference venue, including talks, workshops, parties, Twitter and other online media."]
    [:p "Conference participants violating these rules may be sanctioned or expelled from the conference without a refund at the discretion  of the conference organisers."]]])

(defn opportunity-grant []
  [layout/section "opgrant" "Opportunity Grant"
   [:div
    [:p "IN/Clojure aims to be an inclusive conference. We're really glad to announce an opportunity grant for community members who would be unable to attend the conference for financial reasons. The opportunity grant includes a free ticket and travel assistance."]
    [:p "Please fill out this "
     [:a {:href   "https://goo.gl/forms/QYvM8FRjRV5LC0jn2"
          :target "_blank"}
      "form"]
     " to apply for an opportunity grant. Please do share the link with your friends who might be interested."]
    [:p "We want our conference to have a diverse set of attendees. Ashe Dryden has written a "
     [:a {:href   "https://www.ashedryden.com/blog/increasing-diversity-at-your-conference"
          :target "_blank"}
      "detailed post"]
     " on how to improve diversity at conferences, and it includes a section on what \"diversity\" means. We will try our best to be truly diverse."]
    [:p "Preference will be given to attendees who fall into an under-represented category in tech - women, members of the LGBTQ community, people with physical disabilities, students, and any other under-represented group."]
    [:p "If you are unsure about applying for an opportunity grant because you do not fall into any of these categories, please do apply explaining why you need assistance, and we will try to help you make it to the conference."]
    [:p "Decisions on the opportunity grant will be made by the organizing team. Preference for travel assistance will be given to participants from the Bengaluru region and other cities in India (due to budget constraints). We are open to consider applications from outside India as well."]]])

(def members
  [{:name "Aditya Athalye" :twitter-link "http://twitter.com/adityaathalye"}
   {:name "Akshay Gupta" :twitter-link "http://twitter.com/kitallis"}
   {:name "Deepa Venkatraman" :twitter-link "http://twitter.com/deepa_v"}
   {:name "Kiran Gangadharan" :twitter-link "http://twitter.com/kirang89"}
   {:name "Nivedita Priyadarshini" :twitter-link "http://twitter.com/nid90"}
   {:name "Sandilya Jandhyala" :twitter-link "http://twitter.com/jysandilya"}
   {:name "Srihari Sriraman" :twitter-link "http://twitter.com/sriharisriraman"}])

(defn team-member [twitter-link name]
  [:a.team-member
   {:href twitter-link :target "_blank"}
   name])

(defn team []
  [layout/section "team" "Team"
   [:div.team-members
    (for [{:keys [twitter-link name]} members]
      ^{:key (str (random-uuid))}
      [team-member twitter-link name])]])

(defn invited-speakers []
  [layout/section "invited-speakers" "Keynote Speaker"
   [:div.speaker
    [:img.speaker-img
     {:alt "Eric Normand", :src "images/speakers/eric-normand.jpg"}]
    [:div.speaker-about
     [:p.speaker-name "Eric Normand"]
     [:p.speaker-desc
      [:a {:target "_blank" :href "https://lispcast.com"} "Writer"]
      ", trainer, and speaker. Runs "
      [:a {:target "_blank" :href "https://purelyfunctional.tv/"} "purelyfunctional.tv"]
      ", and organises "
      [:a {:target "_blank" :href "https://clojuresync.com/"} "Clojure SYNC."]]]]])


(defn page []
  [layout/page
   [:div
    [layout/header]
    [layout/navigation]
    [intro]
    [invited-speakers]
    [schedule]
    [workshops]
    [sponsorship]
    [tickets]
    [opportunity-grant]
    [code-of-conduct]
    [team]]])
