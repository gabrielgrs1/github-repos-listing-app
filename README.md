<p align="center">
  <a href="https://github.com/gabrielgrs1/github-repos-listing-app/actions"><img alt="Build Status" src="https://github.com/gabrielgrs1/github-repos-listing-app/workflows/Android%20CI%20(Push)/badge.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
</p>
<h1 align="center">Github Kotlin List Repositories</h1>

A demo app using [github search api](https://docs.github.com/en/rest/search?apiVersion=2022-11-28#search-repositories) to prove concept on Android Development best pratice.

## Tech Stack

### Code

- Minimum SDK Level 21
- Clean Architeture + MVVM
- [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous operations.
- [Retrofit2](https://github.com/square/retrofit) to make HTTP calls to the REST API.
- [GSON](https://github.com/google/gson) to deserialize JSON requests.
- [Okhttp3](https://github.com/square/okhttp) to configure network.
- [Picasso](https://github.com/square/picasso) for image loading.
- Android Architeture Components
    - [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    - [ViewBinding](https://developer.android.com/topic/libraries/view-binding)
- [Koin](https://github.com/InsertKoinIO/koin) for Dependency Injection

### Tests

- [Robolectric](https://github.com/robolectric/robolectric) and [AndroidX Test libraries](https://developer.android.com/training/testing) for Unit Testing.
- [Mockk](https://github.com/mockk/mockk) to create the mocks used in the Unit Tests.
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) to mock web server
 calls.
- [Turbine](https://github.com/cashapp/turbine) to test flows.
