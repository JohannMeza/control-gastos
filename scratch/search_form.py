import os

path = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\UsuariosView.form"

with open(path, "r", encoding="utf-8") as f:
    lines = f.readlines()

found = False
for idx, line in enumerate(lines):
    if "btnPagActive" in line or "btnPag" in line:
        print(f"{idx+1}: {line.strip()}")
        found = True

if not found:
    print("Not found in file.")
