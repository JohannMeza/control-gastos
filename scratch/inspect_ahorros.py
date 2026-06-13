import xml.etree.ElementTree as ET

path = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\AhorrosView.form"
tree = ET.parse(path)
root = tree.getroot()

for elem in root.iter('Component'):
    name = elem.get('name')
    if name in ['jLabelProducts', 'jLabelPurchases', 'jLabelCustomers', 'jLabelEmployes', 'jLabelSupplimers', 'jLabelCategories']:
        print(f"Component: {name}")
        for prop in elem.findall('Properties/Property'):
            print(f"  Property: {prop.get('name')} -> {prop.get('type')}")
            for child in prop:
                print(f"    Child tag: {child.tag} attributes: {child.attrib}")
