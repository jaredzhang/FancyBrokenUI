
### What is this project?
This project is meant to simulate [this effect](https://www.uplabs.com/posts/restaurant-app-for-android)

### How do you achieve that?
This project uses this [AndroidTopSheet](https://github.com/TechIsFun/AndroidTopSheet) library to implement the top sliding.
And a [ClipRecyclerView](app/src/main/java/com/jaredzhang/fancybrokenui/widget/ClipRecyclerView.kt) to listen for the sliding, and clip the drawing.

### Why is broken?
The touch event handling is not as nice since you have to scroll outside of the RecyclerView in order to slide the hero image

### Boring details
- It has two flavors fake and cloud, name self explained
- I am not a backend guy, to get a working apk, most likely you have to run `./gradlew :app:assembleFakeDebug`
- To gain some confidence for your production use without a phone or emulator, run `./gradlew  :app:testCloudDebugUnitTest`
- To just show off with emulator, run `./gradlew  :app:connectedFakeDebugAndroidTest`
- Oh yea, one last thing, run on lollipop above.