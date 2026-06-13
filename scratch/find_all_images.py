import os

images_dir = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\images"
for root, dirs, files in os.walk(images_dir):
    for f in files:
        if f.endswith('.png'):
            rel_path = os.path.relpath(os.path.join(root, f), images_dir)
            print(rel_path)
