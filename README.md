![xxxhdpi-logobanner@4x (1)](https://github.com/user-attachments/assets/b37adab3-b856-41fe-a1e5-6a421ade2742)

# RendezVenue® by Jenerics Software  
This is your new go-to app for retrieving, collecting, and curating events on the fly within an Android environment. RendezVenue simplifies the hassle of scouring event content with a targeted search functionality to get the events you want, 
where and when you need them.

Do you prefer working with Outlook, TimeTree, or maybe even simply Google Calendar? No problem, we’ve got you covered. Effortlessly synchronised to a local calendar app of your choosing, RendezVenue takes a holistic approach with full system integration.

> [!NOTE]  
> This repository, and any of the files contained within, is part of a larger project.
>
> This Android application relies upon data and services provided to it by the RendezVenue API.
> 
> The RendezVenue API exists in a separate repository which you can find [here](https://github.com/Jeneric-Java/RendezVenue-API).

## Overview

RendezVenue boasts a slick UI experience, with automatic background location filtering kicking in from the get-go, granting instant access to events as far as the eye can see. Entrusting response caching and computationally-demanding processes 
to the backend keeps the user experience uninterrupted. The frontend team have worked hard on embellishing the visuals with snappy graphics, elegantly designed, laid out, and all carefully chosen to complement the overarching theme.

## User Manual

_This is where we will include screenshots of the fragments/activities to show the flow of the program.
Alongside this will be a brief commentary to guide the user through operating the app._

## Technologies/Features

_I will include some information here below from the website, but that is just for your reference - use it as you need. 
This is the major section for all the things Jack and Joel did and the technologies you used so I leave it to you guys to decide how to structure._

We used Jetpack Navigation for it's robustness, allowing us to give a fluid user experience when navigating between sections of the app. Use of a NavController allowed for smoother navigation between various layers with multiple points of entry. Implementing a NavGraph gave us complete oversight to sensibly structure our navigation paths. It also allowed us to visualise how our data was being passed between fragments, relying on the SafeArgs dependency for inter-fragment type-safe navigation. We used Glide in our Android frontend to load and display images effectively. Once images are loaded, Glide also facilitates efficient image caching, allowing for a polished and stable user experience while browsing images.

![Screenshot from 2024-10-14 20-03-09](https://github.com/user-attachments/assets/f2c6e815-cdf1-446e-9621-344b15444c67)

Creating Dialog boxes to ensure that Date and Time fields were uniformly shaped to work with, not only the internal Calendar Provider API, but also our very own Spring Boot API backend. It was, in fact, through a combination of helper classes and the invocation of SafeArgs functions made available via the Jetpack Navigation libraries, that we found a solution, transforming the Date and Time fields accordingly. Adapting the app’s theme also proved more difficult than we had anticipated. The high level of thematic customisability was a mixed blessing; a stark reminder that with great power comes similarly great volumes of dispiritingly dense documentation.


## A Real Team Effort

We worked to a tight MVP, building from a clear concept to a multi-faceted, fully-functioning, and bespokely-crafted implementation. The idea for RendezVenue evolved from a desire to create a product from which all team members would benefit. This 
was about the group, not the individual; we wanted everyone to feel that the project was as much their own as it was anyone else's. 

As the project progressed, it really began to take on a life of its own. With each member taking on a particular area of interest, it is within that defined scope of operations that they were afforded the time and space to carry out targeted 
research, and so incorporate new technologies thus enhancing their code. This approach not only helped to streamline team resources, but allowed us to build upon existing skillsets to qualify the project with a unique blend of functionalities. 

### Lifecycle & CI/CD

We were acutely constrained for time, so we introduced agile methodologies early on. Regular stand-ups kept the group informed and aligned, and was the source point for ticket creation within a Kanban-style workflow. Starting from a group Excalidraw, we were able to assess operational and technical feasibilities, relating to risks and legal considerations. It was during this research stage that we were able to distil our project aims down to a discrete set of clear, concise, and testable user stories. 

We took the time to structure our CI/CD pipeline appropriately with a standardised set of instructions serving as the common point of reference for all team members. This not only encouraged us to keep our local work states consistent but helped guard against misuse, ensuring that privileged branches were free of unstable, broken or poorly-tested code. We produced most of our work inside of feature branches. These provisional working directories do not track a remote branch. This goes back to our earlier point about guarding against accidental pollution of the parent branches. 

##### Our early sketch-ups

_Flowcharts_

![image](https://github.com/user-attachments/assets/30d7d0b7-24ce-4d91-88f7-d03ca96e015b)

_UI Prototypes_

![image](https://github.com/user-attachments/assets/c58b617d-8568-452f-82b2-d918178b7b8e)

_First Ideas_

![image](https://github.com/user-attachments/assets/07cc21eb-5f70-4e81-81f7-53abd38a81b5)

_Preserving the Pipeline_

![image](https://github.com/user-attachments/assets/bc09d774-4082-4adf-9f1e-2a8eab1f7c35)

## Security

All data relating to a user's location is encrypted for secure transmission. Jenerics Software puts the utmost priority on preserving the confidentiality and integrity of sensitive user-sourced credentials. We rely on 256-bit AES encryption.

For a more in-depth look at how RendezVenue manages end-to-end encryption, please visit our backend, which you can find [here](https://github.com/Jeneric-Java/RendezVenue-API). 

## FAQs

**On which external APIs does RendezVenue depend?**

Our ethos centres on limiting over-reliance on external APIs. We rely solely on Ticketmaster's Discovery API. 

**Where can I get RendezVenue?**

This is a work in progress. Please be aware that some of the critical files, upon which this app is reliant for AES encryption, will not be made available with this release. 

**Is RendezVenue available in Kotlin?**

Not presently. This project was foremost an exercise in developing a full-stack Android application in Java. 

**How long is a piece of string?**

Great question. For an empty string, about 40 bytes, I believe.
