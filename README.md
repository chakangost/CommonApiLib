# What is CommonApiLib?
CommonApiLib makes it easy to connection Http protocal.

## Setup


### Gradle

Edit `root/app/build.gradle` like below.

#### JAVA Base
```gradle
dependencies {
    implementation 'com.github.chakangost:CommonApiLib:1.0'
}
```

Edit `root/build.gradle` like below.

```gradle
allprojects {
    repositories {
        .....
        maven { url 'https://jitpack.io' }
    }
}
```

## If you think this library is useful, please press the star button at the top.

## How to use
### Normal

``` Kotlin
val response = ""
val relatedData = HttpGet("www.naver.com/api")
val resCode = relatedData.execute()
if (resCode == 200 || resCode == 201) {
    response = relatedData.responseBody
} 
```

``` JAVA
Http httpGet = new HttpGet("https://httpbin.org/get");
int responseCode = httpGet.execute();
```

## You should be run in UI Thread 

If you think this library is useful, please press the star button at the top.


## License 
 ```code
Copyright 2020 Freddie
```
