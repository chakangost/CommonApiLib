What is CommonApiLib?
CommonApiLib makes it easy to connection Http protocal.

Setup
Gradle
Edit root/app/build.gradle like below.

Kotlin Base
dependencies {
    implementation 'com.github.chakangost:CommonApiLib:1.0'
}
Edit root/build.gradle like below.

allprojects {
    repositories {
        .....
        maven { url 'https://jitpack.io' }
    }
}
If you think this library is useful, please press the star button at the top.

How to use
Normal

Http httpGet = new HttpGet("https://httpbin.org/get");
int responseCode = httpGet.execute();
