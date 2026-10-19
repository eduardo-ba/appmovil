# Cómo trabajar en este repositorio

## Un cambio por rama

Actualiza tu copia y crea una rama con un nombre descriptivo:

```sh
git switch main
git pull --ff-only
git switch -c docs/mejorar-readme
```

Si tienes cambios locales pendientes, haz un commit o guárdalos antes de cambiar de rama. Usa `feat/`, `fix/`, `docs/` o `chore/` según el trabajo.

## Commits claros

Antes de confirmar, revisa `git status` y `git diff`. Añade archivos concretos, por ejemplo `git add README.md`, y revisa `git diff --cached`.

Escribe el mensaje con el formato `tipo: cambio concreto` o `tipo(área): cambio concreto`:

- `feat: agregar una función`
- `fix: corregir un error`
- `docs: explicar cómo ejecutar el proyecto`
- `chore: actualizar archivos de mantenimiento`

Cada commit debe resolver una tarea. Evita mensajes como `cambios`, `fixes` o `primer commit` cuando ya hay historial. No incluyas contraseñas, archivos de entorno ni resultados de compilación.

## Verificar antes de subir

Abre el proyecto en Android Studio y sincroniza Gradle. Ejecuta `./gradlew testDebugUnitTest assembleDebug` (Windows: `gradlew.bat testDebugUnitTest assembleDebug`) con JDK 17 o posterior y Android SDK 36. Prueba guardar, buscar, borrar y listar alumnos en un emulador.

## Pull request

Sube la rama con `git push -u origin HEAD` y abre un pull request hacia `main`. Explica el cambio y cómo lo revisaste; añade capturas si cambió la interfaz.

Para un PR con varios commits de una misma tarea, usa **Squash and merge** si está disponible y pon un título descriptivo al commit resultante. Después actualiza tu rama `main` con `git pull --ff-only`.

Conserva los commits publicados: este flujo ordena el trabajo nuevo sin reescribir el historial compartido.
