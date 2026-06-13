import os
import re

views_dir = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view"

# 1. Update SystemView
sys_form_path = os.path.join(views_dir, "SystemView.form")
sys_java_path = os.path.join(views_dir, "SystemView.java")

# Form replacements for SystemView
if os.path.exists(sys_form_path):
    with open(sys_form_path, "r", encoding="utf-8") as f:
        form_content = f.read()
        
    old_layout_block = """      <Layout>
        <DimensionLayout dim="0">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" attributes="0">
                  <Group type="103" groupAlignment="1" max="-2" attributes="0">
                      <Component id="jPanelCategories" alignment="0" max="32767" attributes="0" />
                      <Component id="jPanelSupplimers" alignment="0" max="32767" attributes="0" />
                      <Component id="jPanelEmployes" alignment="0" max="32767" attributes="0" />
                  </Group>
                  <EmptySpace min="0" pref="0" max="32767" attributes="0" />
              </Group>
              <Group type="102" attributes="0">
                  <Group type="103" groupAlignment="1" attributes="0">
                      <Component id="pnlPresupuesto" alignment="0" max="32767" attributes="0" />
                      <Component id="pnlGasto" max="32767" attributes="0" />
                      <Component id="jPanelProducts" max="32767" attributes="0" />
                  </Group>
                  <EmptySpace max="-2" attributes="0" />
              </Group>
          </Group>
        </DimensionLayout>
        <DimensionLayout dim="1">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" attributes="0">
                  <EmptySpace min="10" pref="10" max="-2" attributes="0" />
                  <Component id="jPanelProducts" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="25" pref="25" max="-2" attributes="0" />
                  <Component id="pnlGasto" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="25" pref="25" max="-2" attributes="0" />
                  <Component id="pnlPresupuesto" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="25" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelEmployes" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="25" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelSupplimers" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="25" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelCategories" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="-2" pref="255" max="-2" attributes="0" />
              </Group>
          </Group>
        </DimensionLayout>
      </Layout>"""

    new_layout_block = """      <Layout>
        <DimensionLayout dim="0">
          <Group type="103" groupAlignment="0" attributes="0">
              <Component id="jPanelProducts" alignment="0" max="32767" attributes="0" />
              <Component id="pnlGasto" alignment="0" max="32767" attributes="0" />
              <Component id="pnlPresupuesto" alignment="0" max="32767" attributes="0" />
              <Component id="jPanelEmployes" alignment="0" max="32767" attributes="0" />
              <Component id="jPanelSupplimers" alignment="0" max="32767" attributes="0" />
              <Component id="jPanelCategories" alignment="0" max="32767" attributes="0" />
          </Group>
        </DimensionLayout>
        <DimensionLayout dim="1">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" alignment="0" attributes="0">
                  <EmptySpace min="-2" pref="10" max="-2" attributes="0" />
                  <Component id="jPanelProducts" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="-2" pref="25" max="-2" attributes="0" />
                  <Component id="pnlGasto" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="-2" pref="25" max="-2" attributes="0" />
                  <Component id="pnlPresupuesto" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="-2" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelEmployes" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="-2" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelSupplimers" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="-2" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelCategories" min="-2" max="-2" attributes="0" />
                  <EmptySpace pref="230" max="32767" attributes="0" />
              </Group>
          </Group>
        </DimensionLayout>
      </Layout>"""

    # Check if already updated or update
    if old_layout_block in form_content:
        form_content = form_content.replace(old_layout_block, new_layout_block)
        with open(sys_form_path, "w", encoding="utf-8") as f:
            f.write(form_content)
        print("SystemView.form sidebar layout updated successfully!")
    elif "id=\"pnlPresupuesto\" alignment=\"0\" max=\"32767\" attributes=\"0\"" in form_content:
        print("SystemView.form seems already updated.")
    else:
        print("Warning: Old layout block not found in SystemView.form.")

# Java replacements for SystemView
if os.path.exists(sys_java_path):
    with open(sys_java_path, "r", encoding="utf-8") as f:
        java_content = f.read()
        
    old_java_layout = r"javax\.swing\.GroupLayout SidebarLayout = new javax\.swing\.GroupLayout\(Sidebar\);[\s\S]*?Sidebar\.setLayout\(SidebarLayout\);[\s\S]*?SidebarLayout\.setHorizontalGroup\([\s\S]*?\);[\s\S]*?SidebarLayout\.setVerticalGroup\([\s\S]*?\);"
    
    new_java_layout = """javax.swing.GroupLayout SidebarLayout = new javax.swing.GroupLayout(Sidebar);
        Sidebar.setLayout(SidebarLayout);
        SidebarLayout.setHorizontalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlGasto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelEmployes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelSupplimers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelCategories, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SidebarLayout.setVerticalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidebarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanelProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(pnlGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(pnlPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelEmployes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelSupplimers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(230, Short.MAX_VALUE))
        );"""
        
    if re.search(old_java_layout, java_content):
        java_content = re.sub(old_java_layout, new_java_layout, java_content)
        with open(sys_java_path, "w", encoding="utf-8") as f:
            f.write(java_content)
        print("SystemView.java sidebar layout updated successfully!")
    else:
        print("Error: Sidebar layout pattern not found in SystemView.java")


# 2. Update PresupuestoView
pres_form_path = os.path.join(views_dir, "PresupuestoView.form")
pres_java_path = os.path.join(views_dir, "PresupuestoView.java")

# Form replacements for PresupuestoView
if os.path.exists(pres_form_path):
    with open(pres_form_path, "r", encoding="utf-8") as f:
        form_content = f.read()
        
    old_layout_block = """      <Layout>
        <DimensionLayout dim="0">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" alignment="0" attributes="0">
                  <Group type="103" groupAlignment="1" max="-2" attributes="0">
                      <Component id="jPanelCategories" alignment="0" max="32767" attributes="0" />
                      <Component id="jPanelSupplimers" alignment="0" max="32767" attributes="0" />
                      <Component id="jPanelEmployes" alignment="0" max="32767" attributes="0" />
                  </Group>
                  <EmptySpace min="0" pref="0" max="32767" attributes="0" />
              </Group>
              <Group type="102" alignment="0" attributes="0">
                  <Group type="103" groupAlignment="1" attributes="0">
                      <Component id="jPanelCustomers" alignment="0" max="32767" attributes="0" />
                      <Component id="pnlGasto" alignment="1" max="32767" attributes="0" />
                      <Component id="jPanelProducts" alignment="1" max="32767" attributes="0" />
                  </Group>
                  <EmptySpace max="-2" attributes="0" />
              </Group>
          </Group>
        </DimensionLayout>
        <DimensionLayout dim="1">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" alignment="0" attributes="0">
                  <EmptySpace min="10" pref="10" max="-2" attributes="0" />
                  <Component id="jPanelProducts" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="25" pref="25" max="-2" attributes="0" />
                  <Component id="pnlGasto" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="25" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelCustomers" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="25" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelEmployes" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="25" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelSupplimers" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="25" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelCategories" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="-2" pref="255" max="-2" attributes="0" />
              </Group>
          </Group>
        </DimensionLayout>
      </Layout>"""

    new_layout_block = """      <Layout>
        <DimensionLayout dim="0">
          <Group type="103" groupAlignment="0" attributes="0">
              <Component id="jPanelProducts" alignment="0" max="32767" attributes="0" />
              <Component id="pnlGasto" alignment="0" max="32767" attributes="0" />
              <Component id="jPanelCustomers" alignment="0" max="32767" attributes="0" />
              <Component id="jPanelEmployes" alignment="0" max="32767" attributes="0" />
              <Component id="jPanelSupplimers" alignment="0" max="32767" attributes="0" />
              <Component id="jPanelCategories" alignment="0" max="32767" attributes="0" />
          </Group>
        </DimensionLayout>
        <DimensionLayout dim="1">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" alignment="0" attributes="0">
                  <EmptySpace min="-2" pref="10" max="-2" attributes="0" />
                  <Component id="jPanelProducts" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="-2" pref="25" max="-2" attributes="0" />
                  <Component id="pnlGasto" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="-2" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelCustomers" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="-2" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelEmployes" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="-2" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelSupplimers" min="-2" max="-2" attributes="0" />
                  <EmptySpace min="-2" pref="25" max="-2" attributes="0" />
                  <Component id="jPanelCategories" min="-2" max="-2" attributes="0" />
                  <EmptySpace pref="230" max="32767" attributes="0" />
              </Group>
          </Group>
        </DimensionLayout>
      </Layout>"""

    if old_layout_block in form_content:
        form_content = form_content.replace(old_layout_block, new_layout_block)
        with open(pres_form_path, "w", encoding="utf-8") as f:
            f.write(form_content)
        print("PresupuestoView.form sidebar layout updated successfully!")
    elif "id=\"jPanelCustomers\" alignment=\"0\" max=\"32767\" attributes=\"0\"" in form_content:
        print("PresupuestoView.form seems already updated.")
    else:
        print("Warning: Old layout block not found in PresupuestoView.form.")

# Java replacements for PresupuestoView
if os.path.exists(pres_java_path):
    with open(pres_java_path, "r", encoding="utf-8") as f:
        java_content = f.read()
        
    old_java_layout = r"javax\.swing\.GroupLayout SidebarLayout = new javax\.swing\.GroupLayout\(Sidebar\);[\s\S]*?Sidebar\.setLayout\(SidebarLayout\);[\s\S]*?SidebarLayout\.setHorizontalGroup\([\s\S]*?\);[\s\S]*?SidebarLayout\.setVerticalGroup\([\s\S]*?\);"
    
    new_java_layout = """javax.swing.GroupLayout SidebarLayout = new javax.swing.GroupLayout(Sidebar);
        Sidebar.setLayout(SidebarLayout);
        SidebarLayout.setHorizontalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlGasto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelEmployes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelSupplimers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelCategories, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SidebarLayout.setVerticalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidebarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanelProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(pnlGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelEmployes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelSupplimers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(230, Short.MAX_VALUE))
        );"""
        
    if re.search(old_java_layout, java_content):
        java_content = re.sub(old_java_layout, new_java_layout, java_content)
        with open(pres_java_path, "w", encoding="utf-8") as f:
            f.write(java_content)
        print("PresupuestoView.java sidebar layout updated successfully!")
    else:
        print("Error: Sidebar layout pattern not found in PresupuestoView.java")
