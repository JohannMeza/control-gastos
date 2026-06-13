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
        print(f"\n=== Layout elements in {f_name} ===")
        if layout is not None:
            # Print layout children
            for child in layout:
                print(f"Tag: {child.tag}, Attrs: {child.attrib}")
                for sub in child:
                    print(f"  SubTag: {sub.tag}, Attrs: {sub.attrib}")
                    # Print text if short
                    text = sub.text.strip() if sub.text else ""
                    if text:
                        print(f"    Text: {text}")
        else:
            print("Layout element not found.")
    else:
        print(f"Sidebar container not found in {f_name}")
