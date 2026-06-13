import os

files = [
    r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\AhorrosView.java",
    r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\PresupuestoView.java",
    r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\SystemView.java",
    r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\DashboardView.java"
]

for path in files:
    name = os.path.basename(path)
    print(f"--- {name} ---")
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()
    
    # Search for GEN-BEGIN and GEN-END
    matches = []
    for line_idx, line in enumerate(content.splitlines()):
        if "GEN-" in line or "editor-fold" in line:
            print(f"{line_idx+1}: {line}")
