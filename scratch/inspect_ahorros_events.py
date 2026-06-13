import xml.etree.ElementTree as ET

path = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\AhorrosView.form"
tree = ET.parse(path)
root = tree.getroot()

for elem in root.iter('Events'):
    parent = None
    # find parent name
    for p in root.iter():
        if elem in list(p):
            parent = p
            break
    parent_name = parent.get('name') if parent is not None else "Unknown"
    print(f"Events for component {parent_name}:")
    for event in elem.findall('EventHandler'):
        print(f"  Event: {event.get('event')} | Handler: {event.get('handler')}")
