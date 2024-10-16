![xxxhdpi-logobanner@4x (1)](https://github.com/user-attachments/assets/b37adab3-b856-41fe-a1e5-6a421ade2742)

![Android Badge](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)

***

![Java Badge](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Groovy Badge](https://img.shields.io/badge/Groovy-4298B8?style=for-the-badge&logo=apache-groovy&logoColor=white)
![Jetpack Navigation Badge](https://img.shields.io/badge/Jetpack%20Navigation-4285F4?style=for-the-badge&logo=android&logoColor=white)
![Retrofit Badge](https://img.shields.io/badge/Retrofit-007AFF?style=for-the-badge&logo=android&logoColor=white)
![Glide Badge](https://img.shields.io/badge/Glide-88E0D0?style=for-the-badge&logo=android&logoColor=white)

***

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

## Table of Contents
- [User Flow](#user-flow)
- [Technologies/Features](#technologiesfeatures)
- [A Real Team Effort](#a-real-team-effort)
    - [Lifecycle & CI/CD](#lifecycle--cicd)
- [Security](#security)
- [Future Considerations](#future-considerations)
- [FAQs](#faqs)
- [Bugs and Contributions](#bugs-and-contributions)
  
## User Flow

The application opens up to a carefully considered home page. The bottom menu bar acts as the main point of navigation within the app, with the top bar allowing users consistent access to their calendar throughout the app. 
The Home page features: 
- A section to display two Events saved by the user
- A search bar, giving another point of access to the Explore page
- A selection of buttons, providing quick access to Event listings filtered by the category of event

<br>
<br>
<p align="left">
  <img src="https://github.com/user-attachments/assets/7a54dfe4-af62-4952-8dd5-1084915e2af5" width="350" />
</p>
<br>

_The RendezVenue Home page_

<br>
<br>

The application allows users to search for events with a range of filtering options. Once an event is found clicking it will open up a more detailed view, allowing a user to add it to their calendar with ease.
Creating events is also a breeze with our fillable form, utilising text fields, dropdown menus and picker dialogs to make the process as easy as possible for the user and robust input sanitisation for our API. Once an event is created a pop-up is displayed, asking the user if they wish to also add the event to their own calendar.

<br>
<br>

<img src="https://github.com/user-attachments/assets/7f4407dc-8200-4181-815c-7403dc68099c" width="300" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://github.com/user-attachments/assets/1e05e6c8-6f79-43eb-8878-6a23fb0c035f" width="300" />
<br>
_Finding Events on RendezVenue_ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; _Creating Events on RendezVenue_



## Technologies/Features

We used Jetpack Navigation for it's robustness, allowing us to give a fluid user experience when navigating between sections of the app. Use of a NavController allowed for smoother navigation between various layers with multiple points of entry. Implementing a NavGraph gave us complete oversight to sensibly structure our navigation paths. It also allowed us to visualise how our data was being passed between fragments, relying on the SafeArgs dependency for inter-fragment type-safe navigation. 

![NavGraph_RV](https://github.com/user-attachments/assets/33c959bb-4a56-4719-9551-41de62f651c0)


We used Glide in our Android frontend to load and display images effectively. Once images are loaded, Glide also facilitates efficient image caching, allowing for a polished and stable user experience while browsing images.

When a user creates an event, we use Dialog boxes and Spinners to help ensure that Date, Time and category related fields were uniformly shaped to work with, not only the internal Calendar Provider API, but also our very own Spring Boot API backend. It is, in fact, through a combination of these dialog's, a helper class (affectionately called the Time Wizard) and the invocation of SafeArgs functions made available via the Jetpack Navigation libraries, that we have been able to format the Date and Time fields differently according to the different funtions. 


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

All data relating to a user's location is AES-256 encrypted for secure transmission. Jenerics Software puts the utmost priority on preserving the confidentiality and integrity of sensitive user-sourced credentials.

For a more in-depth look at how RendezVenue manages end-to-end encryption, please visit our backend, which you can find [here](https://github.com/Jeneric-Java/RendezVenue-API). 

## Future Considerations

- Compatibility testing: pivoting from local to instrumented testing, assessing the application's performance across many devices and API levels.
- Expand on the application themes with custom animations between fragments and a broader revision of stylistic choices not adhering to guidelines on accessibility.
- Loading animation when awaiting a non-cached response to a new GET request, most importantly when a user changes location.
- Dynamic path handling on denial of permissions to access the user's location.
- Thorough sanitisation of user input to text fields.
- Push notifications to remind users of their upcoming events; presently a user can do this via their own calendar.
- GitHub Actions to automate end-of-pipeline testing and enforce rulesets on staging and production branches.

## FAQs

**On which external APIs does RendezVenue depend?**

Our ethos centres on limiting over-reliance on external APIs. We rely solely on Ticketmaster's Discovery API. 

**Where can I get RendezVenue?**

This is a work in progress. Please be aware that some of the critical files, upon which this app is reliant for encrypting traffic, will not be made available with this release. 

**Is RendezVenue available in Kotlin?**

Not presently. This project was foremost an exercise in developing a full-stack Android application in Java. 

**How long is a piece of string?**

Great question. For an empty string, about 40 bytes, I believe.

## Bugs and Contributions

If you find any bugs, please create an issue on the issues page of this repository.

As this project is exclusive to the team at Jenerics Software, we don't plan on accepting any external contributions at this time.
