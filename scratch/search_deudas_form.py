import os

path = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\DeudasView.form"

with open(path, "r", encoding="utf-8") as f:
    lines = f.readlines()

for idx, line in enumerate(lines):
    if "jPanelEmployes" in line:
        print(f"{idx+1}: {line.strip()}")
