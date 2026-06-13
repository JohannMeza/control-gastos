import os

views_dir = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view"
files = ["DashboardView.java", "SystemView.java", "PresupuestoView.java", "AhorrosView.java", "DeudasView.java"]

for f_name in files:
    path = os.path.join(views_dir, f_name)
    with open(path, "r", encoding="utf-8") as f:
        lines = f.readlines()
    for idx, line in enumerate(lines):
        if "Variables declaration" in line:
            print(f"{f_name}:{idx+1} -> {line.strip()}")
