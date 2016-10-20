Finstergram
==========
[![Build Status](https://travis-ci.org/chrisdoc/Finstergram.svg?branch=master)](https://travis-ci.org/chrisdoc/Finstergram) Finstergram is here to stay 

Finstergram allows a user to see her nearby images through the Instagram  Api
[Instagram Api](https://www.instagram.com/developer/endpoints/media/). The app itself is written in the MVP Architecture + Dagger.

Testing is done through Junit (JVM) and Espress(UI), both of the test are run on travis-ci.

In order to retrieve images through Instagram an `ACCESS_TOKEN` needs to be [created](https://api.instagram.com/oauth/authorize/?client_id=bb608e380f824b069ffd11a5260a107d&scope=likes+comments+public_content&redirect_uri=https://hookb.in/E7ezabb8&response_type=token).

To configure the app the `ACCESS_TOKEN` needs to be set as a `gradle.property`
```sh
echo "INSTAGRAM_ACCESS_TOKEN=<YOUR_SECURE_ACCESS_TOKEN>" >> ~/.gradle/gradle.properties
```

Finstergram is proud to use the following Open Source Libraries:
 - [Dagger](https://google.github.io/dagger/) - Dependency Injection
 - [OkHttp](http://square.github.io/okhttp/) - Http Client
 - [Retrofit](http://square.github.io/retrofit/) - Hasslefree Api access
 - [Picasso](http://square.github.io/picasso/) - Simple image loader
 - [Moshi](https://github.com/square/moshi) - Great JSON de/serializer
 - [ButterKnife](http://jakewharton.github.io/butterknife/) - View Binding
 - [Support Library](https://developer.android.com/topic/libraries/support-library/index.html) - Material Goodness