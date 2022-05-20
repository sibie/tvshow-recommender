<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="images/action.gif" alt="Logo" width="275" height="200">
  </a>

  <h3 align="center">TV Show Recommender</h3>

  <p align="center">
    Demo Spring Boot project to recommend random shows to users which they haven't seen before.
    <br />
    <a href="https://github.com/sibie/tvshow-recommender"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://tvshow-recommender.herokuapp.com">View Demo</a>
    ·
    <a href="https://github.com/sibie/tvshow-recommender/issues">Report Bug</a>
    ·
    <a href="https://github.com/sibie/tvshow-recommender/issues">Request Feature</a>
  </p>
</div>

## About The Project

This is a demo Spring Boot project for users to get random TV show recommendations that they have never seen before. Some features include:

  * User registration and session management
  * Token verification via email to confirm account creation
  * The service itself which is implemented with Spring modules like MVC, Security and JPA

Sharing this codebase in case it may prove helpful to anyone out there trying to practice their Java and Spring skills.

Do note that I'll be taking down the project from Heroku after May 30th, 2022 as user registration won't work anymore with Gmail SMTP due to changes in their policy for 3rd party use. Might take some time to get the time to switch to a new SMTP provider so here are some screenshots of the app for reference:

To be added.

<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

* [**Java**](https://www.java.com/en/) - Programming language of my choice.
* [**Maven**](https://maven.apache.org/) - Build tool for the project.
* [**SpringBoot**](https://spring.io/projects/spring-boot) - Has everything you need to build out amazing cloud-native web applications.
  * *Spring Security* - Authentication layer of the application including login, logout, session management, etc.
  * *Spring MVC* - To connect backend business logic / CRUD operations with the frontend.
  * *Spring Data JPA* - Auto creation of DB tables and easy mapping to entites and CRUD repositories.
  * *Thymeleaf* - Data binding with front end.
  * *H2* - Embedded DB, since this is a demo project using H2 just makes things easier.
* [**Bootstrap**](https://getbootstrap.com/) - For web page styling and customization.
* **Java Mail Sender with Gmail SMTP** - To send verification links to users so they can enable their accounts.
* **Jasypt and Bcrypt** - Encryption of sensitive application and user data.

<p align="right">(<a href="#top">back to top</a>)</p>



## Getting Started

This project was developed with Java 17, I just made the POM version as 8 so I could deploy my app with my free Heroku account. Overall

### Prerequisites

1. Java 1.8 or above installed on your local system - Spring depedencies will most likely not work with anything pre-8.
2. Any IDE and Build tool of your choice. I opted for IntelliJ and Maven.

### Installation

1. Once you have your local environment set up, simply clone this repo from GitHub:
   ```sh
   git clone https://github.com/sibie/tvshow-recommender.git
   ```
3. If you have Maven configured with your IDE, the build process should start automatically. For command line prompt you can use:
   ```sh
   mvn install
   ```
3. Configure the application.yml file with SMTP details you wish to use. Without this, users won't be able to verify their accounts and use the service. 
   ```yml
   mail:
     host:
     port:
     username:
     password:
     protocol:
     tls: true
     properties.mail.smtp:
       auth: true
       starttls.enable: true
       ssl.trust:
   ```
4. If you don't wish to use encryption, delete the Jasypt block from application.yml file and remove @EnableEncryptableProperties annotation from main class. Otherwise use the Jasypt online tool and secret key of your choice to generate encrypted values for anything you want to protect. Then update into the file.

5.  Run the main application class and you should see it up and running on localhost:8080.

<p align="right">(<a href="#top">back to top</a>)</p>



## Roadmap

I worked on this project over the course of a week in 3 separate sessions:

* **Session 1** - SpringBoot Project and H2 setup, Spring Security integration, Registration service with Email verification using Java Mail Sender + Gmail SMTP.
* **Session 2** - Core Recommendation Service for users to find new TV shows to watch and manage their viewing history.
* **Session 3** - Development and styling of custom pages for both services, login and error handling using Thymeleaf/Bootstrap.

Then I prepared my application build for deployment on to Heroku.

<p align="right">(<a href="#top">back to top</a>)</p>


## Features

* Login, Logout and Session Management - Done using Spring Security.
* Spring MVC used for mapping HTTP requests from UI to backend controllers.
* Added a custom error controller to manage whitelabel errors from the app.
* User Registration Service
  * Users can create new accounts using a simple form, styled with Bootstrap.
  * Made a custom implementation of UserDetails interface to save user data to DB. Passwords are encrypted using BCrypt.
  * Developed a REST API to generate a unique token for the user that expires after 30 mins.
  * Java Mail Sender sends an email using Gmail SMTP with REST link (token appended) so user can verify their account.
  * Until this is completed, users cannot login and use the application.
* TV Show Recommendation Service
  * Entity modeling for TVShow objects. Created associations between User, Token and TVShow tables to drive the application.
  * Used JPA Specification interface to create a custom filter to search for Shows based on user's choice of genre.
  * If the user has already seen the show, they can add it to their watch history, which can be viewed on a separate page.
  * Every row displayed in tables is clicable and will redirect user to the show's IMDB link.

<p align="right">(<a href="#top">back to top</a>)</p>



## Project Structure

Followed a standard MVC approach to organizing the project. Grouped the classes on the basis of their use, i.e. Auth, Registration or Recommendation.

[![Project Structure][project-structure]](images/project-structure.png)



## Project Design

TBC



## Future Improvements

Do note that this is a practice project with the aim of improving my coding skills, so the goal was to demonstrate how features can be implemented rather than making a full-scale production-ready application. If this were to be a real-world app, there's a lot of ways in which this could be improved.

* **Email Validation** - Creating a custom method to validate user email before allowing the registration process to continue. Currently this is not handled.
* **Resend Token** - Sometimes token data can be corrupt or users can miss emails. A utility to resend email with new token would be a handy feature.
* **Robust TV Show Dataset** - Currently I use a premade data.sql file to inject some sample show data into my H2DB at run time. Instead we could set up a new bean that hits a 3rd party API and transforms incoming JSON into SQL records to get us a massive selection of TV Shows for users to browse through.
* **Improved Search Criteria** - I just created one custom spec to filter based on genre to demonstrate my understanding of this interface. We can create more complex predicates which could filter using more criteria, giving users more control over the sort of recommendations they get.
* **Frontend styling** - In the interest of time, I used Thymeleaf to build out my UI. React based solutions are much more powerful and allow for a much cleaner and interactive user experience. We could add an Image table as well to our backend, so we can display show poster on the page. We could also have our own custom TV Show pages (based on ID) rather than redirecting users to IMDB.
* **Comprehensive Error** Handling - Right now, I just have a general error page to handle all codes. Adding custom pages for specific codes would help users to understand whats going wrong when bugs happen. Logging could be improved too to analyze metrics on app performance.
* **Better Testing** - The number of tests I wrote were quite less and that's not optimal. Production-grade apps need maximum coverage so we can catch bugs before they happen when possible.

<p align="right">(<a href="#top">back to top</a>)</p>


## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [**Amigoscode**](https://www.youtube.com/c/amigoscode) - Please subscribe to this channel if you haven't already, best in the game.
* [**MD Bootstrap**](https://mdbootstrap.com/) - For inspiration while styling my web pages.
* [**Spring Community**](https://spring.io/community)

Also it goes without saying, but a huge thanks to the amazing community on Stack Overflow for the hundreds of doubts I've clarified there. :grin:

<p align="right">(<a href="#top">back to top</a>)</p>
