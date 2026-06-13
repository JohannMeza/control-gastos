import os
import re

views_dir = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view"
files = ["DashboardView.java", "SystemView.java", "PresupuestoView.java", "AhorrosView.java", "DeudasView.java", "UsuariosView.java"]

for f_name in files:
    path = os.path.join(views_dir, f_name)
    if not os.path.exists(path):
        print(f"{f_name} not found.")
        continue
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()
    
    print(f"\n=== {f_name} ===")
    # Find the SidebarLayout.setHorizontalGroup block
    match = re.search(r"SidebarLayout\.setHorizontalGroup\([\s\S]*?\);", content)
    if match:
        print(match.group(0))
    else:
        print("SidebarLayout.setHorizontalGroup not found.")
