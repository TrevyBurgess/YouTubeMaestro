# Rename App from YouTubeManager to YouTubeMaestro

This plan covers renaming the project, display names, and remaining theme references to ensure consistency with the name "YouTubeMaestro".

## Proposed Changes

### Project Configuration

#### [MODIFY] [settings.gradle.kts](file:///D:/Dev/GitHub/YouTubeMaestro/Code/settings.gradle.kts)
- Update `rootProject.name` to `"YouTubeMaestro"`.

### Display Names

#### [MODIFY] [strings.xml](file:///D:/Dev/GitHub/YouTubeMaestro/Code/app/src/main/res/values/strings.xml)
- Update `app_name` to `"YouTubeMaestro"`.

#### [MODIFY] [main.kt](file:///D:/Dev/GitHub/YouTubeMaestro/Code/composeApp/src/desktopMain/kotlin/com/cyberfeedforward/youtubemaestro/main.kt)
- Update window `title` to `"YouTubeMaestro"`.

### Theme and Manifest

#### [MODIFY] [AndroidManifest.xml](file:///D:/Dev/GitHub/YouTubeMaestro/Code/app/src/main/AndroidManifest.xml)
- Update theme references from `Theme.YouTubeManager` to `Theme.YouTubeMaestro`.

#### [MODIFY] [themes.xml](file:///D:/Dev/GitHub/YouTubeMaestro/Code/app/src/main/res/values/themes.xml)
- Rename style from `Theme.YouTubeManager` to `Theme.YouTubeMaestro`.

## Verification Plan

### Automated Tests
- Run Gradle sync to ensure project name change is picked up.
- Build the project to verify manifest and theme consistency.

### Manual Verification
- Verify the app name in the launcher (Android).
- Verify the window title on Desktop.
- Check the project name in the IDE.
