import xml.etree.ElementTree as ET

path = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\AhorrosView.form"
try:
    ET.parse(path)
    print("AhorrosView.form parsed successfully!")
except Exception as e:
    print("Parse error:", e)
