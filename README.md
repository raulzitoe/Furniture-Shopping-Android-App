# Android Furniture Shopping App (Work in Progress)
This application was created based on a free figma UI kit created by Bruno ([Link](https://www.figma.com/community/file/1005321051941014567))

## Features
* Login/Sign Up pages
* Main page with 4 different bottom menu tabs:
  - Home: Product list and price
  - Favorites: Favorite products saved by the user
  - Notifications: List with notifications generated by the app
  - Profile: Information and settings related to the user account
* Insert product to cart process and layout (WiP)
* Payment/checkout process and layout (WiP)
* Rate and Review layout (WiP)
* Past Orders and Settings layout (WiP)

## Android Components
  * Architecture consists in 2 Activities:
    - LoginSignUpActivity controls the login/sign up fragments
    - MainActivity controls the rest of the shopping UI fragments
  * Navigation Graphs: 2 graphs where each control one Activity and they are connected through one action
  * ConstraintLayout as the base for all layouts
  * RecyclerView
  * Material.io components such as:
      - App Bar
      - Toggle Button
      - Card
      - Text Input
      - Shapeable Image View
      - Bottom Navigation View
   * MVVM (Model View ViewModel) Architecture
   * Fetch data from external repository - Uses Firebase Real-time database and Storage


App video overview

https://user-images.githubusercontent.com/37592896/158886600-5c9e787c-65e8-4147-92c6-badd93ae1268.mp4


   
