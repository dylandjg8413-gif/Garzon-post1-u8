PerforLab — Garzón Post1 U8

Aplicación Android desarrollada como evidencia práctica de la Unidad 8: Optimización de rendimiento y experiencia de usuario fluida, en la asignatura Aplicaciones Móviles — Ingeniería de Sistemas, Universidad de Santander (UDES), 2026.

Descripción

Esta aplicación simula un entorno de alta carga mediante una lista de aproximadamente 500 productos cuyos valores de stock cambian de forma periódica.

El objetivo principal es analizar problemas de rendimiento asociados a actualizaciones ineficientes en listas, y posteriormente aplicar técnicas de optimización que mejoran significativamente el uso de CPU, memoria y renderizado.

Se comparan dos enfoques:

Actualización tradicional con notifyDataSetChanged()
Actualización optimizada usando DiffUtil y ListAdapter

Requisitos
Android Studio Hedgehog (2023.1.1) o superior
JDK 17 o superior
Android 8.0+ (API 26+)
Emulador o dispositivo físico (recomendado API 29+)

Configuración y ejecución
Clonar el repositorio:
git clone https://github.com/dylandjg8413-gif/Garzon-post1-u8
Abrir el proyecto en Android Studio
Sincronizar dependencias de Gradle
Ejecutar la aplicación en un dispositivo o emulador

Tecnologías implementadas
Herramienta	Función
RecyclerView	Renderizado eficiente de listas grandes
ListAdapter + DiffUtil	Actualización inteligente de elementos
ViewBinding	Acceso seguro y eficiente a vistas
LiveData	Observación reactiva de datos
ViewModel + Coroutines	Manejo de lógica y concurrencia
Android Profiler	Análisis de rendimiento
Trace	Medición de tiempos de ejecución

Arquitectura del proyecto
com.udes.performancelab
├── ProductItem.kt           → Modelo de datos
├── ProductDiffCallback.kt   → Comparador de listas (DiffUtil)
├── ProductAdapter.kt        → Adaptador optimizado
├── ProductViewModel.kt      → Lógica de actualización periódica
├── ProductFragment.kt       → UI y gestión del ciclo de vida
└── MainActivity.kt          → Contenedor principal

Estrategias de optimización

1. Actualización eficiente con DiffUtil

Se reemplazó el uso de notifyDataSetChanged() por un enfoque basado en ListAdapter, el cual calcula automáticamente las diferencias entre listas.

✔ Solo se actualizan los elementos modificados
✔ Reducción significativa de renderizados innecesarios

2. Configuración de RecyclerView

Se utilizó:

recyclerView.setHasFixedSize(true)

Esto evita cálculos repetitivos del layout cuando los cambios no afectan el tamaño de la lista.

3. Prevención de fugas de memoria

Se implementó la limpieza de recursos en el Fragment:

override fun onDestroyView() {
    super.onDestroyView()
    binding.recyclerView.adapter = null
    _binding = null
}

✔ Evita referencias a vistas destruidas
✔ Mejora el uso de memoria

4. Instrumentación de rendimiento

Se añadieron trazas personalizadas usando:

Trace.beginSection("update_list")

✔ Permite analizar tiempos en el System Trace
✔ Facilita identificar cuellos de botella

Evaluación de rendimiento

Uso de CPU (sin optimización)
Alta carga de CPU en cada actualización
Frames omitidos detectados
Renderizado completo de la lista en cada cambio

Uso de CPU (optimizado)
Consumo reducido (3% - 5%)
Actualización selectiva de elementos
Eliminación de bloqueos visuales

Uso de memoria
Sin fugas detectadas
Uso estable del heap
Eliminación de referencias innecesarias

Resultados obtenidos
Mejora significativa en fluidez
Reducción de uso de CPU
Optimización del renderizado
Mayor estabilidad de la aplicación

Conclusiones

La implementación de buenas prácticas como DiffUtil, manejo adecuado del ciclo de vida y medición de rendimiento permite desarrollar aplicaciones más eficientes y escalables.

Este proyecto demuestra cómo pequeñas mejoras en la arquitectura pueden generar grandes diferencias en la experiencia del usuario.
---

## Capturas de pantalla

### CPU Profiler — Antes de DiffUtil (baseline)

![CPU antes](capturas/screenshot_cpu_before.png)

### CPU Profiler — Después de DiffUtil (optimizado)

![CPU después](capturas/screenshot_cpu_after.png)

### Memory Profiler — Heap sin retained instances

![Memory Profiler](capturas/screenshot_memory_profiler.png)

---
