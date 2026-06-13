import xml.etree.ElementTree as ET
import re

# Paths
path_ahorros_form = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\AhorrosView.form"
path_usuarios_form = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\UsuariosView.form"

# Parse AhorrosView.form
tree = ET.parse(path_ahorros_form)
root = tree.getroot()

# Update Sidebar items in UsuariosView.form:
# In UsuariosView, jPanelCategories (Usuarios) is active, and jPanelEmployes (Ahorros) is inactive.
for container in root.iter('Container'):
    name = container.get('name')
    if name == 'jPanelEmployes':
        # Make inactive
        # Background: (248, 250, 252)
        bg = container.find("Properties/Property[@name='background']/Color")
        if bg is not None:
            bg.set('red', 'f8')
            bg.set('green', 'fa')
            bg.set('blue', 'fc')
        # Remove border (MatteBorder)
        border = container.find("Properties/Property[@name='border']")
        if border is not None:
            container.find("Properties").remove(border)
            
    elif name == 'jPanelCategories':
        # Make active
        # Background: (244, 248, 254)
        bg = container.find("Properties/Property[@name='background']/Color")
        if bg is not None:
            bg.set('red', 'f4')
            bg.set('green', 'f8')
            bg.set('blue', 'fe')
        else:
            properties = container.find("Properties")
            if properties is None:
                properties = ET.SubElement(container, "Properties")
            prop_bg = ET.SubElement(properties, "Property", name="background", type="java.awt.Color", editor="org.netbeans.beaninfo.editors.ColorEditor")
            ET.SubElement(prop_bg, "Color", blue="fe", green="f8", red="f4", type="rgb")
            
        # Add border (MatteBorder: 0, 6, 0, 0, #2563EB / 37, 99, 235)
        properties = container.find("Properties")
        border = properties.find("Property[@name='border']")
        if border is not None:
            properties.remove(border)
        prop_border = ET.SubElement(properties, "Property", name="border", type="javax.swing.border.Border", editor="org.netbeans.modules.form.editors2.BorderEditor")
        border_info = ET.SubElement(prop_border, "Border", info="org.netbeans.modules.form.compat2.border.MatteBorderInfo")
        ET.SubElement(border_info, "MatteBorder", bottom="0", left="6", right="0", top="0")
        color = ET.SubElement(border_info, "Color", PropertyName="color", blue="eb", green="63", red="25", type="rgb")

# Update Sidebar Labels
for component in root.iter('Component'):
    name = component.get('name')
    if name == 'jLabelEmployes':
        # Inactive Label
        # Foreground: (71, 85, 105)
        fg = component.find("Properties/Property[@name='foreground']/Color")
        if fg is not None:
            fg.set('red', '47')
            fg.set('green', '55')
            fg.set('blue', '69')
        # Icon: /images/icon/grey/ahorros.png
        icon = component.find("Properties/Property[@name='icon']/Image")
        if icon is not None:
            icon.set('name', '/images/icon/grey/ahorros.png')
            
    elif name == 'jLabelCategories':
        # Active Label
        # Foreground: (37, 99, 235)
        fg = component.find("Properties/Property[@name='foreground']/Color")
        if fg is not None:
            fg.set('red', '25')
            fg.set('green', '63')
            fg.set('blue', 'eb')
        # Icon: /images/icon/blue/usuario.png
        icon = component.find("Properties/Property[@name='icon']/Image")
        if icon is not None:
            icon.set('name', '/images/icon/blue/usuario.png')

# Update Header title
for component in root.iter('Component'):
    if component.get('name') == 'jLabel2' and component.find("Properties/Property[@name='text']") is not None:
        component.find("Properties/Property[@name='text']").set('value', 'Finanzas Personales / Gesti\u00f3n de Usuarios')

# Find the JTabbedPane Body
body_container = None
for container in root.iter('Container'):
    if container.get('name') == 'Body':
        body_container = container
        break

if body_container is not None:
    # Remove all tabs
    sub_components = body_container.find('SubComponents')
    if sub_components is not None:
        sub_components.clear()
        
    # Create the new jPanelTabUsuarios tab panel
    tab_panel = ET.SubElement(sub_components, "Container", name="jPanelTabUsuarios", class_="javax.swing.JPanel")
    
    # Add TabbedPane constraints
    constraints = ET.SubElement(tab_panel, "Constraints")
    constraint = ET.SubElement(constraints, "Constraint", layoutClass="org.netbeans.modules.form.compat2.layouts.support.JTabbedPaneSupportLayout", value="org.netbeans.modules.form.compat2.layouts.support.JTabbedPaneSupportLayout$JTabbedPaneConstraintsDescription")
    jt_constraints = ET.SubElement(constraint, "JTabbedPaneConstraints")
    gen_constraints = ET.SubElement(jt_constraints, "GeneralConstraints")
    props = ET.SubElement(gen_constraints, "Properties")
    ET.SubElement(props, "Property", name="tabTitle", type="java.lang.String", value="Usuarios")
    
    # Layout of the tab panel (AbsoluteLayout)
    layout = ET.SubElement(tab_panel, "Layout", class_="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout")
    ET.SubElement(layout, "Property", name="useNullLayout", type="boolean", value="false")
    
    # Tab sub-components container
    tab_sub = ET.SubElement(tab_panel, "SubComponents")
    
    # Helper to add standard JPanel card
    def add_card(name, x, y, width, height):
        card = ET.SubElement(tab_sub, "Container", name=name, class_="javax.swing.JPanel")
        props = ET.SubElement(card, "Properties")
        
        # bg white
        prop_bg = ET.SubElement(props, "Property", name="background", type="java.awt.Color", editor="org.netbeans.beaninfo.editors.ColorEditor")
        ET.SubElement(prop_bg, "Color", blue="ff", green="ff", red="ff", type="rgb")
        
        # Line border rounded
        prop_border = ET.SubElement(props, "Property", name="border", type="javax.swing.border.Border", editor="org.netbeans.modules.form.editors2.BorderEditor")
        border_info = ET.SubElement(prop_border, "Border", info="org.netbeans.modules.form.compat2.border.LineBorderInfo")
        line_border = ET.SubElement(border_info, "LineBorder", roundedCorners="true")
        ET.SubElement(line_border, "Color", PropertyName="color", blue="f1", green="ea", red="ea", type="rgb") # EAEAF1 border
        
        # Constraints
        consts = ET.SubElement(card, "Constraints")
        const = ET.SubElement(consts, "Constraint", layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout", value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription")
        ET.SubElement(const, "AbsoluteConstraints", x=str(x), y=str(y), width=str(width), height=str(height))
        
        # Card Layout
        card_layout = ET.SubElement(card, "Layout", class_="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout")
        ET.SubElement(card_layout, "Property", name="useNullLayout", type="boolean", value="false")
        
        card_sub = ET.SubElement(card, "SubComponents")
        return card, card_sub

    # Helper to add JLabel
    def add_label(parent_sub, name, text, x, y, width="-1", height="-1", font_size=12, font_style=0, fg_rgb=(71,85,105), icon_res=None):
        label = ET.SubElement(parent_sub, "Component", name=name, class_="javax.swing.JLabel")
        props = ET.SubElement(label, "Properties")
        
        # Font
        prop_font = ET.SubElement(props, "Property", name="font", type="java.awt.Font", editor="org.netbeans.beaninfo.editors.FontEditor")
        ET.SubElement(prop_font, "Font", name="Dialog", size=str(font_size), style=str(font_style))
        
        # Foreground
        prop_fg = ET.SubElement(props, "Property", name="foreground", type="java.awt.Color", editor="org.netbeans.beaninfo.editors.ColorEditor")
        ET.SubElement(prop_fg, "Color", red=hex(fg_rgb[0])[2:], green=hex(fg_rgb[1])[2:], blue=hex(fg_rgb[2])[2:], type="rgb")
        
        # Text
        ET.SubElement(props, "Property", name="text", type="java.lang.String", value=text)
        
        # Icon
        if icon_res:
            prop_icon = ET.SubElement(props, "Property", name="icon", type="javax.swing.Icon", editor="org.netbeans.modules.form.editors2.IconEditor")
            ET.SubElement(prop_icon, "Image", iconType="3", name=icon_res)
            
        # Constraints
        consts = ET.SubElement(label, "Constraints")
        const = ET.SubElement(consts, "Constraint", layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout", value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription")
        ET.SubElement(const, "AbsoluteConstraints", x=str(x), y=str(y), width=str(width), height=str(height))
        return label

    # Helper to add JTextField
    def add_textfield(parent_sub, name, text, x, y, width, height):
        tf = ET.SubElement(parent_sub, "Component", name=name, class_="javax.swing.JTextField")
        props = ET.SubElement(tf, "Properties")
        ET.SubElement(props, "Property", name="text", type="java.lang.String", value=text)
        
        # Border
        prop_border = ET.SubElement(props, "Property", name="border", type="javax.swing.border.Border", editor="org.netbeans.modules.form.editors2.BorderEditor")
        border_info = ET.SubElement(prop_border, "Border", info="org.netbeans.modules.form.compat2.border.LineBorderInfo")
        line_border = ET.SubElement(border_info, "LineBorder", roundedCorners="true")
        ET.SubElement(line_border, "Color", PropertyName="color", blue="e2", green="e2", red="e2", type="rgb")
        
        # Constraints
        consts = ET.SubElement(tf, "Constraints")
        const = ET.SubElement(consts, "Constraint", layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout", value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription")
        ET.SubElement(const, "AbsoluteConstraints", x=str(x), y=str(y), width=str(width), height=str(height))
        return tf

    # Helper to add JComboBox
    def add_combobox(parent_sub, name, items, x, y, width, height):
        cb = ET.SubElement(parent_sub, "Component", name=name, class_="javax.swing.JComboBox")
        props = ET.SubElement(cb, "Properties")
        prop_model = ET.SubElement(props, "Property", name="model", type="javax.swing.ComboBoxModel", editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor")
        str_array = ET.SubElement(prop_model, "StringArray", count=str(len(items)))
        for idx, item in enumerate(items):
            ET.SubElement(str_array, "StringItem", index=str(idx), value=item)
            
        # Constraints
        consts = ET.SubElement(cb, "Constraints")
        const = ET.SubElement(consts, "Constraint", layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout", value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription")
        ET.SubElement(const, "AbsoluteConstraints", x=str(x), y=str(y), width=str(width), height=str(height))
        return cb

    # Helper to add JButton
    def add_button(parent_sub, name, text, x, y, width, height, bg_rgb=(37,99,235), fg_rgb=(255,255,255), is_outline=False):
        btn = ET.SubElement(parent_sub, "Component", name=name, class_="javax.swing.JButton")
        props = ET.SubElement(btn, "Properties")
        ET.SubElement(props, "Property", name="text", type="java.lang.String", value=text)
        
        # Font
        prop_font = ET.SubElement(props, "Property", name="font", type="java.awt.Font", editor="org.netbeans.beaninfo.editors.FontEditor")
        ET.SubElement(prop_font, "Font", name="Dialog", size="12", style="1")
        
        # Cursor
        prop_cursor = ET.SubElement(props, "Property", name="cursor", type="java.awt.Cursor", editor="org.netbeans.modules.form.editors2.CursorEditor")
        ET.SubElement(prop_cursor, "Color", id="Hand Cursor")
        
        # BG
        prop_bg = ET.SubElement(props, "Property", name="background", type="java.awt.Color", editor="org.netbeans.beaninfo.editors.ColorEditor")
        ET.SubElement(prop_bg, "Color", red=hex(bg_rgb[0])[2:], green=hex(bg_rgb[1])[2:], blue=hex(bg_rgb[2])[2:], type="rgb")
        
        # FG
        prop_fg = ET.SubElement(props, "Property", name="foreground", type="java.awt.Color", editor="org.netbeans.beaninfo.editors.ColorEditor")
        ET.SubElement(prop_fg, "Color", red=hex(fg_rgb[0])[2:], green=hex(fg_rgb[1])[2:], blue=hex(fg_rgb[2])[2:], type="rgb")
        
        # Border
        if is_outline:
            prop_border = ET.SubElement(props, "Property", name="border", type="javax.swing.border.Border", editor="org.netbeans.modules.form.editors2.BorderEditor")
            border_info = ET.SubElement(prop_border, "Border", info="org.netbeans.modules.form.compat2.border.LineBorderInfo")
            line_border = ET.SubElement(border_info, "LineBorder", roundedCorners="true")
            ET.SubElement(line_border, "Color", PropertyName="color", red=hex(bg_rgb[0])[2:], green=hex(bg_rgb[1])[2:], blue=hex(bg_rgb[2])[2:], type="rgb")
            
        # Action Event
        events = ET.SubElement(btn, "Events")
        ET.SubElement(events, "EventHandler", event="actionPerformed", handler=f"{name}ActionPerformed")
        
        # Constraints
        consts = ET.SubElement(btn, "Constraints")
        const = ET.SubElement(consts, "Constraint", layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout", value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription")
        ET.SubElement(const, "AbsoluteConstraints", x=str(x), y=str(y), width=str(width), height=str(height))
        return btn

    # Helper to add JProgressBar
    def add_progressbar(parent_sub, name, value, x, y, width, height, bg_rgb=(37,99,235)):
        pb = ET.SubElement(parent_sub, "Component", name=name, class_="javax.swing.JProgressBar")
        props = ET.SubElement(pb, "Properties")
        ET.SubElement(props, "Property", name="value", type="int", value=str(value))
        
        # FG
        prop_fg = ET.SubElement(props, "Property", name="foreground", type="java.awt.Color", editor="org.netbeans.beaninfo.editors.ColorEditor")
        ET.SubElement(prop_fg, "Color", red=hex(bg_rgb[0])[2:], green=hex(bg_rgb[1])[2:], blue=hex(bg_rgb[2])[2:], type="rgb")
        
        # Border painted false
        ET.SubElement(props, "Property", name="borderPainted", type="boolean", value="false")
        
        # Constraints
        consts = ET.SubElement(pb, "Constraints")
        const = ET.SubElement(consts, "Constraint", layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout", value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription")
        ET.SubElement(const, "AbsoluteConstraints", x=str(x), y=str(y), width=str(width), height=str(height))
        return pb

    # Helper to add Table
    def add_table_scroll(parent_sub, scroll_name, table_name, cols, x, y, width, height):
        scroll = ET.SubElement(parent_sub, "Container", name=scroll_name, class_="javax.swing.JScrollPane")
        
        # Constraints
        consts = ET.SubElement(scroll, "Constraints")
        const = ET.SubElement(consts, "Constraint", layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout", value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription")
        ET.SubElement(const, "AbsoluteConstraints", x=str(x), y=str(y), width=str(width), height=str(height))
        
        # Layout
        ET.SubElement(scroll, "Layout", class_="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout")
        
        scroll_sub = ET.SubElement(scroll, "SubComponents")
        
        table = ET.SubElement(scroll_sub, "Component", name=table_name, class_="javax.swing.JTable")
        props = ET.SubElement(table, "Properties")
        
        prop_model = ET.SubElement(props, "Property", name="model", type="javax.swing.table.TableModel", editor="org.netbeans.modules.form.editors2.TableModelEditor")
        table_xml = ET.SubElement(prop_model, "Table", columnCount=str(len(cols)), rowCount="0")
        for col in cols:
            ET.SubElement(table_xml, "Column", editable="false", title=col, type="java.lang.Object")
            
        # Selection colors
        prop_sel_bg = ET.SubElement(props, "Property", name="selectionBackground", type="java.awt.Color", editor="org.netbeans.beaninfo.editors.ColorEditor")
        ET.SubElement(prop_sel_bg, "Color", blue="ff", green="f1", red="ea", type="rgb")
        
        prop_sel_fg = ET.SubElement(props, "Property", name="selectionForeground", type="java.awt.Color", editor="org.netbeans.beaninfo.editors.ColorEditor")
        ET.SubElement(prop_sel_fg, "Color", blue="30", green="1c", red="b", type="rgb")
        
        return scroll

    # --- Title & Description & Export ---
    add_label(tab_sub, "lblTabTitle", "Acceso Colaborativo", 20, 20, font_size=18, font_style=1, fg_rgb=(11,28,48))
    add_label(tab_sub, "lblTabDesc", "Gestiona qui\u00e9n puede ver y editar este espacio de trabajo financiero.", 20, 45, font_size=12, fg_rgb=(100,116,139))
    add_button(tab_sub, "btnExportar", "Exportar Lista", 850, 20, 140, 28, bg_rgb=(255,255,255), fg_rgb=(71,85,105), is_outline=True)

    # --- Left Column: Invite User ---
    _, sub_invite = add_card("pnlInvitarUsuario", 20, 80, 310, 360)
    add_label(sub_invite, "lblInvitarUsuarioTitle", "Invitar Nuevo Usuario", 15, 15, font_size=14, font_style=1, fg_rgb=(11,28,48))
    add_label(sub_invite, "lblCorreo", "Correo Electr\u00f3nico", 15, 45, font_size=11, fg_rgb=(71,85,105))
    add_textfield(sub_invite, "txtCorreo", "colega@ejemplo.com", 15, 63, 280, 28)
    
    add_label(sub_invite, "lblRol", "Rol de Acceso", 15, 105, font_size=11, fg_rgb=(71,85,105))
    add_combobox(sub_invite, "cbRol", ["Lector (Solo Lectura)", "Editor (Modificar)", "Administrador (Acceso Total)"], 15, 123, 280, 28)
    
    add_button(sub_invite, "btnEnviarInvitacion", "Enviar Invitaci\u00f3n", 15, 175, 280, 34)
    
    add_label(sub_invite, "lblAsientosTitle", "Asientos Usados", 15, 235, font_size=11, fg_rgb=(71,85,105))
    add_label(sub_invite, "lblAsientosValue", "4 / 10", 250, 235, font_size=11, font_style=1, fg_rgb=(11,28,48))
    add_progressbar(sub_invite, "pbAsientos", 40, 15, 255, 280, 8)
    
    # Description below seats progress
    add_label(sub_invite, "lblLicenciaDesc", "<html><body style='width: 250px;'>La licencia est\u00e1ndar incluye hasta 10 asientos colaborativos.</body></html>", 15, 275, font_size=10, fg_rgb=(100,116,139))

    # --- Right Column: Authorized Users ---
    _, sub_auth = add_card("pnlUsuariosAutorizados", 350, 80, 640, 360)
    add_label(sub_auth, "lblUsuariosAutorizadosTitle", "Usuarios Autorizados", 15, 15, font_size=14, font_style=1, fg_rgb=(11,28,48))
    add_textfield(sub_auth, "txtBuscar", "Buscar usuarios...", 430, 12, 195, 26)
    
    # JTable
    add_table_scroll(sub_auth, "jScrollPaneUsuarios", "tblUsuarios", ["USUARIO", "ROL", "ESTADO", "ACCIONES"], 15, 55, 610, 255)
    
    add_label(sub_auth, "lblConexionesCount", "Mostrando 4 de 4 conexiones activas", 15, 320, font_size=11, fg_rgb=(100,116,139))
    
    # Pagination
    add_button(sub_auth, "btnPagPrev", "<", 535, 318, 22, 22, bg_rgb=(255,255,255), fg_rgb=(100,116,139), is_outline=True)
    add_button(sub_auth, "btnPagActive", "1", 562, 318, 22, 22)
    add_button(sub_auth, "btnPagNext", ">", 589, 318, 22, 22, bg_rgb=(255,255,255), fg_rgb=(100,116,139), is_outline=True)

    # --- Bottom Row: Summary Cards ---
    # Card 1: Last Sync
    _, sub_sync = add_card("pnlCardSincronizacion", 20, 460, 310, 90)
    add_label(sub_sync, "lblCardSincronizacionIcon", "", 15, 20, 40, 40, icon_res="/images/icon/blue/historial.png")
    add_label(sub_sync, "lblCardSincronizacionTitle", "\u00daLTIMA SINCRONIZACI\u00d3N", 70, 20, font_size=11, font_style=1, fg_rgb=(100,116,139))
    add_label(sub_sync, "lblCardSincronizacionValue", "Hace 2 minutos por Jane Doe", 70, 40, font_size=13, font_style=1, fg_rgb=(11,28,48))

    # Card 2: MFA Enabled
    _, sub_mfa = add_card("pnlCardMfa", 350, 460, 310, 90)
    add_label(sub_mfa, "lblCardMfaIcon", "", 15, 20, 40, 40, icon_res="/images/icon/green/controlar.png")
    add_label(sub_mfa, "lblCardMfaTitle", "MFA ACTIVADO", 70, 20, font_size=11, font_style=1, fg_rgb=(100,116,139))
    add_label(sub_mfa, "lblCardMfaValue", "100% del equipo protegido", 70, 40, font_size=13, font_style=1, fg_rgb=(11,28,48))

    # Card 3: Audit Logs
    _, sub_audit = add_card("pnlCardAuditoria", 680, 460, 310, 90)
    add_label(sub_audit, "lblCardAuditoriaIcon", "", 15, 20, 40, 40, icon_res="/images/icon/brown/calendario.png")
    add_label(sub_audit, "lblCardAuditoriaTitle", "LOGS DE AUDITOR\u00cdA", 70, 20, font_size=11, font_style=1, fg_rgb=(100,116,139))
    add_label(sub_audit, "lblCardAuditoriaValue", "Ver historial de seguridad completo", 70, 40, font_size=13, font_style=1, fg_rgb=(11,28,48))

# Write to UsuariosView.form
tree.write(path_usuarios_form, encoding="UTF-8", xml_declaration=True)

# Post-processing to clean up XML namespace prefix and replace class_ with class
with open(path_usuarios_form, "r", encoding="utf-8") as f:
    xml_content = f.read()

xml_content = re.sub(r'ns\d+:', '', xml_content)
xml_content = re.sub(r':ns\d+', '', xml_content)
xml_content = xml_content.replace('class_="', 'class="')

with open(path_usuarios_form, "w", encoding="utf-8") as f:
    f.write(xml_content)

print("UsuariosView.form generated successfully!")
