import os
import re
import xml.etree.ElementTree as ET

java_path = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\DeudasView.java"
form_path = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\DeudasView.form"

# 1. Update DeudasView.form (XML)
if os.path.exists(form_path):
    tree = ET.parse(form_path)
    root = tree.getroot()
    
    target = None
    for elem in root.iter('Container'):
        if elem.get('name') == 'jPanelEmployes':
            target = elem
            break
            
    if target is not None:
        properties = target.find('Properties')
        if properties is None:
            properties = ET.SubElement(target, 'Properties')
            
        cursor = properties.find("Property[@name='cursor']")
        if cursor is None:
            cursor_prop = ET.SubElement(properties, "Property", name="cursor", type="java.awt.Cursor", editor="org.netbeans.modules.form.editors2.CursorEditor")
            ET.SubElement(cursor_prop, "Color", id="Hand Cursor")
            
        events = target.find('Events')
        if events is None:
            events = ET.SubElement(target, 'Events')
            
        handler_elem = events.find("EventHandler[@handler='jPanelEmployesMouseClicked']")
        if handler_elem is None:
            ET.SubElement(events, "EventHandler", event="mouseClicked", handler="jPanelEmployesMouseClicked")
            
        tree.write(form_path, encoding="UTF-8", xml_declaration=True)
        
        # Strip namespaces
        with open(form_path, "r", encoding="utf-8") as f:
            xml_content = f.read()
        xml_content = re.sub(r'ns\d+:', '', xml_content)
        xml_content = re.sub(r':ns\d+', '', xml_content)
        with open(form_path, "w", encoding="utf-8") as f:
            f.write(xml_content)
            
        print("DeudasView.form updated with Ahorros click navigation.")
    else:
        print("Error: jPanelEmployes not found in DeudasView.form")
else:
    print("Error: DeudasView.form not found.")

# 2. Update DeudasView.java
if os.path.exists(java_path):
    with open(java_path, "r", encoding="utf-8") as f:
        content = f.read()
        
    registration_pattern = r"jPanelEmployes\.addMouseListener"
    if re.search(registration_pattern, content):
        print("Java registration already exists in DeudasView for jPanelEmployes")
    else:
        bg_pattern = r"jPanelEmployes\.setBackground\(new java\.awt\.Color\(248, 250, 252\)\);"
        bg_match = re.search(bg_pattern, content)
        
        insert_text = """
        jPanelEmployes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelEmployes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelEmployesMouseClicked(evt);
            }
        });"""
        
        if bg_match:
            content = content.replace(bg_match.group(0), bg_match.group(0) + insert_text)
            with open(java_path, "w", encoding="utf-8") as f:
                f.write(content)
            print("DeudasView.java updated with mouse listener registration for jPanelEmployes.")
        else:
            print("Error: setBackground call for jPanelEmployes not found in DeudasView.java")
else:
    print("Error: DeudasView.java not found.")
