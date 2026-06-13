import xml.etree.ElementTree as ET
import re

# Paths
path_ahorros_form = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\AhorrosView.form"
path_deudas_form = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\DeudasView.form"

# Parse AhorrosView.form
tree = ET.parse(path_ahorros_form)
root = tree.getroot()

# Update Sidebar items in DeudasView.form:
# In DeudasView, jPanelSupplimers (Deudas) is active, and jPanelEmployes (Ahorros) is inactive.
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
            
    elif name == 'jPanelSupplimers':
        # Make active
        # Background: (244, 248, 254)
        bg = container.find("Properties/Property[@name='background']/Color")
        if bg is not None:
            bg.set('red', 'f4')
            bg.set('green', 'f8')
            bg.set('blue', 'fe')
        else:
            # create properties if missing
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
            
    elif name == 'jLabelSupplimers':
        # Active Label
        # Foreground: (37, 99, 235)
        fg = component.find("Properties/Property[@name='foreground']/Color")
        if fg is not None:
            fg.set('red', '25')
            fg.set('green', '63')
            fg.set('blue', 'eb')
        # Icon: /images/icon/blue/deudas.png
        icon = component.find("Properties/Property[@name='icon']/Image")
        if icon is not None:
            icon.set('name', '/images/icon/blue/deudas.png')

# Update Header title
for component in root.iter('Component'):
    if component.get('name') == 'jLabel2' and component.find("Properties/Property[@name='text']") is not None:
        component.find("Properties/Property[@name='text']").set('value', 'Control de Deudas y Préstamos')

# Find the JTabbedPane Body
body_container = None
for container in root.iter('Container'):
    if container.get('name') == 'Body':
        body_container = container
        break

if body_container is not None:
    # Remove all tabs (which is just jPanelTabAhorros)
    sub_components = body_container.find('SubComponents')
    if sub_components is not None:
        sub_components.clear()
        
    # Create the new jPanelTabDeudas tab panel
    tab_panel = ET.SubElement(sub_components, "Container", name="jPanelTabDeudas", class_="javax.swing.JPanel")
    
    # Add TabbedPane constraints
    constraints = ET.SubElement(tab_panel, "Constraints")
    constraint = ET.SubElement(constraints, "Constraint", layoutClass="org.netbeans.modules.form.compat2.layouts.support.JTabbedPaneSupportLayout", value="org.netbeans.modules.form.compat2.layouts.support.JTabbedPaneSupportLayout$JTabbedPaneConstraintsDescription")
    jt_constraints = ET.SubElement(constraint, "JTabbedPaneConstraints")
    gen_constraints = ET.SubElement(jt_constraints, "GeneralConstraints")
    props = ET.SubElement(gen_constraints, "Properties")
    ET.SubElement(props, "Property", name="tabTitle", type="java.lang.String", value="Deudas")
    
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

    # Helper to add JDateChooser
    def add_datechooser(parent_sub, name, x, y, width, height):
        dc = ET.SubElement(parent_sub, "Component", name=name, class_="com.toedter.calendar.JDateChooser")
        props = ET.SubElement(dc, "Properties")
        prop_bg = ET.SubElement(props, "Property", name="background", type="java.awt.Color", editor="org.netbeans.beaninfo.editors.ColorEditor")
        ET.SubElement(prop_bg, "Color", blue="ff", green="ff", red="ff", type="rgb")
        ET.SubElement(props, "Property", name="dateFormatString", type="java.lang.String", value="dd/MM/yyyy")
        
        # Constraints
        consts = ET.SubElement(dc, "Constraints")
        const = ET.SubElement(consts, "Constraint", layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout", value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription")
        ET.SubElement(const, "AbsoluteConstraints", x=str(x), y=str(y), width=str(width), height=str(height))
        return dc

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

    # --- 1. Deuda Total Card ---
    _, sub = add_card("pnlCardDeudaTotal", 10, 15, 310, 90)
    add_label(sub, "lblCardDeudaTotalIcon", "", 15, 20, 40, 40, icon_res="/images/icon/blue/deudas.png")
    add_label(sub, "lblCardDeudaTotalTitle", "DEUDA TOTAL", 70, 20, font_size=11, font_style=1, fg_rgb=(100,116,139))
    add_label(sub, "lblCardDeudaTotalValue", "$45,280.00", 70, 40, font_size=24, font_style=1, fg_rgb=(11,28,48))

    # --- 2. Próximo Vencimiento Card ---
    _, sub = add_card("pnlCardProximoVencimiento", 340, 15, 310, 90)
    add_label(sub, "lblCardProximoVencimientoIcon", "", 15, 20, 40, 40, icon_res="/images/icon/brown/calendario.png")
    add_label(sub, "lblCardProximoVencimientoTitle", "PR\u00d3XIMO VENCIMIENTO", 70, 20, font_size=11, font_style=1, fg_rgb=(100,116,139))
    add_label(sub, "lblCardProximoVencimientoValue", "Oct 15, 2023", 70, 40, font_size=24, font_style=1, fg_rgb=(154,52,18)) # Brownish-red

    # --- 3. Total Pagado Card ---
    _, sub = add_card("pnlCardTotalPagado", 670, 15, 320, 90)
    add_label(sub, "lblCardTotalPagadoIcon", "", 15, 20, 40, 40, icon_res="/images/icon/green/controlar.png")
    add_label(sub, "lblCardTotalPagadoTitle", "TOTAL PAGADO ESTE MES", 70, 20, font_size=11, font_style=1, fg_rgb=(100,116,139))
    add_label(sub, "lblCardTotalPagadoValue", "$3,450.00", 70, 40, font_size=24, font_style=1, fg_rgb=(6,95,70)) # Green

    # --- 4. Nueva Deuda Box ---
    _, sub = add_card("pnlNuevaDeuda", 10, 120, 310, 260)
    add_label(sub, "lblNuevaDeudaTitle", "Nueva Deuda", 15, 12, font_size=14, font_style=1, fg_rgb=(11,28,48))
    add_label(sub, "lblAcreedor", "Acreedor / Entidad", 15, 40, font_size=11, fg_rgb=(71,85,105))
    add_textfield(sub, "txtAcreedor", "", 15, 58, 280, 26)
    
    add_label(sub, "lblMontoTotal", "Monto Total", 15, 92, font_size=11, fg_rgb=(71,85,105))
    add_textfield(sub, "txtMontoTotal", "0.00", 15, 110, 135, 26)
    add_label(sub, "lblTasaInteres", "Tasa de Inter\u00e9s (%)", 160, 92, font_size=11, fg_rgb=(71,85,105))
    add_textfield(sub, "txtTasaInteres", "0.0", 160, 110, 135, 26)
    
    add_label(sub, "lblCuotasTotales", "Cuotas Totales", 15, 144, font_size=11, fg_rgb=(71,85,105))
    add_textfield(sub, "txtCuotasTotales", "12", 15, 162, 135, 26)
    add_label(sub, "lblFechaInicio", "Fecha Inicio", 160, 144, font_size=11, fg_rgb=(71,85,105))
    add_datechooser(sub, "dateFechaInicio", 160, 162, 135, 26)
    
    add_button(sub, "btnRegistrarDeuda", "Registrar Deuda", 15, 208, 280, 34)

    # --- 5. Registrar Abono Box ---
    _, sub = add_card("pnlRegistrarAbono", 10, 395, 310, 230)
    add_label(sub, "lblRegistrarAbonoTitle", "Registrar Abono", 15, 12, font_size=14, font_style=1, fg_rgb=(11,28,48))
    add_label(sub, "lblSeleccionarDeuda", "Seleccionar Deuda", 15, 36, font_size=11, fg_rgb=(71,85,105))
    add_combobox(sub, "cbSeleccionarDeuda", ["Visa Oro - Santander", "Hipotecario - BBVA", "Automotriz - Ford"], 15, 52, 280, 26)
    
    add_label(sub, "lblMontoAbono", "Monto del Abono", 15, 86, font_size=11, fg_rgb=(71,85,105))
    add_textfield(sub, "txtMontoAbono", "0.00", 15, 102, 280, 26)
    add_label(sub, "lblFechaPago", "Fecha del Pago", 15, 136, font_size=11, fg_rgb=(71,85,105))
    add_datechooser(sub, "dateFechaPago", 15, 152, 280, 26)
    
    add_button(sub, "btnConfirmarPago", "Confirmar Pago", 15, 190, 280, 28, bg_rgb=(255,255,255), fg_rgb=(37,99,235), is_outline=True)

    # --- 6. Deudas Activas Box ---
    _, sub = add_card("pnlDeudasActivas", 340, 120, 650, 265)
    add_label(sub, "lblDeudasActivasTitle", "Deudas Activas", 15, 12, font_size=14, font_style=1, fg_rgb=(11,28,48))
    add_label(sub, "lblDeudasActivasSubtitle", "3 Deudas en curso", 530, 14, font_size=11, fg_rgb=(100,116,139))
    
    # 6.1 Hipotecario Card
    p1, s1 = add_card("pnlDebt1", 15, 38, 620, 68)
    # Background and border are already styled by add_card. Let's make sure its border uses a light E2E8F0 color.
    add_label(s1, "lblDebt1Title", "Pr\u00e9stamo Hipotecario - Banco BBVA", 12, 8, font_size=12, font_style=1, fg_rgb=(11,28,48))
    add_label(s1, "lblDebt1Desc", "Monto Inicial: $120,000.00 | Tasa: 4.5%", 12, 24, font_size=10, fg_rgb=(100,116,139))
    # Green badge: "AL DÍA"
    add_label(s1, "lblDebt1Badge", "   AL D\u00cdA", 410, 8, 65, 18, font_size=9, font_style=1, fg_rgb=(6,95,70))
    # progress bar
    add_progressbar(s1, "pbDebt1", 18, 12, 48, 320, 6, bg_rgb=(16,185,129)) # green progress
    add_label(s1, "lblDebt1ProgressText", "Progreso de Pago (45 de 240 cuotas)", 12, 35, font_size=9, fg_rgb=(71,85,105))
    add_label(s1, "lblDebt1Percent", "18.7%", 340, 35, font_size=9, font_style=1, fg_rgb=(11,28,48))
    # Saldo Pendiente
    add_label(s1, "lblDebt1SaldoTitle", "Saldo Pendiente", 495, 20, font_size=9, fg_rgb=(100,116,139))
    add_label(s1, "lblDebt1SaldoValue", "$97,500.00", 495, 34, font_size=13, font_style=1, fg_rgb=(11,28,48))

    # 6.2 Tarjeta Oro Card (with left red margin. We configure the composite border in java or just keep it simple in XML)
    p2, s2 = add_card("pnlDebt2", 15, 112, 620, 68)
    # Set left border of pnlDebt2 to red matte in the properties:
    props_p2 = p2.find("Properties")
    border_p2 = props_p2.find("Property[@name='border']")
    if border_p2 is not None:
        props_p2.remove(border_p2)
    prop_border2 = ET.SubElement(props_p2, "Property", name="border", type="javax.swing.border.Border", editor="org.netbeans.modules.form.editors2.BorderEditor")
    border_info2 = ET.SubElement(prop_border2, "Border", info="org.netbeans.modules.form.compat2.border.MatteBorderInfo")
    ET.SubElement(border_info2, "MatteBorder", bottom="1", left="4", right="1", top="1")
    ET.SubElement(border_info2, "Color", PropertyName="color", blue="f1", green="ea", red="ea", type="rgb") # Default border
    # Wait, we can set the left matte border color to red in Java to avoid complex XML validation
    
    add_label(s2, "lblDebt2Title", "Tarjeta de Cr\u00e9dito Oro - Visa", 12, 8, font_size=12, font_style=1, fg_rgb=(11,28,48))
    add_label(s2, "lblDebt2Desc", "Monto Inicial: $4,500.00 | Pago M\u00ednimo: $150.00", 12, 24, font_size=10, fg_rgb=(100,116,139))
    # Red badge: "PRÓXIMO A VENCER"
    add_label(s2, "lblDebt2Badge", " PR\u00d3XIMO A VENCER", 370, 8, 105, 18, font_size=8, font_style=1, fg_rgb=(153,27,27))
    # Warning text
    add_label(s2, "lblDebt2WarningIcon", "", 12, 42, 16, 16, icon_res="/images/icon/red/chat-flecha-abajo.png") # Warning icon
    add_label(s2, "lblDebt2WarningText", "Vence en 2 d\u00edas (Oct 12)", 32, 42, font_size=10, font_style=1, fg_rgb=(153,27,27))
    # Pagar ahora
    add_label(s2, "lblDebt2PayNow", "Pagar ahora", 520, 42, font_size=11, font_style=1, fg_rgb=(37,99,235))
    
    # 6.3 Automotriz Card
    p3, s3 = add_card("pnlDebt3", 15, 186, 620, 68)
    add_label(s3, "lblDebt3Title", "Pr\u00e9stamo Automotriz - Ford Credit", 12, 8, font_size=12, font_style=1, fg_rgb=(11,28,48))
    add_label(s3, "lblDebt3Desc", "Monto Inicial: $25,000.00 | Tasa: 6.0%", 12, 24, font_size=10, fg_rgb=(100,116,139))
    # Blue badge: "PENDIENTE"
    add_label(s3, "lblDebt3Badge", "   PENDIENTE", 400, 8, 75, 18, font_size=9, font_style=1, fg_rgb=(30,58,138))
    # progress bar
    add_progressbar(s3, "pbDebt3", 62, 12, 48, 320, 6, bg_rgb=(37,99,235)) # blue progress
    add_label(s3, "lblDebt3ProgressText", "Progreso de Pago (30 de 48 cuotas)", 12, 35, font_size=9, fg_rgb=(71,85,105))
    add_label(s3, "lblDebt3Percent", "62.5%", 340, 35, font_size=9, font_style=1, fg_rgb=(11,28,48))
    # Saldo Pendiente
    add_label(s3, "lblDebt3SaldoTitle", "Saldo Pendiente", 495, 20, font_size=9, fg_rgb=(100,116,139))
    add_label(s3, "lblDebt3SaldoValue", "$9,375.00", 495, 34, font_size=13, font_style=1, fg_rgb=(11,28,48))

    # --- 7. Historial Reciente Box ---
    _, sub = add_card("pnlHistorialReciente", 340, 400, 650, 215)
    add_label(sub, "lblHistorialRecienteTitle", "Historial Reciente", 15, 12, font_size=14, font_style=1, fg_rgb=(11,28,48))
    add_label(sub, "lblExportarCSV", "Exportar CSV", 550, 14, font_size=11, font_style=1, fg_rgb=(37,99,235)) # Export link
    
    # Add Table
    add_table_scroll(sub, "jScrollPaneDeudas", "tblHistorial", ["FECHA", "DEUDA", "MONTO PAGADO", "SALDO RESTANTE"], 15, 38, 620, 160)

# Write to DeudasView.form
tree.write(path_deudas_form, encoding="UTF-8", xml_declaration=True)

# Post-processing to clean up XML namespace prefix (ElementTree adds ns0: prefixes sometimes)
with open(path_deudas_form, "r", encoding="utf-8") as f:
    xml_content = f.read()

# Replace any ns0: prefixes
xml_content = re.sub(r'ns\d+:', '', xml_content)
xml_content = re.sub(r':ns\d+', '', xml_content)

# Remove any comments if they were accidentally added (none should be there as we read from AhorrosView which has none)
with open(path_deudas_form, "w", encoding="utf-8") as f:
    f.write(xml_content)

print("DeudasView.form generated successfully!")
