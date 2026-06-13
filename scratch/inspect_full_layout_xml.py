import os
import xml.etree.ElementTree as ET

views_dir = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view"
files = ["SystemView.form", "PresupuestoView.form", "AhorrosView.form"]

for f_name in files:
    path = os.path.join(views_dir, f_name)
    tree = ET.parse(path)
    root = tree.getroot()
    
    target = None
    for elem in root.iter('Container'):
        if elem.get('name') == 'Sidebar':
            target = elem
            break
            
    if target is not None:
        layout = target.find('Layout')
        print(f"\n=== FULL Layout XML in {f_name} ===")
        if layout is not None:
            # We can use ET.tostring to print layout XML
            xml_str = ET.tostring(layout, encoding='utf-8').decode('utf-8')
            print(xml_str)
        else:
            print("Layout element not found.")
    else:
        print(f"Sidebar container not found in {f_name}")
