import os

views_dir = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view"
files = ["SystemView.form", "PresupuestoView.form", "AhorrosView.form"]

for f_name in files:
    path = os.path.join(views_dir, f_name)
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()
    
    print(f"\n=== {f_name} ===")
    start_idx = content.find('<Container class="javax.swing.JPanel" name="Sidebar">')
    if start_idx != -1:
        sidebar_xml = content[start_idx:start_idx+1600]
        # Find the <DimensionLayout dim="1"> block
        dim1_idx = sidebar_xml.find('<DimensionLayout dim="1">')
        if dim1_idx != -1:
            end_layout_idx = sidebar_xml.find('</DimensionLayout>', dim1_idx)
            print(sidebar_xml[dim1_idx:end_layout_idx+18])
        else:
            print("dim1 layout not found.")
    else:
        print("Sidebar container not found.")
