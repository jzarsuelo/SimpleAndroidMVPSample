# Sunshine

This is the same project from <b>Udacity's Project Sunshine</b> implemented by following Google's [MVP Android Architecture Blueprint](https://github.com/googlesamples/android-architecture/tree/todo-mvp/). 


### Supported Version

- Target SDK Version - Android 8.1 (API Level 27)
- Minimum SDK Version - Android 4.1 (API Level 16)

### Prerequisites

* [OpenWeatherMap](https://openweathermap.org) Account

### Installing

1. Clone the project repository

```
$ git clone https://github.com/jzarsuelo/Sunshine
```

2. Import the project in Android Studio

3. Build and run the project. Make sure the Android version of the device or emulator is supported.

4. In the `local.properties` add the line below. No need for the square bracket.

```
openWeatherMapApiKey=[PLACE YOUR OpenWeatherMap API KEY]
```

You can get the API KEY in [OpenWeatherMap](https://openweathermap.org). If you don't have `local.properties`, you can create one inside `app` directory.


## Built With

* Android Room
* [Retrofit](https://github.com/square/retrofit) - HTTP client
* [Kotpref](https://github.com/chibatching/Kotpref) - Android SharedPreference delegation
* [Glide](https://github.com/bumptech/glide) - Image loading and caching for Android


