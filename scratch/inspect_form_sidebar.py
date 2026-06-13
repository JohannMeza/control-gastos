import os

path = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\SystemView.form"

with open(path, "r", encoding="utf-8") as f:
    content = f.read()

# Let's find '<Container class="javax.swing.JPanel" name="Sidebar">' and print the next 200 lines
start_idx = content.find('<Container class="javax.swing.JPanel" name="Sidebar">')
if start_idx != -1:
    print(content[start_idx:start_idx+1500])
else:
    print("Sidebar container not found in form.")
