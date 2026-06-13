import xml.etree.ElementTree as ET

path = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\DeudasView.form"
tree = ET.parse(path)
root = tree.getroot()

declarations = []
for elem in root.iter():
    if elem.tag in ('Component', 'Container'):
        name = elem.get('name')
        class_name = elem.get('class') or elem.get('class_') or ""
        if class_name == "javax.swing.JPanel":
            # Check if it is a container
            pass
        if name and class_name:
            declarations.append((class_name, name))

# Remove duplicates
seen = set()
unique_decls = []
for c, n in declarations:
    if n not in seen:
        seen.add(n)
        unique_decls.append((c, n))

for c, n in sorted(unique_decls):
    print(f"    private {c} {n};")
