import re

path_deudas = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\DeudasView.form"
path_ahorros = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\AhorrosView.form"

# 1. Fix DeudasView.form
with open(path_deudas, "r", encoding="utf-8") as f:
    deudas_content = f.read()

# Replace class_ with class
deudas_content = deudas_content.replace('class_="', 'class="')

with open(path_deudas, "w", encoding="utf-8") as f:
    f.write(deudas_content)
print("DeudasView.form class_ replaced with class.")

# 2. Fix AhorrosView.form JTable structure
with open(path_ahorros, "r", encoding="utf-8") as f:
    ahorros_content = f.read()

# Find the model property for tblAhorros
# We will replace the non-standard JTable Table declaration with the clean one
old_table_pattern = r'<Property name="model" type="javax\.swing\.table\.TableModel" editor="org\.netbeans\.modules\.form\.editors2\.TableModelEditor">[\s\S]*?<Table[\s\S]*?/Table>[\s\S]*?</Property>'
# Wait, if there is a self-closing Table like <Table values="..." /> we match that:
old_table_pattern_self = r'<Property name="model" type="javax\.swing\.table\.TableModel" editor="org\.netbeans\.modules\.form\.editors2\.TableModelEditor">[\s\S]*?<Table[\s\S]*?/>[\s\S]*?</Property>'

clean_table_xml = """<Property name="model" type="javax.swing.table.TableModel" editor="org.netbeans.modules.form.editors2.TableModelEditor">
                          <Table columnCount="5" rowCount="0">
                            <Column editable="false" title="FECHA" type="java.lang.Object"/>
                            <Column editable="false" title="META / FONDO" type="java.lang.Object"/>
                            <Column editable="false" title="MONTO" type="java.lang.Object"/>
                            <Column editable="false" title="USUARIO" type="java.lang.Object"/>
                            <Column editable="false" title="ACCI\u00d3N" type="java.lang.Object"/>
                          </Table>
                        </Property>"""

if re.search(old_table_pattern, ahorros_content):
    ahorros_content = re.sub(old_table_pattern, clean_table_xml, ahorros_content)
    print("Replaced standard old table pattern in AhorrosView.form.")
elif re.search(old_table_pattern_self, ahorros_content):
    ahorros_content = re.sub(old_table_pattern_self, clean_table_xml, ahorros_content)
    print("Replaced self-closing old table pattern in AhorrosView.form.")
else:
    print("Could not match old table pattern, let's look for standard Table representation.")

# Save AhorrosView.form
with open(path_ahorros, "w", encoding="utf-8") as f:
    f.write(ahorros_content)

print("AhorrosView.form table properties fixed.")
