import xml.etree.ElementTree as ET

path = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\AhorrosView.form"
tree = ET.parse(path)
root = tree.getroot()

body_elem = None
for elem in root.iter('Container'):
    if elem.get('name') == 'Body':
        body_elem = elem
        break

if body_elem is not None:
    print("Body Container type:", body_elem.get('class'))
    # List children
    for child in body_elem.findall('SubComponents/Container'):
        print(f"Tab Container: {child.get('name')} | Class: {child.get('class')}")
        # Show sub-containers
        for sub in child.findall('SubComponents/Container'):
            print(f"  Sub Container: {sub.get('name')} | Class: {sub.get('class')}")
            for sub_c in sub.findall('SubComponents/Component'):
                print(f"    Component: {sub_c.get('name')} | Class: {sub_c.get('class')}")
else:
    print("Body not found")
