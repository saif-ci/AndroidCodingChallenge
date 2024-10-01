

This a simple Android app that exercises the API endpoint `https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=KoRB4K5LRHygfjCL2AH6iQ7NeUqDAGAB` and shows the books returned.

### Requirements:
- Android Studio Koala Feature Drop | 2024.1.2 Patch 1
- JDK 22

### Noteworthy Dependencies:
- Anvil (to simplify Dependency Injection)
- Moshi (for Json parsing)
- RxJava3 (didn't switch to Coroutines to honor using the already included RxJava dependencies)
- Robolectric (for testing classes with Android specific dependencies)


## Screenshots

### Phone

| Portrait                            | Landscape                            |
|-------------------------------------|--------------------------------------|
| ![](screenshots/phone_portrait.png) | ![](screenshots/phone-landscape.png) |

### Tablet

| Regular                     | Error                             |
|-----------------------------|-----------------------------------|
| ![](screenshots/tablet.png) | ![](screenshots/tablet-error.png) |