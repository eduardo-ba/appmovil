# Registro de alumnos · Android

Aplicación académica nativa desarrollada con Kotlin. Permite guardar, buscar y borrar alumnos por matrícula, asociarles una fotografía y consultar una lista almacenada en SQLite.

## Requisitos

- Android Studio compatible con Android Gradle Plugin 8.13.
- JDK 17 o posterior para ejecutar Gradle; el código se compila con destino Java/Kotlin 11.
- Android SDK 36 y un emulador o dispositivo con Android 7.0 (API 24) o posterior.
- Internet para la primera sincronización de dependencias.

## Ejecutar

1. Clona el repositorio y abre su carpeta raíz en Android Studio.
2. Espera la sincronización de Gradle e instala el SDK que solicite el IDE.
3. Selecciona el módulo `app`, elige un emulador o dispositivo y pulsa **Run**.

Para compilar y ejecutar las pruebas unitarias desde la terminal:

```sh
./gradlew testDebugUnitTest assembleDebug
```

En Windows usa `gradlew.bat testDebugUnitTest assembleDebug`. El APK de depuración se genera en `app/build/outputs/apk/debug/`.

## Estructura

- `app/src/main/java/com/example/appunidad02_43_2025/`: actividad, fragmentos y adaptador de la lista.
- `app/src/main/java/com/example/appunidad02_43_2025/database/`: modelo y acceso a SQLite.
- `app/src/main/res/`: pantallas XML, menú, imágenes y temas.
- `gradle/libs.versions.toml`: versiones de plugins y dependencias.

El SDK local se configura en `local.properties`, que queda fuera de Git. Las pruebas de dispositivo requieren un emulador o teléfono conectado.


## Contribuir y organizar commits

Consulta [CONTRIBUTING.md](CONTRIBUTING.md) para crear ramas, escribir commits y verificar los cambios antes de abrir un pull request.
