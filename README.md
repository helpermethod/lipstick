# 💄 lipstick

Turn ugly Java builders into beautiful, type-safe Kotlin DSLs.

```kotlin
val client: HttpClient =
    HttpClient
        .newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .build()
```

```kotlin
val client = httpClient {
    version = HttpClient.Version.HTTP_1_1
}
```
