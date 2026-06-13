import os
import re

views_dir = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view"
files = ["DashboardView.java", "SystemView.java", "PresupuestoView.java", "AhorrosView.java", "DeudasView.java"]

for f_name in files:
    path = os.path.join(views_dir, f_name)
    if not os.path.exists(path):
        print(f"{f_name} does not exist!")
        continue
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()
    
    print(f"\n=== INSPECTING {f_name} ===")
    
    # Let's search for lines matching "Usuarios", "Categorias", "pnl", "jPanel", "menu"
    # Find all JPanels declared
    panels = re.findall(r"private javax\.swing\.JPanel (\w+);", content)
    labels = re.findall(r"private javax\.swing\.JLabel (\w+);", content)
    
    print("Panels:", panels)
    
    # Find occurrences of text in labels (e.g. .setText("...") or XML labels)
    # Let's look for any label setText containing "Usuarios", "Ahorros", "Deudas", "Presupuesto", "Gasto"
    setTexts = re.findall(r"(\w+)\.setText\(\"([^\"]+)\"\);", content)
    for lbl, text in setTexts:
        if any(x in text.lower() for x in ["usuario", "categor", "ahorro", "deuda", "presupuesto", "gasto"]):
            print(f"  {lbl}.setText(\"{text}\")")
            
    # Also find if there is a mouseClicked handler for any panels
    mouse_handlers = re.findall(r"private void (\w+)MouseClicked\(java\.awt\.event\.MouseEvent", content)
    print("Mouse Handlers:", mouse_handlers)
