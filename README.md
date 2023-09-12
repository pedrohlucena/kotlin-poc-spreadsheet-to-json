# Introduction
POC that I made of a piece of code for a high school project. It consists of a function, written in Kotlin, that reads a spreadsheet in the user's cellphone and returns a string in a JSON format, with the following format:

```
{
  "column1": ["value1", "value2", "value3"],
  "column2": ["value1", "value2", "value3"],
  "column3": ["value1", "value2", "value3"]
}
```

# Dependencies
I used the following dependencies to build the function:
- org.apache.poi:poi-ooxml:5.2.3
- org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0

# Required permissions
To the function run, the user must permit the app to read the external storage of its cellphone. You can do this by pasting the following code:

`<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />`

In the "AndroidManifest.xml" file, like this:

```
<manifest ... >
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

    <application
        ...
    </application>
</manifest>
```
In addition, the user has to allow the app to access its storage in "App Info" section.

# Minimum SDK
POC performed with API 27 "Oreo" (Android 8.1).
