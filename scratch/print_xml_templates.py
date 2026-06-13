import xml.etree.ElementTree as ET

path = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\AhorrosView.form"
tree = ET.parse(path)
root = tree.getroot()

def print_element_xml(name):
    for elem in root.iter():
        if elem.get('name') == name:
            xml_str = ET.tostring(elem, encoding='utf-8').decode('utf-8')
            # Print first 20 lines to keep it clean
            lines = xml_str.split('\n')
            print(f"--- XML for {name} ({elem.tag}) ---")
            print('\n'.join(lines[:35]))
            if len(lines) > 35:
                print("... (truncated) ...")
            print()
            break

print_element_xml('pnlCardTotalAhorrado')
print_element_xml('lblCardTotalAhorradoTitle')
print_element_xml('txtMonto')
print_element_xml('cbMetaFondo')
print_element_xml('jScrollPane1') # scroll pane containing table
