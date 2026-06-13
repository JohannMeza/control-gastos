import os
import re
import xml.etree.ElementTree as ET

# File Paths and Configurations
views = {
    "Dashboard": {
        "java": r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\DashboardView.java",
        "form": r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\DashboardView.form",
        "comp_name": "pnlUsuarios",
        "handler": "pnlUsuariosMouseClicked"
    },
    "System": {
        "java": r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\SystemView.java",
        "form": r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\SystemView.form",
        "comp_name": "jPanelCategories",
        "handler": "jPanelCategoriesMouseClicked"
    },
    "Presupuesto": {
        "java": r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\PresupuestoView.java",
        "form": r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\PresupuestoView.form",
        "comp_name": "jPanelCategories",
        "handler": "jPanelCategoriesMouseClicked"
    },
    "Ahorros": {
        "java": r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\AhorrosView.java",
        "form": r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\AhorrosView.form",
        "comp_name": "jPanelCategories",
        "handler": "jPanelCategoriesMouseClicked"
    },
    "Deudas": {
        "java": r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\DeudasView.java",
        "form": r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\DeudasView.form",
        "comp_name": "jPanelCategories",
        "handler": "jPanelCategoriesMouseClicked"
    }
}

def wire_xml(name, path, comp_name, handler):
    if not os.path.exists(path):
        print(f"Error: {path} not found.")
        return
        
    tree = ET.parse(path)
    root = tree.getroot()
    
    # Find the target Container component
    target = None
    for elem in root.iter('Container'):
        if elem.get('name') == comp_name:
            target = elem
            break
            
    if target is None:
        print(f"Error: Component {comp_name} not found in {path}")
        return
        
    properties = target.find('Properties')
    if properties is None:
        properties = ET.SubElement(target, 'Properties')
        
    # Check/add cursor property
    cursor = properties.find("Property[@name='cursor']")
    if cursor is None:
        cursor_prop = ET.SubElement(properties, "Property", name="cursor", type="java.awt.Cursor", editor="org.netbeans.modules.form.editors2.CursorEditor")
        ET.SubElement(cursor_prop, "Color", id="Hand Cursor")
        
    # Check/add Event handler
    events = target.find('Events')
    if events is None:
        events = ET.SubElement(target, 'Events')
    
    handler_elem = events.find(f"EventHandler[@handler='{handler}']")
    if handler_elem is None:
        ET.SubElement(events, "EventHandler", event="mouseClicked", handler=handler)
        
    # Write back XML
    tree.write(path, encoding="UTF-8", xml_declaration=True)
    
    # Post-process to remove namespaces introduced by ElementTree writing
    with open(path, "r", encoding="utf-8") as f:
        xml_content = f.read()
    xml_content = re.sub(r'ns\d+:', '', xml_content)
    xml_content = re.sub(r':ns\d+', '', xml_content)
    with open(path, "w", encoding="utf-8") as f:
        f.write(xml_content)
        
    print(f"XML navigation wired successfully for {name} ({comp_name} -> {handler})")

def wire_java(name, path, comp_name, handler):
    if not os.path.exists(path):
        print(f"Error: {path} not found.")
        return
        
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()
        
    # Check if mouse clicked registration already exists
    registration_pattern = rf"{comp_name}\.addMouseListener"
    if re.search(registration_pattern, content):
        print(f"Java registration already exists in {name} for {comp_name}")
    else:
        # We need to find the initialization of comp_name and add registration
        # We target the setBackground call for the component, or the instantiation
        bg_pattern = rf"{comp_name}\.setBackground\(new java\.awt\.Color\([^)]+\)\);"
        bg_match = re.search(bg_pattern, content)
        
        insert_text = f"""
        {comp_name}.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        {comp_name}.addMouseListener(new java.awt.event.MouseAdapter() {{
            public void mouseClicked(java.awt.event.MouseEvent evt) {{
                {handler}(evt);
            }}
        }});"""
        
        if bg_match:
            # Insert right after setBackground
            content = content.replace(bg_match.group(0), bg_match.group(0) + insert_text)
            print(f"Inserted mouse listener registration after setBackground for {comp_name} in {name}")
        else:
            instantiation_pattern = rf"{comp_name} = new javax\.swing\.JPanel\(\);"
            instantiation_match = re.search(instantiation_pattern, content)
            if instantiation_match:
                content = content.replace(instantiation_match.group(0), instantiation_match.group(0) + insert_text)
                print(f"Inserted mouse listener registration after instantiation for {comp_name} in {name}")
            else:
                print(f"Error: Instantiation/setBackground of {comp_name} not found in Java code for {name}")
                return

    # Check if the handler method is already defined
    handler_pattern = rf"private void {handler}\(java\.awt\.event\.MouseEvent"
    if re.search(handler_pattern, content):
        print(f"Java handler {handler} already exists in {name}")
    else:
        # We need to append the handler method before the variables declaration block
        vars_decl_pattern = r"// Variables declaration - do not modify"
        vars_decl_match = re.search(vars_decl_pattern, content)
        
        if not vars_decl_match:
            print(f"Error: Variables declaration block not found in Java code for {name}")
            return
            
        handler_code = f"""    private void {handler}(java.awt.event.MouseEvent evt) {{
        UsuariosView viewUsuarios = new UsuariosView();
        viewUsuarios.setVisible(true);
        this.dispose();
    }}

    """
        content = content.replace(vars_decl_match.group(0), handler_code + vars_decl_match.group(0))
        print(f"Inserted method {handler} in {name}")

    with open(path, "w", encoding="utf-8") as f:
        f.write(content)
        
    print(f"Java navigation wired successfully for {name}")

# Run wiring on all views
for name, info in views.items():
    print(f"\n--- Wiring {name} ---")
    wire_xml(name, info["form"], info["comp_name"], info["handler"])
    wire_java(name, info["java"], info["comp_name"], info["handler"])

print("\nAll navigation wiring process finished!")
