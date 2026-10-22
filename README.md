# Juego del Ahorcado

Versión para Android del clásico juego del ahorcado. El jugador adivina la palabra letra por letra y cada error dibuja una parte del muñeco; con seis errores pierde la partida.

## Funcionalidades

- Palabras relacionadas con programación: `ANDROID`, `BUCLES`, `WHILE`, `FOR`, `IOS` y `BOTONES`.
- La palabra se muestra como guiones bajos y las letras acertadas se revelan en su posición.
- Cada error muestra una parte del muñeco (cabeza, cuerpo, brazos y piernas), hasta **6 intentos**.
- Mensaje de **"Ganaste"** al completar la palabra y diálogo de **"Perdiste"** con opción de volver a jugar.
- Botón **Limpiar** para pasar a la siguiente palabra y reiniciar el tablero.

## Tecnologías

- Java
- Android SDK (AppCompat, ConstraintLayout, Material Components)
- Gradle (Kotlin DSL)

## Requisitos

- Android Studio (versión reciente)
- Android 11 o superior (`minSdk 30`, `compileSdk 34`)

## Cómo ejecutarlo

1. Clona el repositorio:
   ```bash
   git clone https://github.com/iantrejogarcia-del/juego_ahorcado.git
   ```
2. Abre el proyecto en Android Studio y espera a que termine la sincronización de Gradle.
3. Conecta un dispositivo Android o inicia un emulador.
4. Presiona **Run**.

## Cómo se juega

1. Escribe una letra en el campo de texto.
2. Presiona **Verificar**.
3. Si la letra está en la palabra, aparece en su lugar; si no, se dibuja una parte del muñeco.
4. Gana al completar la palabra; pierdes al llegar a 6 errores.

## Estructura del proyecto

```
app/src/main/
├── java/com/example/juego_ahorcado/
│   └── MainActivity.java    # Lógica del juego
└── res/
    ├── drawable/            # Piezas del muñeco y fondos
    └── layout/              # Pantalla principal
```

## Posibles mejoras

- Agregar más palabras o cargarlas desde un archivo.
- Elegir las palabras al azar en lugar de en orden.
- Llevar un marcador de partidas ganadas y perdidas.
- Niveles de dificultad.

## Autor

**Ian Trejo García** — Estudiante de Ingeniería en Sistemas Computacionales
GitHub: [iantrejogarcia-del](https://github.com/iantrejogarcia-del)
