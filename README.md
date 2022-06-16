<<<<<<< HEAD
<!--
*** Thanks for checking out this README Template. If you have a suggestion that would
*** make this better, please fork the repo and create a pull request or simply open
*** an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
-->

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->

<!-- PROJECT LOGO -->
<br />
<p align="center">
    <img src="./images/logo/logo.png" alt="Logo" width="80" height="80">

  <h1 align="center">Akiyama</h1>

  <h3 align="center">
    An open source website and android app to watch anime
    <br />
  </h3>
</p>

<br>

<p align="center">
    <img  src="images/screenshots/4.jpg" alt="Logo" width="33%" >
  <img  src="images/screenshots/3.jpg" alt="Logo" width="33%" >
</p>
<p align="center">
   <img  src="images/screenshots/1.jpg" alt="Logo" width="33%" >
    <img  src="images/screenshots/2.jpg" alt="Logo" width="33%" >
</p>
<br>

<!-- ABOUT THE PROJECT -->

## About The Project

Akiyama is an open source website and android app to watch anime using the unofficial [AnimeFLV API](https://github.com/Jeluchu/animeflv), developed with the objective of learning and improving my coding skills

All the content that can be found in this application is hosted in third party servers such as Mega, YourUpload, FEMBED, etc. All this services are available for free online. For any legal trouble, related to the content shown in this website, must be addressed with the owners of the servers that are storing this content, as we are not affilliated nor colaborating with them.

### Built With

Web App:

- Angular
- Angular Material

Android App:

- Kotlin
- MVVM architecture

<!-- GETTING STARTED -->

## Getting Started

Before cloning the repo make sure you have installed

- [**NODE**](https://www.google.com/search?q=how+to+install+node) (version >= 12.17.x)
- [**NPM**](https://www.google.com/search?q=how+to+install+npm) (version >= 6.14.x)
- [**Android Studio**](https://developer.android.com/studio)

## Starting a local development server

- Once you have forked or downloaded the project, go into de server directory and install the server dependencies with

```
  npm install
```

- Then you can start the server with

```
  npm run build && npm run start
```

## Note

- By default the android project is configured to run in an emulator, so the API url is 10.0.2.2:4000 which is the equivalent to localhost:4000, if you want to debug the app in a physical device make sure to look inside the file mobile_app/app/build.gradle, then look into the buildTypes and change the url in buildConfigField inside debug to your local ip address.

## Contribution

1. Fork it!
2. Create a branch for the feature you want to make: `git checkout -b my-new-feature`
3. Commit changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request

**Note** both the angular and the android projects have environment variables to connect to your local Aruppi Api server. For default
the android app will connect to your local server only if you are running it on an emulator, if you want to test on a real device, change the variable
in the gradle file, but please don´t commit this change.

<!-- CONTACT -->

## Contact

Fernando Maldonado - [@Fmaldonado4202](https://twitter.com/Fmaldonado4202) - fmaldonado824@gmail.com

Project Link: [https://github.com/Fmaldonado6/Akiyama](https://github.com/Fmaldonado6/Akiyama)
=======
# **Aruppi API** (v3.2.4)

> This API has everything about Japan, from anime, music, radio, images, videos ... to japanese culture 
>
> These are the services used for the Aruppi App (only available in Spanish language)


![node version](https://img.shields.io/badge/node->=12.17.x-brightgreen.svg)
![npm version](https://img.shields.io/badge/npm->=6.14.x-brightgreen.svg)
![type code](https://img.shields.io/badge/aruppi-API-brightgreen.svg)
![maintenance](https://img.shields.io/badge/maintained-Yes-brightgreen.svg)
![now](https://badgen.net/badge/icon/now?icon=now&label)
![gitrepo](https://img.shields.io/github/stars/aruppi/aruppi-api?style=social)
---

<img src="./assets/img/logo.png" width="100%" alt="">

## 📖 API Documentation
Soon we will add the documentation information in a link

## :rocket: Custom Aruppi API Link
Link to access the [Aruppi API](https://aruppi-api.jeluchu.now.sh/api/v2)

## 📚 **Development Diary**
Describe the purpose of the project and give clues about what the code does. 
For more information go to the following link [Diary Reference](./development_diary/README.md).

## **:wrench: Developer usage**

### **Set up project**

Before cloning the repo **be sure** you have installed:

- [**NODE**](https://www.google.com/search?q=how+to+install+node) (version >= 12.17.x)
- [**NPM**](https://www.google.com/search?q=how+to+install+npm) (version >= 6.14.x)

Then:

- Choose a folder project in your system and switch in `cd [folder path]`
- Clone the repo in your folder path `git clone https://github.com/aruppi/aruppi-api`

---

### **Installation**

In order to install the project and all dependencies, enter in the project folder and run `npm install`

---

### Start the project

```bash
npm start
```

### Test the project

```bash
npm test
```

---

## Deprecated v1 for API
Aruppi has grown since it was launched and we need to continue improving the application along with the services to be able to give new features.

But if you need to see the code or the operation of the old version you can do it

- [Aruppi API GitHub (v1) [Deprecated]](https://github.com/aruppi/aruppi-api-v1)
- [Aruppi API Custom Link(v1) [Deprecated]](https://aruppi.herokuapp.com/api/Aruppi/)

## Countdown to deprecation of v2 API

Aruppi has grown since it was launched and we need to continue improving the application along with the services to be able to give new features.

At this time version 2.6.8 will remain functional until Aruppi App users fully migrate to version 1.5.0 of the app

## **:handshake: Contributing**

- Fork it!
- Create your feature branch: `git checkout -b my-new-feature`
- Commit your changes: `git commit -am 'Add some feature'`
- Push to the branch: `git push origin my-new-feature`
- Submit a pull request

---

### **:busts_in_silhouette: Credits**

- [Darkangeel](https://github.com/Darkangeel-hd) (System administration authority (SYSADM))
- [Jéluchu](https://github.com/Jeluchu) (Android Developer, designer, and others)

---

### **:heart: Show your support**

Please :star: this repository if you like it or this project helped you!\
Feel free to open issues or submit pull-requests to help me improving my work.


---

### **📚 Projects that use the API**

<table>
  <tr>
    <td align="center">
      <a href="https://aruppi.jeluchu.com/">
        <img src="https://avatars2.githubusercontent.com/u/38753425?s=200&v=4" width="75px;" alt="Jeluchu"/><br />
          <sub>
            <b>Aruppi</b>
          </sub>
      </a><br/>
        <sub>Anime y Manga</sub>
      </a>
    </td>
  </tr>
</table>


### **:robot: Author**

_*Jéluchu*_

> You can follow me on
[github](https://github.com/Jeluchu)&nbsp;&middot;&nbsp;[twitter](https://twitter.com/Jeluchu)

---

Copyright © 2020 [Jéluchu](https://about.jeluchu.com/).
>>>>>>> 099f534d9c1a9f041b348fdb57e987bb69e1d2ce
